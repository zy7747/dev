import { defineConfig, loadEnv } from "vite";
import { createHtmlPlugin } from "vite-plugin-html";
import vue from "@vitejs/plugin-vue";
import svgLoader from "vite-svg-loader";
import WindiCSS from "vite-plugin-windicss";
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import Icons from "unplugin-icons/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";
import VueDevTools from "vite-plugin-vue-devtools";
import vueJsx from "@vitejs/plugin-vue-jsx"; // 添加这一句
import { createSvgIconsPlugin } from "vite-plugin-svg-icons";
import { resolve } from "path";
// import basicSsl from "@vitejs/plugin-basic-ssl";
const getTarget = (mode: string, target: string) => {
  return loadEnv(mode, process.cwd())[target];
};

export default ({ mode }: any) => {
  const env = loadEnv(mode, process.cwd());
  return defineConfig({
    base: "/",
    plugins: [
      VueDevTools(),
      vue(),
      vueJsx(),
      // basicSsl(),
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
            ], //提示
          },
          {
            "@/hooks/usePage": [["default", "usePage"]], //Hook
          },
          {
            "@/hooks/useTable": [["default", "useTable"]], //Hook
          },
          {
            "@/hooks/useModal": [["showModal", "showModal"]], //Hook
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
      extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".scss"], // 使用路径别名时想要省略的后缀名，可以自己 增减
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
