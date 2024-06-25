export default {
  page: {
    url: "/userOnline/page",
    method: "get",
    app: "/system-api",
    text: "在线用户分页",
  },
  list: {
    url: "/userOnline/list",
    method: "get",
    app: "/system-api",
    text: "在线用户列表",
  },
  detail: {
    url: "/userOnline/detail",
    method: "get",
    app: "/system-api",
    text: "在线用户详情",
  },
  save: {
    url: "/userOnline/save",
    method: "post",
    app: "/system-api",
    text: "在线用户编辑",
  },
  saveList: {
    url: "/userOnline/saveList",
    method: "post",
    app: "/system-api",
    text: "在线用户批量编辑",
  },
  remove: {
    url: "/userOnline/delete",
    method: "delete",
    app: "/system-api",
    text: "在线用户删除",
  },
  imports: {
    url: "/userOnline/import",
    method: "post",
    app: "/system-api",
    text: "在线用户导入",
  },
  exports: {
    url: "/userOnline/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "在线用户导出",
  },
};
