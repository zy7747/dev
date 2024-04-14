import { defineStore } from "pinia";
import { removeToken, setToken } from "@/utils/auth";
import { handleTree } from "@/utils/formatData";
import { menuRoute } from "@/router/index";
const modules = import.meta.glob("../views/**/*.vue");

const resComponent = (name: string) => {
  return modules[`../views${name}.vue`];
};

export const useUserStore = defineStore({
  id: "user", // id必填，且需要唯一
  state: () => {
    return {
      userInfo: {} as any, //用户信息
      roles: [] as any, //角色
      menu: [] as any, //菜单
      menuList: [] as any, //菜单
      permission: [] as any, //权限
      asyncRoutes: [] as any, //路由
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
            this.userInfo = response.data.userInfo;
            const { menu, permission } = this.getMenu(response.data.menuList);
            this.asyncRoutes = this.getRoutes(response.data.menuList);
            this.roles = response.data.roles;
            this.menu = menu;
            this.permission = permission;

            return response.data;
          } else {
            removeToken();
          }
        })
        .catch(() => {
          removeToken();
        });
    },
    //获取路由
    getRoutes(routes: any) {
      let myRoute: any = [];

      routes.forEach((item: any) => {
        if (item.type === "menu") {
          myRoute.push({
            id: item.id,
            parentId: item.parentId,
            name: item.name,
            title: item.title,
            component: resComponent(item.component),
            path: item.path,
            meta: {
              title: item.title,
              keepAlive: item.keepAlive,
              icon: item.icon,
            },
          });
        }
      });

      const route = [
        {
          path: "/",
          component: () => import("@/layouts/index.vue"),
          children: myRoute,
        },
        {
          path: "/:pathMatch(.*)*",
          component: () => import("@/views/system/404/index.vue"),
        },
      ];

      this.menuList = myRoute;

      return route;
    },
    getMenu(data: any) {
      const menu: any = [];
      const permission: any = [];

      data.forEach((item: any) => {
        if (
          (item.type === "directory" ||
            item.type === "menu" ||
            item.type === "system") &&
          item.status === "0"
        ) {
          menu.push({
            ...item,
            meta: {
              title: item.title,
              alwaysShow: item.alwaysShow,
              visible: item.visible,
              icon: item.icon,
            },
          });
        } else if (item.type === "button" && item.status === "0") {
          //按钮
          if (item.permission) {
            permission.push(item.permission);
          }
        }
      });

      menu.sort((a: any, b: any) => a.sort - b.sort);

      const menuList = handleTree(menu);

      const list = menuList[0].children.map((i: any) => {
        return { ...i, parentId: null };
      });

      menuRoute.forEach((item: any) => {
        list.push({
          ...item,
          meta: {
            title: item.title,
            alwaysShow: "1",
            visible: "1",
          },
        });
      });

      return { menu: list, permission };
    },
  },
});
