import request from "@/utils/request";

export default {
  login,
  page,
  save,
  remove,
  detail,
};

export function login(params: any) {
  return request({
    url: "/user/login",
    method: "get",
    params,
  });
}

export function page(params: any) {
  return request({
    url: "/user/page",
    method: "get",
    params,
  });
}

export function detail(params: any) {
  return request({
    url: "/user/detail",
    method: "get",
    params,
  });
}

export function save(data: any) {
  return request({
    url: "/user/save",
    method: "post",
    data,
  });
}

export function remove(data: any) {
  return request({
    url: "/user/delete",
    method: "delete",
    data,
  });
}
