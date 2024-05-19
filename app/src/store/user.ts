import { userLogin } from "@/apis/user";
import { defineStore } from "pinia";
import { getToken, setToken } from "@/utils/auth";

export const useUserStore = defineStore({
  id: "user", // id必填，且需要唯一
  state: () => {
    return {
      userInfo: {} as any,
      token: getToken(),
      permission: [], //权限
      asyncRoutes: [] as any, //路由
    };
  },
  actions: {
    // 登录
    login(loginInfo: any) {
      return userLogin({ ...loginInfo, loginSystem: "videoWeb" }).then(
        (response: any) => {
          if (response.code === 200) {
            this.token = response.data.token;
            this.userInfo = response.data.userInfo;
            this.asyncRoutes = response.data.menuList;

            setToken(response.data.token);

            return response.data;
          } else {
            return false;
          }
        }
      );
    },
  },
});
