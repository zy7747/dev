import type { App } from "vue";
import { useUserStore } from "@/store/user";

export function hasRole(app: App<Element>) {
  app.directive("hasRole", (el, binding) => {
    const userStore = useUserStore();

    const { value } = binding;
    const super_admin = "admin";
    const roles: any = userStore.roles;

    if (value && value instanceof Array && value.length > 0) {
      const roleFlag = value;

      const hasRole = roles.some((role: string) => {
        return super_admin === role || roleFlag.includes(role);
      });

      if (!hasRole) {
        el.parentNode && el.parentNode.removeChild(el);
      }
    }
  });
}
