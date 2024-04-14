import type { App } from "vue";
import type { RouteRecordRaw } from "vue-router";
import { createRouter, createWebHashHistory } from "vue-router";

//其他独立的路由在这里拼接
const baseRoute: any = [
  {
    path: "/",
    redirect: "/dashboard",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/system/login/index.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/views/system/404/index.vue"),
  },
];

export const menuRoute: any = [
  // {
  //   path: "/test",
  //   name: "test",
  //   title: "测试",
  //   component: () => import("@/views/system/test/index.vue"),
  // },
];

const Routes = [...menuRoute, ...baseRoute];

// app router
export const router = createRouter({
  history: createWebHashHistory(),
  routes: Routes as RouteRecordRaw[],
});

export function setupRouter(app: App<Element>) {
  app.use(router);
}
