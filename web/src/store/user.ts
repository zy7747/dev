import { defineStore } from "pinia";
import { removeToken, setToken } from "@/utils/auth";
import { getLoginInfo, setLoginInfo, removeLoginInfo } from "@/utils/loginInfo";
import { router } from "@/router";

const modules = import.meta.glob("../views/**/*.vue");

const resComponent = (name: string) => {
  return modules[`../views${name}.vue`];
};

//重新整理pid
function handleTreeList(data: any, newList: any) {
  data.forEach((childPar: any) => {
    //如果没有parentId
    if (!childPar.parentId) {
      newList.push(childPar);
    } else {
      //如果有找parentId===id相同的
      const arr = data.find((child: any) => {
        return childPar.parentId === child.id;
      });

      if (arr) {
        newList.push(childPar);
      } else {
        newList.push({ ...childPar, parentId: null });
      }
    }
  });
}
//树形
function handleTree(
  data: any,
  rootId?: any,
  id?: any,
  parentId?: any,
  children?: any
): any {
  id = id || "id";
  parentId = parentId || "parentId";
  children = children || "children";
  rootId = rootId || null;

  //对源数据深度克隆
  const cloneData = data;
  //循环所有项
  const treeData = cloneData.filter((father: any) => {
    let branchArr = cloneData.filter((child: any) => {
      //返回每一项的子级数组
      return father[id] === child[parentId];
    });
    branchArr.length > 0 ? (father.children = branchArr) : "";

    //返回第一层
    return father[parentId] === rootId;
  });

  return treeData !== "" ? treeData : data;
}

export const useUserStore = defineStore({
  id: "user", // id必填，且需要唯一
  state: () => {
    return {
      userId: {} as any, //用户Id
      loginId: null, //当次登录ID
      loginTime: null, //当次登录时间
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
      return Service.system.user
        .login({ ...loginInfo })
        .then((response: any) => {
          if (response.code === 200) {
            //加入Token
            setToken(response.data.token);

            this.userInfo = response.data.userInfo;
            this.userId = response.data.userInfo.id;
            setLoginInfo(
              JSON.stringify({
                loginId: response.data.loginId,
                loginTime: response.data.loginTime,
              })
            );

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
    logout() {
      const loginInfo: any = getLoginInfo();

      return Service.system.user
        .logout({ id: JSON.parse(loginInfo).loginId })
        .then((response: any) => {
          if (response.code === 200) {
            this.userInfo = [];
            this.userId = [];
            this.userId = [];
            this.roles = [];
            this.menu = [];
            this.menuList = [];
            this.asyncRoutes = [];

            removeToken();
            removeLoginInfo();

            router.push("/login");
          }
        });
    },
    getUserInfo() {
      return Service.system.user
        .userInfo({})
        .then((response: any) => {
          if (response.code === 200) {
            this.userInfo = response.data.userInfo;
            this.userId = response.data.userInfo.id;
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
      //只获取菜单
      const menuRouter = routes.filter((item: any) => {
        return item.type === "menu";
      });
      //重新整理PID
      const newList: any = [];

      handleTreeList(menuRouter, newList);
      //组装数据
      const myRoute = newList.map((item: any) => {
        return {
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
        };
      });

      const route = [
        {
          path: "/",
          component: () => import("@/layouts/index.vue"),
          children: handleTree(myRoute),
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
          item.status === "1" &&
          item.visible
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
        } else if (item.type === "button" && item.status === "1") {
          //按钮
          if (item.permission) {
            permission.push(item.permission);
          }
        }
      });

      menu.sort((a: any, b: any) => a.sort - b.sort);

      const menuList = handleTree(menu);

      let list = [];

      if (menuList.length === 1) {
        list = menuList[0].children.map((i: any) => {
          return { ...i, parentId: null };
        });
      } else {
        list = menuList;
      }

      return { menu: list, permission };
    },
  },
});
