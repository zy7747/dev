export default {
  page: {
    url: "/message/page",
    method: "get",
    app: "/system-api",
    text: "消息通知分页",
  },
  list: {
    url: "/message/list",
    method: "get",
    app: "/system-api",
    text: "消息通知列表",
  },
  detail: {
    url: "/message/detail",
    method: "get",
    app: "/system-api",
    text: "消息通知详情",
  },
  save: {
    url: "/message/save",
    method: "post",
    app: "/system-api",
    text: "消息通知编辑",
  },
  saveList: {
    url: "/message/saveList",
    method: "post",
    app: "/system-api",
    text: "消息通知批量编辑",
  },
  remove: {
    url: "/message/delete",
    method: "delete",
    app: "/system-api",
    text: "消息通知删除",
  },
  imports: {
    url: "/message/import",
    method: "post",
    app: "/system-api",
    text: "消息通知导入",
  },
  exports: {
    url: "/message/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "消息通知导出",
  },
};
