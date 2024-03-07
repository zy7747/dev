import request from "@/utils/request";

export default {
  login,
  page,
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
