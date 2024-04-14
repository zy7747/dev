import axios from "axios";
import { getToken } from "./auth";
import { useUserStore } from "@/store/user";

const baseUrl = import.meta.env.VITE_APP_BASE_API;

const service = axios.create({
  baseURL: baseUrl,
  timeout: 5000,
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    const userStore = useUserStore();
    // 如果存在登录用户信息，则将其设置到请求头部
    if (getToken()) {
      config.headers["Authorization"] = getToken();
    }

    if (userStore.userInfo.id) {
      config.headers["UserId"] = userStore.userInfo.id;
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (res: any) => {
    // 哪怕网络请求成功, 也有两种可能
    if (res) {
      if (res.data.code === 400) {
        ElNotification({
          title: "Warning",
          message: res.data.message,
          type: "warning",
        });
      } else if (res.data.code === 500) {
        ElNotification({
          title: "Error",
          message: res.data.message,
          type: "error",
        });
      } else {
      }
      return res.data;
    } else {
      ElNotification({
        title: "Error",
        message: "接口错误，请联系管理员",
        type: "error",
      });
      return Promise.reject(new Error("Error"));
    }
  },
  (err) => {
    // 失败
    ElNotification({
      title: "Error",
      message: "接口错误，请联系管理员",
      type: "error",
    });
    console.dir(err);
    // 第二个是失败回调函数(网络层面)
    return Promise.reject(err);
  }
);

export default service;
