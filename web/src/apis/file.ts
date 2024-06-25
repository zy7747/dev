export default {
  page: {
    url: "/file/page",
    method: "get",
    app: "/file-api",
    text: "文件分页",
  },
  list: {
    url: "/file/list",
    method: "get",
    app: "/file-api",
    text: "文件列表",
  },
  fileDetailList: {
    url: "/file/fileDetailList",
    method: "get",
    app: "/file-api",
    text: "文件列表",
  },
  detail: {
    url: "/file/detail",
    method: "get",
    app: "/file-api",
    text: "文件详情",
  },
  save: {
    url: "/file/save",
    method: "post",
    app: "/file-api",
    text: "文件编辑",
  },
  saveList: {
    url: "/file/saveList",
    method: "post",
    app: "/file-api",
    text: "文件批量编辑",
  },
  remove: {
    url: "/file/delete",
    method: "delete",
    app: "/file-api",
    text: "文件删除",
  },
  imports: {
    url: "/file/import",
    method: "post",
    app: "/file-api",
    text: "文件导入",
  },
  uploadFile: {
    url: "/file/uploadFile",
    method: "post",
    app: "/file-api",
    text: "文件上传",
  },
  exports: {
    url: "/file/export",
    method: "get",
    app: "/file-api",
    blob: true,
    text: "文件导出",
  },
};
