import { defineStore } from "pinia";
import { useUserStore } from "@/store/user";

const useUser = useUserStore();
const routes = useUser.menuList;

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
      //判断是否有
      if (this.visitedViews.some((v: any) => v.path === route.path)) return;
      //找到路由
      const view = routes.find((i: any) => i.path === route.path);
      //可视路由
      this.visitedViews.push({
        ...view,
      });
      //缓存路由
      if (view && view.meta && view.meta.keepAlive) {
        this.keepViews.push(view.name);
      }
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
    },
  },
});
