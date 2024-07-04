export default {
  page: {
    url: "/page/page",
    method: "get",
    app: "/system-api",
    text: "配置页面分页",
  },
  list: {
    url: "/page/list",
    method: "get",
    app: "/system-api",
    text: "配置页面列表",
  },
  detail: {
    url: "/page/detail",
    method: "get",
    app: "/system-api",
    text: "配置页面详情",
  },
  save: {
    url: "/page/save",
    method: "post",
    app: "/system-api",
    text: "配置页面编辑",
  },
  saveList: {
    url: "/page/saveList",
    method: "post",
    app: "/system-api",
    text: "配置页面批量编辑",
  },
  remove: {
    url: "/page/delete",
    method: "delete",
    app: "/system-api",
    text: "配置页面删除",
  },
  imports: {
    url: "/page/import",
    method: "post",
    app: "/system-api",
    text: "配置页面导入",
  },
  exports: {
    url: "/page/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "配置页面导出",
  },
};
