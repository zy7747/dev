import type { App } from "vue";
import { hasRole } from "./permission/hasRole";
import { hasPermission } from "./permission/hasPermission";

/**
 * 导出指令：v-xxx
 * @methods hasRole 用户权限，用法: v-hasRole
 * @methods hasPermission 按钮权限，用法: v-hasPermission
 */
const setupAuth = (app: App<Element>) => {
  hasRole(app);
  hasPermission(app);
};

export default setupAuth;
