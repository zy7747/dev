// 请求拦截
const baseUrl = import.meta.env.VITE_APP_BASE_API;
import { getToken } from "@/utils/auth";

function service(params: any) {
  return new Promise((resolve, reject) => {
    uni.request({
      ...params,
      url: baseUrl + params.url,
      data: params.params,
      header: {
        "ngrok-skip-browser-warning": 69420,
        Authorization: getToken(),
      },
      success: (res: any) => {
        resolve(res.data);
      },
      fail: (error: any) => {
        reject(error);
      },
    });
  });
}

export default service;
