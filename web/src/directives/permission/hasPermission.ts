import type { App } from "vue";

export function hasPermission(app: App<Element>) {
  app.directive("hasPermission", (el, binding) => {
    const { value } = binding;
    const all_permission = "*:*:*"; // 全部权限
    const permissions: any = []; // 用户拥有的权限标识的数组

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
    } else {
      throw new Error("没有权限");
    }
  });
}
