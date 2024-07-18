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

      //可视路由
      this.visitedViews.push({ ...route, ...route.meta });

      //缓存路由
      if (route && route.meta && route.meta.keepAlive) {
        this.keepViews.push(route.name);
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
    //关闭其他
    removeOther(route: any) {
      const arr = this.visitedViews.filter(
        (item: any) => item.path === route.path
      );

      this.visitedViews.splice(0, this.visitedViews.length, ...arr);

      this.saveTab();
    },
    //关闭左侧
    removeLeft(route: any) {
      const index = this.visitedViews.findIndex(
        (item: any) => item.path === route.path
      );

      this.visitedViews.splice(0, index);

      this.saveTab();
    },
    //关闭右侧
    removeRight(route: any) {
      const index = this.visitedViews.findIndex(
        (item: any) => item.path === route.path
      );

      this.visitedViews.splice(index + 1);

      this.saveTab();
    },
    saveTab() {
      sessionStorage.setItem("tabs", JSON.stringify(this.visitedViews));
    },
    setTab() {
      const str = sessionStorage.getItem("tabs");
      const list = str ? JSON.parse(str) : [];

      this.visitedViews.splice(0);

      this.visitedViews.push(...list);
    },
  },
});
