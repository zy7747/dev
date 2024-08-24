# 一.创建项目

## 1.vite创建命令

```
pnpm create vite
```

## 2.拉取依赖

```
pnpm i
```

## 3.安装基础依赖

```cmd
#1.路由
pnpm i vue-router
#2.i18n
pnpm i vue-i18n
#3.axios
pnpm i axios
#4.element-plus
pnpm i element-plus
pnpm i @element-plus/icons-vue
#5.pinia
pnpm i pinia
#6.lodash
pnpm i lodash
#7.vxe-table
pnpm i vxe-pc-ui vxe-table 
#8.vite-plugin-html
pnpm i vite-plugin-html
#9.vite-plugin-svg-icons //svg
pnpm i vite-plugin-svg-icons
#10.js-cookie //cookie
pnpm i js-cookie
pnpm i @types/js-cookie -D
#11.unplugin-vue-components unplugin-auto-import // 自动引入
pnpm i unplugin-vue-components unplugin-auto-import
#12.sass
pnpm i sass
#13.@vavt/md-editor-extension md编辑器
pnpm i md-editor-v3 @vavt/md-editor-extension
#14.vitepress 文档
pnpm i vitepress
#15.windicss
pnpm i windicss vite-plugin-windicss
#16.dayjs
pnpm i dayjs
#17.animate.css
pnpm i animate.css
#18.highlight    代码高亮
pnpm i highlight
#19.hover
pnpm i hover.css
#20.js-beautify js代码美化
pnpm i js-beautify
#21.nprogress 进度条
pnpm i nprogress
#22.qrcode 二维码
pnpm i qrcode
#23.sortablejs 拖拽
pnpm i sortablejs
#24.vueuse 插件
pnpm i @vueuse/core
#25.jsx 插件
pnpm i @vitejs/plugin-vue-jsx -D
#26.xgplayer 西瓜播放器
pnpm i xgplayer
```

## 4.配置 vite.config.ts

```ts
import { defineConfig, loadEnv } from "vite";
import { createHtmlPlugin } from "vite-plugin-html";
import vue from "@vitejs/plugin-vue";
import svgLoader from "vite-svg-loader";
import Icons from "unplugin-icons/vite";
import { createSvgIconsPlugin } from "vite-plugin-svg-icons";
import WindiCSS from "vite-plugin-windicss";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";
import vueJsx from "@vitejs/plugin-vue-jsx"; // 添加这一句

import { resolve } from "path";
const getTarget = (mode: string, target: string) => {
  return loadEnv(mode, process.cwd())[target];
};

export default ({ mode }: any) => {
  const env = loadEnv(mode, process.cwd());
  return defineConfig({
    base: "/system",
    plugins: [
      vue(),
      vueJsx(),
      WindiCSS(),
      svgLoader(),
      createSvgIconsPlugin({
        iconDirs: [
          resolve(process.cwd(), "src/icons/app"),
          resolve(process.cwd(), "src/icons/flag"),
          resolve(process.cwd(), "src/icons/system"),
        ],
        // 指定symbolId格式（这里的配置与6.2步骤中的引入svg组件的name配置项写法有关）
        symbolId: "[name]",
      }),
      AutoImport({
        imports: [
          "vue",
          "vue-router",
          "pinia",
          {
            "@/plugins/download": [["default", "Download"]], //全局下载
          },
          {
            "@/plugins/service": [["default", "Service"]], //api
          },
          {
            "@/language/index": [["default", "$t"]], //翻译
          },
          {
            "@/dict": [
              ["Dict", "Dict"],
              ["DictService", "DictService"],
              ["useDict", "useDict"],
              ["getDictTag", "getDictTag"],
              ["getDictName", "getDictName"],
            ], //字典
          },
          {
            "@/hooks/tips": [
              ["removeSuccess", "removeSuccess"],
              ["submitSuccess", "submitSuccess"],
              ["importSuccess", "importSuccess"],
              ["exportSuccess", "exportSuccess"],
              ["validateFail", "validateFail"],
              ["uploadSuccess", "uploadSuccess"],
            ], //提示
          },
          {
            "@/hooks/usePage": [["default", "usePage"]], //Hook
          },
          {
            "@/hooks/useTable": [["default", "useTable"]], //Hook
          },
          {
            "@/utils/formatData": [["default", "FormatData"]], //Hook
          },
          {
            "@/hooks/useModal": [
              ["showModal", "showModal"],
              ["closeModal", "closeModal"],
            ], //Hook
          },
        ],
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
      Icons({
        compiler: "vue3",
        autoInstall: true,
      }),
      createHtmlPlugin({
        inject: {
          data: {
            //获取标题变量
            title: getTarget(mode, "VITE_APP_TITLE"),
          },
        },
      }),
    ],
    resolve: {
      alias: {
        "@": resolve(__dirname, "src"), // 路径别名
      },
      extensions: [
        ".mjs",
        ".js",
        ".ts",
        "vue",
        ".jsx",
        ".tsx",
        ".json",
        ".scss",
        "/index.vue",
        "/index.ts",
      ], // 使用路径别名时想要省略的后缀名，可以自己 增减
    },
    server: {
      host: true,
      port: 80,
      // 设置代理
      proxy: {
        "/video-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8082`, //这里填入你要请求的接口的前缀
          changeOrigin: true, //是否跨域
          rewrite: (path) => path.replace(/^\/video-api/, ""),
        },
        "/system-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8080`, //这里填入你要请求的接口的前缀
          changeOrigin: true, //是否跨域
          rewrite: (path) => path.replace(/^\/system-api/, ""),
        },
        "/file-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8081`, //这里填入你要请求的接口的前缀
          changeOrigin: true, //是否跨域
          rewrite: (path) => path.replace(/^\/file-api/, ""),
        },
      },
    },
  });
};

