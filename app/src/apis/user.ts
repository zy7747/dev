import request from "@/utils/request";

//获取视频分页
export function userLogin(params: any): any {
  return request({
    url: "/user/login",
    method: "get",
    params,
  });
}
