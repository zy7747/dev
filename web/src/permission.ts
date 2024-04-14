import { router } from "@/router";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import { getToken } from "./utils/auth";
import { useUserStore } from "@/store/user";
import { useDictStore } from "@/store/dict";
const appTitle = import.meta.env.VITE_APP_TITLE;

const whiteList = ["/login", "/404", "/500"]; //白名单

router.beforeEach(async (to: any, _from, next) => {
  NProgress.start();
  const userStore = useUserStore();
  const useDict = useDictStore();
  const token = getToken(); //token
  const url = to.path; //跳转路径

  // 1. 已登录 去登录页 -> 跳转首页
  if (token && url === "/login") {
    next({ path: "/" });
  }

  // 2. 已登录 不是登录页 -> 放行
  if (token && url !== "/login") {
    if (!userStore.asyncRoutes.length) {
      //字典
      useDict.getDictMap();
      //用户信息
      await userStore.getUserInfo();
      //路由
      userStore.asyncRoutes.forEach((item: any) => {
        router.addRoute(item);
      });

      next({ ...to, replace: true });
    } else {
      next();
    }
  }

  // 3. 未登录 在白名单 -> 放行
  if (!token && whiteList.indexOf(url) !== -1) {
    next();
  }

  // 4. 未登录 不在白名单 -> 登录页
  if (!token && whiteList.indexOf(url) === -1) {
    next({ path: "/login" });
  }

  //动态标题
  if (to.meta && to.meta.title) {
    const title = to.meta.title ? to.meta.title : "";
    window.document.title = title + " - " + appTitle;
  } else {
    window.document.title = appTitle;
  }
});

router.afterEach(() => {
  NProgress.done();
});
