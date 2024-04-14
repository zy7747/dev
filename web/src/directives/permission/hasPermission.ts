import type { App } from "vue";
import { useUserStore } from "@/store/user";

export function hasPermission(app: App<Element>) {
  app.directive("hasPermission", (el, binding) => {
    const userStore = useUserStore();

    const { value } = binding;
    const all_permission = "*:*:*"; // 全部权限
    const permissions: any = userStore.permission; // 用户拥有的权限标识的数组

    if (value && value instanceof Array && value.length > 0) {
      const permissionFlag = value;

      const hasPermissions = permissions.some((permission: string) => {
        return (
          all_permission === permission || permissionFlag.includes(permission)
        );
      });

      if (!hasPermissions) {
        el.parentNode && el.parentNode.removeChild(el);
      }
    }
  });
}