```

## 5.配置 tsconfig.json

```ts
{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "module": "ESNext",
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "baseUrl": ".", // 用于设置解析非相对模块名称的基本目录，相对模块不会受到baseUrl的影响
    "paths": {
      // 用于设置模块名到基于baseUrl的路径映射
      "@/*": ["src/*"]
    },
    "skipLibCheck": true,
    /* Bundler mode */
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "resolveJsonModule": true,
    "isolatedModules": true,
    "noEmit": true,
    "jsx": "preserve",
    /* Linting */
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "noFallthroughCasesInSwitch": true
  },
  "types": ["pinia-plugin-persist"],
  "include": [
    "src/**/*.ts",
    "src/**/*.d.ts",
    "src/**/*.tsx",
    "src/**/*.vue",
    "auto-imports.d.ts",
    "components.d.ts",
    "src/hooks/useModal.tsx"
  ],
  "references": [{ "path": "./tsconfig.node.json" }]
}

```

## 6.TS配置

### 6.1vue.d.ts

```ts
declare module "*.vue" {
  import { ComponentOptions } from "vue";
  const componentOptions: ComponentOptions;
  export default componentOptions;
}
```

### 6.vite-env.d.ts

```ts
/// <reference types="vite/client" />
declare module "element-plus/dist/locale/zh-cn.mjs";
```

## 7.路由

###  7.1.创建      router/index.ts

```ts
import type { App } from "vue";
import type { RouteRecordRaw } from "vue-router";
import { createRouter, createWebHashHistory } from "vue-router";

//其他独立的路由在这里拼接
const baseRoute: any = [
  {
    path: "/",
    redirect: "/dashboard",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/system/login/index.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/views/system/404/index.vue"),
  },
];

export const menuRoute: any = [];

const Routes = [...menuRoute, ...baseRoute];

// app router
export const router = createRouter({
  history: createWebHashHistory(),
  routes: Routes as RouteRecordRaw[],
});

export function setupRouter(app: App<Element>) {
  app.use(router);
}
```

### 7.2.       main   中引入 router

```ts
import { createApp } from "vue";
import App from "./App.vue";

import { setupRouter } from "@/router";

const app = createApp(App);

async function create() {
  //router
  setupRouter(app);
  //app
  app.mount("#app");
}

