import request from "@/utils/request";

//获取所有字典
export function dictAllList() {
  return request({
    url: "/dict/dictAllList",
    method: "get",
  });
}
