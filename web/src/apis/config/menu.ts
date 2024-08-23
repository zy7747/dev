export default {
  page: {
    url: "/menu/page",
    method: "get",
    app: "/system-api",
    text: "菜单分页",
  },
  list: {
    url: "/menu/list",
    method: "get",
    app: "/system-api",
    text: "菜单列表",
  },
  detail: {
    url: "/menu/detail",
    method: "get",
    app: "/system-api",
    text: "菜单详情",
  },
  save: {
    url: "/menu/save",
    method: "post",
    app: "/system-api",
    text: "菜单编辑",
  },
  saveList: {
    url: "/menu/saveList",
    method: "post",
    app: "/system-api",
    text: "菜单批量编辑",
  },
  remove: {
    url: "/menu/delete",
    method: "delete",
    app: "/system-api",
    text: "菜单删除",
  },
  imports: {
    url: "/menu/import",
    method: "post",
    app: "/system-api",
    text: "菜单导入",
  },
  exports: {
    url: "/menu/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "菜单导出",
  },
};
