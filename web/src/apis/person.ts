export default {
  page: {
    url: "/person/page",
    method: "get",
    app: "/system-api",
    text: "个人信息分页",
  },
  list: {
    url: "/person/list",
    method: "get",
    app: "/system-api",
    text: "个人信息列表",
  },
  detail: {
    url: "/person/detail",
    method: "get",
    app: "/system-api",
    text: "个人信息详情",
  },
  save: {
    url: "/person/save",
    method: "post",
    app: "/system-api",
    text: "个人信息编辑",
  },
  saveList: {
    url: "/person/saveList",
    method: "post",
    app: "/system-api",
    text: "个人信息批量编辑",
  },
  remove: {
    url: "/person/delete",
    method: "delete",
    app: "/system-api",
    text: "个人信息删除",
  },
  imports: {
    url: "/person/import",
    method: "post",
    app: "/system-api",
    text: "个人信息导入",
  },
  exports: {
    url: "/person/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "个人信息导出",
  },
};
