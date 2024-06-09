import { defineStore } from "pinia";

export const useTabStore = defineStore({
  id: "tabs", // id必填，且需要唯一
  state: () => {
    return {
      visitedViews: [] as any,
      keepViews: [] as any,
    };
  },
  actions: {
    addTab(route: any) {
      //判断是否添加过路由
      if (this.visitedViews.some((v: any) => v.path === route.path)) return;
      //找到菜单对应的路由
      const view = {
        ...route,
        ...route.meta,
      };

      const { matched, redirectedFrom, ...routeInfo } = view;

      //可视路由
      this.visitedViews.push(routeInfo);
      //缓存路由
      if (routeInfo && routeInfo.meta && routeInfo.meta.keepAlive) {
        this.keepViews.push(routeInfo.name);
      }
      this.saveTab();
    },
    removeTab(route: any) {
      const index = this.visitedViews.findIndex(
        (i: any) => i.path === route.path
      );
      //删除可视路由
      this.visitedViews.splice(index, 1);
      //删除缓存路由
      const keepIndex = this.keepViews.indexOf(route.name);
      index > -1 && this.keepViews.splice(keepIndex, 1);
      this.saveTab();
    },
    saveTab() {
      sessionStorage.setItem("tabs", JSON.stringify(this.visitedViews));
    },
    setTab() {
      const str = sessionStorage.getItem("tabs");
      const list = str ? JSON.parse(str) : [];

      this.visitedViews.push(...list);
    },
  },
});
