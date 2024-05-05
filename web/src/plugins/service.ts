import request from "@/utils/request";
import lodash from "lodash";

const modules = import.meta.glob(["@/apis/**.ts", "@/apis/**/*.ts"], {
  import: "default",
  eager: true,
});

const apis: any = {};

// api配置
function config(api: any = {}, data: any) {
  const config: any = {};

  config.method = api.method || "post";

  config.url = api.url || "";

  if (api.blob) {
    config.responseType = "blob";
  }

  if (api.responseType) {
    config.responseType = api.responseType;
  }

  if (api.baseUrl) {
    config.baseURL = api.baseUrl;
  }

  if (api.headers) {
    config.headers = api.headers;
  }

  if (config.method === "GET" || config.method === "get") {
    return request({ ...config, params: data });
  } else {
    return request({ ...config, data: data });
  }
}

Object.keys(modules).forEach((key: any) => {
  //1.获取文件路径数组
  const newKey = key.replace(".ts", "").replace("/src/apis/", "");
  const pathArr = newKey.split("/");
  //2.获取文件下所有导出对象
  const obj: any = modules[key];
  const newObj: any = {};
  // 3.重新配置参数
  Object.keys(obj).forEach((item: any) => {
    newObj[item] = (data: any) => {
      return config(obj[item], data);
    };
  });
  //4.深层装配
  lodash.set(apis, pathArr, newObj);
});

export default apis;
