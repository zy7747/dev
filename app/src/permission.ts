import { getToken } from "@/utils/auth";

// 登录页面
const loginPage = "/pages/login";

// 页面白名单
const whiteList = [
  "/pages/login",
  "/pages/register",
  "/pages/common/webview/index",
];

// 检查地址白名单
function checkWhite(url: string) {
  const path = url.split("?")[0];
  return whiteList.indexOf(path) !== -1;
}

// 路由拦截器
uni.addInterceptor("navigateTo", {
  invoke(to) {
    //判断是否有TOKEN

    if (getToken()) {
      if (to.url === loginPage) {
        uni.reLaunch({ url: "/" });
        return false;
      }

      return true;
    } else {
      //如果在白名单
      if (checkWhite(to.url)) {
        return true;
      } else {
        return false;
      }
    }
  },
  fail(err) {
    // 失败回调拦截
    console.log(err);
  },
});
