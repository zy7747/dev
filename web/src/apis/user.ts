import request from "@/utils/request";

export default {
  login,
  page,
  save,
  remove,
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

export function save(params: any) {
  return request({
    url: "/user/save",
    method: "post",
    params,
  });
}

export function remove(params: any) {
  return request({
    url: "/user/delete",
    method: "post",
    params,
  });
}
