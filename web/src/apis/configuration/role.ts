export default {
  page: {
    url: "/role/page",
    method: "get",
    app: "role",
    text: "角色分页",
  },
  list: {
    url: "/role/list",
    method: "get",
    app: "role",
    text: "角色列表",
  },
  detail: {
    url: "/role/detail",
    method: "get",
    app: "role",
    text: "角色详情",
  },
  save: {
    url: "/role/save",
    method: "post",
    app: "role",
    text: "角色编辑",
  },
  saveList: {
    url: "/role/saveList",
    method: "post",
    app: "role",
    text: "角色批量编辑",
  },
  remove: {
    url: "/role/delete",
    method: "delete",
    app: "role",
    text: "角色删除",
  },
  imports: {
    url: "/role/import",
    method: "post",
    app: "role",
    text: "角色导入",
  },
  exports: {
    url: "/role/export",
    method: "get",
    app: "role",
    blob: true,
    text: "角色导出",
  },
};