create();
```

### 7.3App.vue中引入路由

```vue
<template>
  <RouterView />
</template>
```

## 8.*axios*

### 8.1.创建 utils/request.ts

```ts
import axios from "axios";
import { getToken } from "./auth";
import { useUserStore } from "@/store/user";

const service = axios.create({
  baseURL: "",
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
```

### 8.2.创建 utils/auth.ts

```ts
import Cookies from "js-cookie";

const TokenKey = "Token";

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token: string) {
  return Cookies.set(TokenKey, token);
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}
```

### 8.3.创建环境变量                              .env.dev

```ts
# 开发环境配置
ENV = dev

# 页面标题
VITE_APP_TITLE = 后台系统

#api地址
VITE_APP_BASE_API = http://localhost:8080

#文件服务器地址
VITE_APP_FILE_API = http://localhost:8080/files

#web跳转的URL
VITE_APP_WEB_URL = http://localhost:80 

# 访问路径
PUBLIC_PATH = /web-system
```

### 8.4.package.json

```json
  "scripts": {
	"dev": "vite --mode dev",
    "sit": "vite --mode sit",
    "uat": "vite --mode uat",
    "prod": "vite --mode prod",
    "sh:prod": "bash ./build/build.sh prod prod",
    "build:dev": "vue-tsc && vite build --mode dev",
    "build:sit": "vue-tsc && vite build --mode sit",
    "build:uat": "vue-tsc && vite build --mode uat",
    "build:prod": "vue-tsc && vite build --mode prod",
    "preview": "vite preview",
    "docs:dev": "vitepress dev docs",
    "docs:build": "vitepress build docs",
    "docs:preview": "vitepress preview docs"
  },
```

### 8.5创建apis/user.ts

```ts
import request from "@/utils/request";

export function userLogin(params: any) {
  return request({
    url: "/user/login",
    method: "get",
    params,
  });
}
```

## 9.SVG

### 9.1.main引入SVG

```ts
//svg
import "virtual:svg-icons-register";
```

### 9.2创建组件  components/SvgIcon/index.vue

```vue
<template>
  <svg aria-hidden="true" class="svg-icon">
    <use :xlink:href="symbolId" :fill="color" />
  </svg>
</template>

<script lang="ts" setup>
import { computed } from "vue";

const props = defineProps({
  // 使用的svg图标名称，也就是svg文件名
  name: {
    type: String,
    required: true,
  },
  prefix: {
    type: String,
    default: "icon",
  },
  color: {
    type: String,
    default: "#333",
  },
});

const symbolId = computed(() => `#${props.prefix}-${props.name}`);
</script>

<style lang="scss" scoped>
.svg-icon {
  width: 16px;
  height: 16px;
  color: #333;
  fill: currentColor;
}
</style>

```

### 9.3.全局引入组件components/index.ts

```ts
import SvgIcon from "./SvgIcon/index.vue";

function setupComponents(app: any) {
  app.component("SvgIcon", SvgIcon);

  return app;
}

export default setupComponents;
```

mian.ts

```ts
import { createApp } from "vue";
import App from "./App.vue";
//svg
import "virtual:svg-icons-register";
//components
import setupComponents from "@/components/index";
//router
import { setupRouter } from "@/router";

const app = createApp(App);

async function create() {
  //router
  setupRouter(app);
  //组成全局组件
  setupComponents(app);
  //app
  app.mount("#app");
}

create();
```

## 10.pinia

### 10.1.main引入pinia

```ts
//pinia
import { createPinia } from "pinia";

 //pinia
  app.use(createPinia());
```

### 10.2.创建store/user.ts

```ts
import { userLogin } from "@/apis/user";
import { defineStore } from "pinia";
import { getToken, setToken } from "@/utils/auth";

export const useUserStore = defineStore({
  id: "user", // id必填，且需要唯一
  state: () => {
    return {
      userInfo: {} as any,
      token: getToken(),
      permission: [], //权限
      asyncRoutes: [] as any, //路由
    };
  },
  actions: {
    // 登录
    login(loginInfo: any) {
      return userLogin({ ...loginInfo, loginSystem: "videoWeb" }).then(
        (response: any) => {
          if (response.code === 200) {
            this.token = response.data.token;
            this.userInfo = response.data.userInfo;
            this.asyncRoutes = response.data.menuList;

            setToken(response.data.token);

            return response.data;
          } else {
            return false;
          }
        }
      );
    },
  },
});

```

### 10.3创建icon

```
将svg放在icon下
```

## 11.styles样式

### 11.1.创建styles/index

```scss
@import "./reset.scss";
```

### 11.2.创建styles/reset

```scss

```

### 11.3引入main

```ts
import { createApp } from "vue";
import App from "./App.vue";
//svg
import "virtual:svg-icons-register";
//components
import setupComponents from "@/components";
//router
import { setupRouter } from "@/router";
//pinia
import { createPinia } from "pinia";
//style
import "@/styles/index.scss";

const app = createApp(App);

async function create() {
  //router
  setupRouter(app);
  //组成全局组件
  setupComponents(app);
  //pinia
  app.use(createPinia());
  //app
  app.mount("#app");
}

create();
```

## 12.权限路由 permission.ts

```ts
import { router } from "@/router";

router.beforeEach(async (_to, _from, next) => {
  next();
});
```

## 13.多语言

### 13.1创建language/index.ts

```ts
import { createI18n } from "vue-i18n";
// 从语言包文件中导入语言包对象
import zh from "./locales/zh.json";
import en from "./locales/en.json";
import jp from "./locales/jp.json";
import kr from "./locales/kr.json";

const i18n = createI18n({
  //这里是语种的持久化，刷新也存在
  locale: localStorage.getItem("language") || "zh", // 默认是中文
  fallbackLocale: "zh", // 语言切换的时候是英文
  globalInjection: true, //全局配置
  legacy: false, //vue3写法
  messages: { en, zh, jp, kr }, //需要做国际化的语种
});

export default i18n;
```

### 13.2.main引入

```ts
import { createApp } from "vue";
import App from "./App.vue";
//svg
import "virtual:svg-icons-register";
//components
import setupComponents from "@/components";
//router
import { setupRouter } from "@/router";
//pinia
import { createPinia } from "pinia";
//style
import "@/styles/index.scss";
//language
import i18n from "@/language/index";

const app = createApp(App);

async function create() {
  //router
  setupRouter(app);
  //组成全局组件
  setupComponents(app);
  //翻译
  app.use(i18n);
  //pinia
  app.use(createPinia());
  //app
  app.mount("#app");
}

create();
```

### 13.3.插件.vscode/settings.json

```ts
{
  "i18n-ally.localesPaths": ["src/language/locales"],
  "i18n-ally.keystyle": "nested",
  "i18n-ally.sortKeys": true,
  "i18n-ally.enabledParsers": ["ts", "json"],
  "i18n-ally.sourceLanguage": "en",
  "i18n-ally.displayLanguage": "zh",
  "i18n-ally.enabledFrameworks": ["vue", "react"]
}
```

## 14.*VXETable*

### 14.1.创建plugins/vxe-table.ts

```ts
import VXETable from "vxe-table";
import "vxe-table/lib/style.css";
import type { App } from "vue";

function useTable(app: App) {
  app.use(VXETable);
}

export default useTable;
```

### 14.2..main中引入

```ts
import { createApp } from "vue";
import App from "./App.vue";
//svg
import "virtual:svg-icons-register";
//components
import setupComponents from "@/components";
//router
import { setupRouter } from "@/router";
//pinia
import { createPinia } from "pinia";
//style
import "@/styles/index.scss";
//language
import i18n from "@/language/index";
//utils
import "@/utils/index";
//permission
import "@/permission";
//VXETable
import useTable from "@/plugins/vxe-table";

const app = createApp(App);

async function create() {
  //router
  setupRouter(app);
  //组成全局组件
  setupComponents(app);
  //VXETable
  useTable(app);
  //翻译
  app.use(i18n);
  //pinia
  app.use(createPinia());
  //app
  app.mount("#app");
}

create();

```

