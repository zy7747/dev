export default {
  page: {
    url: "/dict/page",
    method: "get",
    app: "dict",
    text: "字典分页",
  },
  list: {
    url: "/dict/list",
    method: "get",
    app: "dict",
    text: "字典列表",
  },
  detail: {
    url: "/dict/detail",
    method: "get",
    app: "dict",
    text: "字典详情",
  },
  save: {
    url: "/dict/save",
    method: "post",
    app: "dict",
    text: "字典编辑",
  },
  saveList: {
    url: "/dict/saveList",
    method: "post",
    app: "dict",
    text: "字典批量编辑",
  },
  remove: {
    url: "/dict/delete",
    method: "delete",
    app: "dict",
    text: "字典删除",
  },
  imports: {
    url: "/dict/import",
    method: "post",
    app: "dict",
    text: "字典导入",
  },
  exports: {
    url: "/dict/export",
    method: "get",
    app: "dict",
    blob: true,
    text: "字典导出",
  },
  dictMap: {
    url: "/dict/dictMap",
    method: "get",
    app: "dict",
    text: "字典Map",
  },
};
