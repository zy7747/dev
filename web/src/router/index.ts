import type { App } from "vue";
import type { RouteRecordRaw } from "vue-router";
import { createRouter, createWebHashHistory } from "vue-router";

interface routeModuleListType {
  path: any;
  redirect?: string;
  name?: string;
  component?: any;
  children?: any;
  meta?: any;
}

//其他独立的路由在这里拼接
const routeModuleList: routeModuleListType[] = [
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
    path: "/",
    component: () => import("@/layouts/index.vue"),
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: () => import("@/views/pages/dashboard/index.vue"),
      },
      {
        path: "page",
        name: "page",
        component: () => import("@/views/pages/configuration/page/index.vue"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/system/user/index.vue"),
    children: [
      {
        path: "center",
        name: "center",
        component: () => import("@/views/system/user/center/index.vue"),
      },
    ],
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/system/404/index.vue"),
  },
];

const Routes = routeModuleList;

// app router
export const router = createRouter({
  history: createWebHashHistory(),
  routes: Routes as RouteRecordRaw[],
});

export function setupRouter(app: App<Element>) {
  app.use(router);
}
