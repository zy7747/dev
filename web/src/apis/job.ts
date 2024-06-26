export default {
  page: {
    url: "/job/page",
    method: "get",
    app: "/system-api",
    text: "定时任务分页",
  },
  list: {
    url: "/job/list",
    method: "get",
    app: "/system-api",
    text: "定时任务列表",
  },
  detail: {
    url: "/job/detail",
    method: "get",
    app: "/system-api",
    text: "定时任务详情",
  },
  save: {
    url: "/job/save",
    method: "post",
    app: "/system-api",
    text: "定时任务编辑",
  },
  saveList: {
    url: "/job/saveList",
    method: "post",
    app: "/system-api",
    text: "定时任务批量编辑",
  },
  remove: {
    url: "/job/delete",
    method: "delete",
    app: "/system-api",
    text: "定时任务删除",
  },
  imports: {
    url: "/job/import",
    method: "post",
    app: "/system-api",
    text: "定时任务导入",
  },
  exports: {
    url: "/job/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "定时任务导出",
  },
};
