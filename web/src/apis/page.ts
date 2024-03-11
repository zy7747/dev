import request from "@/utils/request";

export default {
  page,
  save,
  remove,
  detail,
};

export function page(params: any) {
  return request({
    url: "/page/page",
    method: "get",
    params,
  });
}

export function detail(params: any) {
  return request({
    url: "/page/detail",
    method: "get",
    params,
  });
}

export function save(data: any) {
  return request({
    url: "/page/save",
    method: "post",
    data,
  });
}

export function remove(data: any) {
  return request({
    url: "/page/delete",
    method: "delete",
    data,
  });
}
