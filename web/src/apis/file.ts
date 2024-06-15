const baseUrl = import.meta.env.VITE_APP_FILE_API;

export default {
  page: {
    url: "/file/page",
    method: "get",
    app: "file",
    text: "文件分页",
    baseUrl,
  },
  list: {
    url: "/file/list",
    method: "get",
    app: "file",
    text: "文件列表",
    baseUrl,
  },
  fileDetailList: {
    url: "/file/fileDetailList",
    method: "get",
    app: "file",
    text: "文件列表",
    baseUrl,
  },
  detail: {
    url: "/file/detail",
    method: "get",
    app: "file",
    text: "文件详情",
    baseUrl,
  },
  save: {
    url: "/file/save",
    method: "post",
    app: "file",
    text: "文件编辑",
    baseUrl,
  },
  saveList: {
    url: "/file/saveList",
    method: "post",
    app: "file",
    text: "文件批量编辑",
    baseUrl,
  },
  remove: {
    url: "/file/delete",
    method: "delete",
    app: "file",
    text: "文件删除",
    baseUrl,
  },
  imports: {
    url: "/file/import",
    method: "post",
    app: "file",
    text: "文件导入",
    baseUrl,
  },
  uploadFile: {
    url: "/file/uploadFile",
    method: "post",
    app: "file",
    text: "文件上传",
    baseUrl,
  },
  exports: {
    url: "/file/export",
    method: "get",
    app: "file",
    blob: true,
    text: "文件导出",
    baseUrl,
  },
};
