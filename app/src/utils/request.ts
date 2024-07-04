// 请求拦截

import { getToken } from "@/utils/auth";
import { useUserStore } from "@/store/user";

function service(params: any) {
  const userStore = useUserStore();

  return new Promise((resolve, reject) => {
    uni.request({
      ...params,
      url: params.baseURL + params.url,
      data: params.params,
      header: {
        "ngrok-skip-browser-warning": 69420,
        Authorization: getToken(),
        UserId: userStore.userInfo.id,
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
