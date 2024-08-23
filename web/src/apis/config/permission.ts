export default {
  page: {
    url: "/permission/page",
    method: "get",
    app: "/system-api",
    text: "权限分页",
  },
  list: {
    url: "/permission/list",
    method: "get",
    app: "/system-api",
    text: "权限列表",
  },
  detail: {
    url: "/permission/detail",
    method: "get",
    app: "/system-api",
    text: "权限详情",
  },
  save: {
    url: "/permission/save",
    method: "post",
    app: "/system-api",
    text: "权限编辑",
  },
  saveList: {
    url: "/permission/saveList",
    method: "post",
    app: "/system-api",
    text: "权限批量编辑",
  },
  remove: {
    url: "/permission/delete",
    method: "delete",
    app: "/system-api",
    text: "权限删除",
  },
  imports: {
    url: "/permission/import",
    method: "post",
    app: "/system-api",
    text: "权限导入",
  },
  exports: {
    url: "/permission/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "权限导出",
  },
};
