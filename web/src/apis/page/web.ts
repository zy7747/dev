export default {
  page: {
    url: "/web/page",
    method: "get",
    app: "/system-api",
    text: "网址收藏分页",
  },
  list: {
    url: "/web/list",
    method: "get",
    app: "/system-api",
    text: "网址收藏列表",
  },
  detail: {
    url: "/web/detail",
    method: "get",
    app: "/system-api",
    text: "网址收藏详情",
  },
  save: {
    url: "/web/save",
    method: "post",
    app: "/system-api",
    text: "网址收藏编辑",
  },
  saveList: {
    url: "/web/saveList",
    method: "post",
    app: "/system-api",
    text: "网址收藏批量编辑",
  },
  remove: {
    url: "/web/delete",
    method: "delete",
    app: "/system-api",
    text: "网址收藏删除",
  },
  imports: {
    url: "/web/import",
    method: "post",
    app: "/system-api",
    text: "网址收藏导入",
  },
  exports: {
    url: "/web/export",
    method: "get",
    app: "/system-api",
    blob: true,
    text: "网址收藏导出",
  },
};
