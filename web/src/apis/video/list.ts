export default {
  page: {
    url: "/video/page",
    method: "get",
    app: "/video-api",
    text: "视频分页",
  },
  list: {
    url: "/video/list",
    method: "get",
    app: "/video-api",
    text: "视频列表",
  },
  detail: {
    url: "/video/detail",
    method: "get",
    app: "/video-api",
    text: "视频详情",
  },
  save: {
    url: "/video/save",
    method: "post",
    app: "/video-api",
    text: "视频编辑",
  },
  saveList: {
    url: "/video/saveList",
    method: "post",
    app: "/video-api",
    text: "视频批量编辑",
  },
  remove: {
    url: "/video/delete",
    method: "delete",
    app: "/video-api",
    text: "视频删除",
  },
  imports: {
    url: "/video/import",
    method: "post",
    app: "/video-api",
    text: "视频导入",
  },
  exports: {
    url: "/video/export",
    method: "get",
    app: "/video-api",
    blob: true,
    text: "视频导出",
  },
};
