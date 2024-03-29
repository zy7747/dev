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
import { createSvgIconsPlugin } from "vite-plugin-svg-icons";
import { resolve } from "path";

const getTarget = (mode: string, target: string) => {
  return loadEnv(mode, process.cwd())[target];
};

export default ({ mode }: any) =>
  defineConfig({
    base: "/system/",
    plugins: [
      VueDevTools(),
      vue(),
      WindiCSS(),
      svgLoader(),
      createSvgIconsPlugin({
        iconDirs: [resolve("./src/icons")],
        // 指定symbolId格式（这里的配置与6.2步骤中的引入svg组件的name配置项写法有关）
        symbolId: "icon-[dir]-[name]",
      }),
      AutoImport({
        imports: [
          "vue",
          "vue-router",
          "pinia",
          {
            "@/plugins/download": [["default", "Download"]],
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
            ], //字典
          },
          {
            "@/hooks/usePage": [["default", "usePage"]], //Hook
          },
          {
            "@/hooks/useDict": [["default", "useDict"]], //Hook
          },
          {
            "@/hooks/useTable": [["default", "useTable"]],
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
      // Listening on all local IPs
      host: true,
      port: 88,
      // 设置代理
    },
  });
