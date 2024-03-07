import type { App } from "vue";
import type { RouteRecordRaw } from "vue-router";
import { createRouter, createWebHashHistory } from "vue-router";

interface routeModuleListType {
  path: string;
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
    redirect: "/home",
  },
  {
    path: "/home",
    name: "home",
    component: () => import("@/layouts/index.vue"),
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
