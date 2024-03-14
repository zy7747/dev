import request from "@/utils/request";

export default {
  page(params: any) {
    return request({
      url: "/page/page",
      method: "get",
      params,
    });
  },
  detail(params: any) {
    return request({
      url: "/page/detail",
      method: "get",
      params,
    });
  },
  remove(data: any) {
    return request({
      url: "/page/delete",
      method: "delete",
      data,
    });
  },
  save(data: any) {
    return request({
      url: "/page/save",
      method: "post",
      data,
    });
  },
};
