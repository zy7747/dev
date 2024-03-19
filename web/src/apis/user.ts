import request from "@/utils/request";

export default {
  login(params: any) {
    return request({
      url: "/user/login",
      method: "get",
      params,
    });
  },
  page(params: any) {
    return request({
      url: "/user/page",
      method: "get",
      params,
    });
  },
  save(data: any) {
    return request({
      url: "/user/save",
      method: "post",
      data,
    });
  },
  exports(params: any) {
    return request({
      url: "/user/export",
      method: "get",
      responseType: "blob",
      params,
    });
  },
  imports(data: any) {
    return request({
      url: "/user/import",
      method: "post",
      data,
    });
  },
  remove(data: any) {
    return request({
      url: "/user/delete",
      method: "delete",
      data,
    });
  },
  detail(params: any) {
    return request({
      url: "/user/detail",
      method: "get",
      params,
    });
  },
};
