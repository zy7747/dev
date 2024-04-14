export default {
  page: {
    url: "/operationLog/page",
    method: "get",
    app: "operationLog",
    text: "操作日志分页",
  },
  list: {
    url: "/operationLog/list",
    method: "get",
    app: "operationLog",
    text: "操作日志列表",
  },
  detail: {
    url: "/operationLog/detail",
    method: "get",
    app: "operationLog",
    text: "操作日志详情",
  },
  save: {
    url: "/operationLog/save",
    method: "post",
    app: "operationLog",
    text: "操作日志编辑",
  },
  saveList: {
    url: "/operationLog/saveList",
    method: "post",
    app: "operationLog",
    text: "操作日志批量编辑",
  },
  remove: {
    url: "/operationLog/delete",
    method: "delete",
    app: "operationLog",
    text: "操作日志删除",
  },
  imports: {
    url: "/operationLog/import",
    method: "post",
    app: "operationLog",
    text: "操作日志导入",
  },
  exports: {
    url: "/operationLog/export",
    method: "get",
    app: "operationLog",
    blob: true,
    text: "操作日志导出",
  },
};
