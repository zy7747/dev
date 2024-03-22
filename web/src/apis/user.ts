export default {
  login: {
    url: "/user/login",
    method: "get",
    app: "user",
    text: "登录",
  },
  userInfo: {
    url: "/user/userInfo",
    method: "get",
    app: "user",
    text: "获取用户信息",
  },
  page: {
    url: "/user/page",
    method: "get",
    app: "user",
    text: "用户分页",
  },
  list: {
    url: "/user/list",
    method: "get",
    app: "user",
    text: "用户列表",
  },
  detail: {
    url: "/user/detail",
    method: "get",
    app: "user",
    text: "用户详情",
  },
  saveList: {
    url: "/user/saveList",
    method: "post",
    app: "user",
    text: "用户编辑",
  },
  delete: {
    url: "/user/delete",
    method: "delete",
    app: "user",
    text: "用户删除",
  },
  export: {
    url: "/user/export",
    method: "get",
    app: "user",
    blob: true,
    text: "用户导出",
  },
};
