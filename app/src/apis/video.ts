import request from "@/utils/request";

//获取视频分页
export function videoPage(params: any): any {
  return request({
    url: "/video/page",
    method: "get",
    params,
  });
}

// 热门视频
export function hotVideo() {
  return request({
    url: "/video/hotVideo",
    method: "get",
  });
}

//获取视频详情
export function videoDetail(params: any) {
  return request({
    url: "/video/detail",
    method: "get",
    params,
  });
}

// 修改播放量
export function playNum(params: any) {
  return request({
    url: "/video/playNum",
    method: "get",
    params,
  });
}
