import { defineStore } from "pinia";
import { removeToken, setToken } from "@/utils/auth";

export const useUserStore = defineStore({
  id: "user", // id必填，且需要唯一
  state: () => {
    return {
      userInfo: {} as any, //用户信息
      roles: [], //角色
      menu: [], //菜单
      permission: [], //权限
      asyncRoutes: [], //路由
    };
  },
  actions: {
    // 登录
    login(loginInfo: any) {
      return Service.user
        .login({ ...loginInfo, loginSystem: "system" })
        .then((response: any) => {
          if (response.code === 200) {
            //加入Token
            setToken(response.data.token);
            this.userInfo = response.data.userInfo;

            //1.记住密码
            if (loginInfo.rememberMe) {
              localStorage.setItem("rememberMe", JSON.stringify(loginInfo));
            } else {
              localStorage.removeItem("rememberMe");
            }

            return response.data;
          }
        });
    },
    getUserInfo() {
      return Service.user
        .userInfo({ loginSystem: "system" })
        .then((response: any) => {
          if (response.code === 200) {
            //3.存储用户信息
            this.userInfo = response.data.userInfo;

            return response.data;
          } else {
            removeToken();
          }
        });
    },
  },
});
