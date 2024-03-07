import axios from "axios";
import { getToken } from "./auth";
const baseUrl = import.meta.env.VITE_APP_BASE_API;

const service = axios.create({
  baseURL: baseUrl,
  timeout: 5000,
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    // 如果存在登录用户信息，则将其设置到请求头部
    if (getToken()) {
      config.headers["Authorization"] = getToken();
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
      // 成功
      return res.data;
    } else {
      // 失败
      return Promise.reject(new Error("Error"));
    }
  },
  (err) => {
    console.dir(err);
    // 第二个是失败回调函数(网络层面)
    return Promise.reject(err);
  }
);

export default service;
