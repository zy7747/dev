// vite.config.ts
import { defineConfig, loadEnv } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite@5.1.4_sass@1.71.1/node_modules/vite/dist/node/index.js";
import { createHtmlPlugin } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-html@3.2.2_vite@5.1.4/node_modules/vite-plugin-html/dist/index.mjs";
import vue from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/@vitejs+plugin-vue@5.0.4_vite@5.1.4_vue@3.4.19/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import svgLoader from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-svg-loader@5.1.0_vue@3.4.19/node_modules/vite-svg-loader/index.js";
import WindiCSS from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-windicss@1.9.3_vite@5.1.4/node_modules/vite-plugin-windicss/dist/index.mjs";
import AutoImport from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-auto-import@0.17.5_@vueuse+core@10.8.0/node_modules/unplugin-auto-import/dist/vite.js";
import Components from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/vite.js";
import Icons from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-icons@0.18.5/node_modules/unplugin-icons/dist/vite.js";
import { ElementPlusResolver } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/resolvers.js";
import VueDevTools from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-vue-devtools@7.0.24_vite@5.1.4_vue@3.4.19/node_modules/vite-plugin-vue-devtools/dist/vite.mjs";
import vueJsx from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/@vitejs+plugin-vue-jsx@4.0.0_vite@5.1.4_vue@3.4.19/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
import { createSvgIconsPlugin } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-svg-icons@2.0.1_vite@5.1.4/node_modules/vite-plugin-svg-icons/dist/index.mjs";
import { resolve } from "path";
var __vite_injected_original_dirname = "C:\\Users\\zy\\Desktop\\project\\dev\\web";
var getTarget = (mode, target) => {
  return loadEnv(mode, process.cwd())[target];
};
var vite_config_default = ({ mode }) => {
  const env = loadEnv(mode, process.cwd());
  return defineConfig({
    base: "/system",
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
          resolve(process.cwd(), "src/icons/system")
        ],
        // 指定symbolId格式（这里的配置与6.2步骤中的引入svg组件的name配置项写法有关）
        symbolId: "[name]"
      }),
      AutoImport({
        imports: [
          "vue",
          "vue-router",
          "pinia",
          {
            "@/plugins/download": [["default", "Download"]]
            //全局下载
          },
          {
            "@/plugins/service": [["default", "Service"]]
            //api
          },
          {
            "@/language/index": [["default", "$t"]]
            //翻译
          },
          {
            "@/dict": [
              ["Dict", "Dict"],
              ["DictService", "DictService"],
              ["useDict", "useDict"],
              ["getDictTag", "getDictTag"],
              ["getDictName", "getDictName"]
            ]
            //字典
          },
          {
            "@/hooks/tips": [
              ["removeSuccess", "removeSuccess"],
              ["submitSuccess", "submitSuccess"],
              ["importSuccess", "importSuccess"],
              ["exportSuccess", "exportSuccess"]
            ]
            //提示
          },
          {
            "@/hooks/usePage": [["default", "usePage"]]
            //Hook
          },
          {
            "@/hooks/useTable": [["default", "useTable"]]
            //Hook
          },
          {
            "@/hooks/useModal": [
              ["showModal", "showModal"],
              ["initModal", "initModal"]
            ]
            //Hook
          }
        ],
        resolvers: [ElementPlusResolver()]
      }),
      Components({
        resolvers: [ElementPlusResolver()]
      }),
      Icons({
        compiler: "vue3",
        autoInstall: true
      }),
      createHtmlPlugin({
        inject: {
          data: {
            //获取标题变量
            title: getTarget(mode, "VITE_APP_TITLE")
          }
        }
      })
    ],
    resolve: {
      alias: {
        "@": resolve(__vite_injected_original_dirname, "src")
        // 路径别名
      },
      extensions: [
        ".mjs",
        ".js",
        ".ts",
        "vue",
        ".jsx",
        ".tsx",
        ".json",
        ".scss"
      ]
      // 使用路径别名时想要省略的后缀名，可以自己 增减
    },
    server: {
      host: true,
      port: 80,
      // 设置代理
      proxy: {
        "/video-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8082`,
          //这里填入你要请求的接口的前缀
          changeOrigin: true,
          //是否跨域
          rewrite: (path) => path.replace(/^\/video-api/, "")
        },
        "/system-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8080`,
          //这里填入你要请求的接口的前缀
          changeOrigin: true,
          //是否跨域
          rewrite: (path) => path.replace(/^\/system-api/, "")
        },
        "/file-api": {
          // '/api'是代理标识，用于告诉node，url前面是/api的就是使用代理的
          target: `${env.VITE_APP_BASE_DOMAIN}:8081`,
          //这里填入你要请求的接口的前缀
          changeOrigin: true,
          //是否跨域
          rewrite: (path) => path.replace(/^\/file-api/, "")
        }
      }
    }
  });
};
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFx6eVxcXFxEZXNrdG9wXFxcXHByb2plY3RcXFxcZGV2XFxcXHdlYlwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiQzpcXFxcVXNlcnNcXFxcenlcXFxcRGVza3RvcFxcXFxwcm9qZWN0XFxcXGRldlxcXFx3ZWJcXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0M6L1VzZXJzL3p5L0Rlc2t0b3AvcHJvamVjdC9kZXYvd2ViL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnLCBsb2FkRW52IH0gZnJvbSBcInZpdGVcIjtcclxuaW1wb3J0IHsgY3JlYXRlSHRtbFBsdWdpbiB9IGZyb20gXCJ2aXRlLXBsdWdpbi1odG1sXCI7XHJcbmltcG9ydCB2dWUgZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZVwiO1xyXG5pbXBvcnQgc3ZnTG9hZGVyIGZyb20gXCJ2aXRlLXN2Zy1sb2FkZXJcIjtcclxuaW1wb3J0IFdpbmRpQ1NTIGZyb20gXCJ2aXRlLXBsdWdpbi13aW5kaWNzc1wiO1xyXG5pbXBvcnQgQXV0b0ltcG9ydCBmcm9tIFwidW5wbHVnaW4tYXV0by1pbXBvcnQvdml0ZVwiO1xyXG5pbXBvcnQgQ29tcG9uZW50cyBmcm9tIFwidW5wbHVnaW4tdnVlLWNvbXBvbmVudHMvdml0ZVwiO1xyXG5pbXBvcnQgSWNvbnMgZnJvbSBcInVucGx1Z2luLWljb25zL3ZpdGVcIjtcclxuaW1wb3J0IHsgRWxlbWVudFBsdXNSZXNvbHZlciB9IGZyb20gXCJ1bnBsdWdpbi12dWUtY29tcG9uZW50cy9yZXNvbHZlcnNcIjtcclxuaW1wb3J0IFZ1ZURldlRvb2xzIGZyb20gXCJ2aXRlLXBsdWdpbi12dWUtZGV2dG9vbHNcIjtcclxuaW1wb3J0IHZ1ZUpzeCBmcm9tIFwiQHZpdGVqcy9wbHVnaW4tdnVlLWpzeFwiOyAvLyBcdTZERkJcdTUyQTBcdThGRDlcdTRFMDBcdTUzRTVcclxuaW1wb3J0IHsgY3JlYXRlU3ZnSWNvbnNQbHVnaW4gfSBmcm9tIFwidml0ZS1wbHVnaW4tc3ZnLWljb25zXCI7XHJcbmltcG9ydCB7IHJlc29sdmUgfSBmcm9tIFwicGF0aFwiO1xyXG4vLyBpbXBvcnQgYmFzaWNTc2wgZnJvbSBcIkB2aXRlanMvcGx1Z2luLWJhc2ljLXNzbFwiO1xyXG5jb25zdCBnZXRUYXJnZXQgPSAobW9kZTogc3RyaW5nLCB0YXJnZXQ6IHN0cmluZykgPT4ge1xyXG4gIHJldHVybiBsb2FkRW52KG1vZGUsIHByb2Nlc3MuY3dkKCkpW3RhcmdldF07XHJcbn07XHJcblxyXG5leHBvcnQgZGVmYXVsdCAoeyBtb2RlIH06IGFueSkgPT4ge1xyXG4gIGNvbnN0IGVudiA9IGxvYWRFbnYobW9kZSwgcHJvY2Vzcy5jd2QoKSk7XHJcbiAgcmV0dXJuIGRlZmluZUNvbmZpZyh7XHJcbiAgICBiYXNlOiBcIi9zeXN0ZW1cIixcclxuICAgIHBsdWdpbnM6IFtcclxuICAgICAgVnVlRGV2VG9vbHMoKSxcclxuICAgICAgdnVlKCksXHJcbiAgICAgIHZ1ZUpzeCgpLFxyXG4gICAgICAvLyBiYXNpY1NzbCgpLFxyXG4gICAgICBXaW5kaUNTUygpLFxyXG4gICAgICBzdmdMb2FkZXIoKSxcclxuICAgICAgY3JlYXRlU3ZnSWNvbnNQbHVnaW4oe1xyXG4gICAgICAgIGljb25EaXJzOiBbXHJcbiAgICAgICAgICByZXNvbHZlKHByb2Nlc3MuY3dkKCksIFwic3JjL2ljb25zL2FwcFwiKSxcclxuICAgICAgICAgIHJlc29sdmUocHJvY2Vzcy5jd2QoKSwgXCJzcmMvaWNvbnMvZmxhZ1wiKSxcclxuICAgICAgICAgIHJlc29sdmUocHJvY2Vzcy5jd2QoKSwgXCJzcmMvaWNvbnMvc3lzdGVtXCIpLFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgLy8gXHU2MzA3XHU1QjlBc3ltYm9sSWRcdTY4M0NcdTVGMEZcdUZGMDhcdThGRDlcdTkxQ0NcdTc2ODRcdTkxNERcdTdGNkVcdTRFMEU2LjJcdTZCNjVcdTlBQTRcdTRFMkRcdTc2ODRcdTVGMTVcdTUxNjVzdmdcdTdFQzRcdTRFRjZcdTc2ODRuYW1lXHU5MTREXHU3RjZFXHU5ODc5XHU1MTk5XHU2Q0Q1XHU2NzA5XHU1MTczXHVGRjA5XHJcbiAgICAgICAgc3ltYm9sSWQ6IFwiW25hbWVdXCIsXHJcbiAgICAgIH0pLFxyXG4gICAgICBBdXRvSW1wb3J0KHtcclxuICAgICAgICBpbXBvcnRzOiBbXHJcbiAgICAgICAgICBcInZ1ZVwiLFxyXG4gICAgICAgICAgXCJ2dWUtcm91dGVyXCIsXHJcbiAgICAgICAgICBcInBpbmlhXCIsXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9wbHVnaW5zL2Rvd25sb2FkXCI6IFtbXCJkZWZhdWx0XCIsIFwiRG93bmxvYWRcIl1dLCAvL1x1NTE2OFx1NUM0MFx1NEUwQlx1OEY3RFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL3BsdWdpbnMvc2VydmljZVwiOiBbW1wiZGVmYXVsdFwiLCBcIlNlcnZpY2VcIl1dLCAvL2FwaVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2xhbmd1YWdlL2luZGV4XCI6IFtbXCJkZWZhdWx0XCIsIFwiJHRcIl1dLCAvL1x1N0ZGQlx1OEJEMVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2RpY3RcIjogW1xyXG4gICAgICAgICAgICAgIFtcIkRpY3RcIiwgXCJEaWN0XCJdLFxyXG4gICAgICAgICAgICAgIFtcIkRpY3RTZXJ2aWNlXCIsIFwiRGljdFNlcnZpY2VcIl0sXHJcbiAgICAgICAgICAgICAgW1widXNlRGljdFwiLCBcInVzZURpY3RcIl0sXHJcbiAgICAgICAgICAgICAgW1wiZ2V0RGljdFRhZ1wiLCBcImdldERpY3RUYWdcIl0sXHJcbiAgICAgICAgICAgICAgW1wiZ2V0RGljdE5hbWVcIiwgXCJnZXREaWN0TmFtZVwiXSxcclxuICAgICAgICAgICAgXSwgLy9cdTVCNTdcdTUxNzhcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9ob29rcy90aXBzXCI6IFtcclxuICAgICAgICAgICAgICBbXCJyZW1vdmVTdWNjZXNzXCIsIFwicmVtb3ZlU3VjY2Vzc1wiXSxcclxuICAgICAgICAgICAgICBbXCJzdWJtaXRTdWNjZXNzXCIsIFwic3VibWl0U3VjY2Vzc1wiXSxcclxuICAgICAgICAgICAgICBbXCJpbXBvcnRTdWNjZXNzXCIsIFwiaW1wb3J0U3VjY2Vzc1wiXSxcclxuICAgICAgICAgICAgICBbXCJleHBvcnRTdWNjZXNzXCIsIFwiZXhwb3J0U3VjY2Vzc1wiXSxcclxuICAgICAgICAgICAgXSwgLy9cdTYzRDBcdTc5M0FcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9ob29rcy91c2VQYWdlXCI6IFtbXCJkZWZhdWx0XCIsIFwidXNlUGFnZVwiXV0sIC8vSG9va1xyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2hvb2tzL3VzZVRhYmxlXCI6IFtbXCJkZWZhdWx0XCIsIFwidXNlVGFibGVcIl1dLCAvL0hvb2tcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9ob29rcy91c2VNb2RhbFwiOiBbXHJcbiAgICAgICAgICAgICAgW1wic2hvd01vZGFsXCIsIFwic2hvd01vZGFsXCJdLFxyXG4gICAgICAgICAgICAgIFtcImluaXRNb2RhbFwiLCBcImluaXRNb2RhbFwiXSxcclxuICAgICAgICAgICAgXSwgLy9Ib29rXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgcmVzb2x2ZXJzOiBbRWxlbWVudFBsdXNSZXNvbHZlcigpXSxcclxuICAgICAgfSksXHJcbiAgICAgIENvbXBvbmVudHMoe1xyXG4gICAgICAgIHJlc29sdmVyczogW0VsZW1lbnRQbHVzUmVzb2x2ZXIoKV0sXHJcbiAgICAgIH0pLFxyXG4gICAgICBJY29ucyh7XHJcbiAgICAgICAgY29tcGlsZXI6IFwidnVlM1wiLFxyXG4gICAgICAgIGF1dG9JbnN0YWxsOiB0cnVlLFxyXG4gICAgICB9KSxcclxuICAgICAgY3JlYXRlSHRtbFBsdWdpbih7XHJcbiAgICAgICAgaW5qZWN0OiB7XHJcbiAgICAgICAgICBkYXRhOiB7XHJcbiAgICAgICAgICAgIC8vXHU4M0I3XHU1M0Q2XHU2ODA3XHU5ODk4XHU1M0Q4XHU5MUNGXHJcbiAgICAgICAgICAgIHRpdGxlOiBnZXRUYXJnZXQobW9kZSwgXCJWSVRFX0FQUF9USVRMRVwiKSxcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgfSxcclxuICAgICAgfSksXHJcbiAgICBdLFxyXG4gICAgcmVzb2x2ZToge1xyXG4gICAgICBhbGlhczoge1xyXG4gICAgICAgIFwiQFwiOiByZXNvbHZlKF9fZGlybmFtZSwgXCJzcmNcIiksIC8vIFx1OERFRlx1NUY4NFx1NTIyQlx1NTQwRFxyXG4gICAgICB9LFxyXG4gICAgICBleHRlbnNpb25zOiBbXHJcbiAgICAgICAgXCIubWpzXCIsXHJcbiAgICAgICAgXCIuanNcIixcclxuICAgICAgICBcIi50c1wiLFxyXG4gICAgICAgIFwidnVlXCIsXHJcbiAgICAgICAgXCIuanN4XCIsXHJcbiAgICAgICAgXCIudHN4XCIsXHJcbiAgICAgICAgXCIuanNvblwiLFxyXG4gICAgICAgIFwiLnNjc3NcIixcclxuICAgICAgXSwgLy8gXHU0RjdGXHU3NTI4XHU4REVGXHU1Rjg0XHU1MjJCXHU1NDBEXHU2NUY2XHU2MEYzXHU4OTgxXHU3NzAxXHU3NTY1XHU3Njg0XHU1NDBFXHU3RjAwXHU1NDBEXHVGRjBDXHU1M0VGXHU0RUU1XHU4MUVBXHU1REYxIFx1NTg5RVx1NTFDRlxyXG4gICAgfSxcclxuICAgIHNlcnZlcjoge1xyXG4gICAgICBob3N0OiB0cnVlLFxyXG4gICAgICBwb3J0OiA4MCxcclxuICAgICAgLy8gXHU4QkJFXHU3RjZFXHU0RUUzXHU3NDA2XHJcbiAgICAgIHByb3h5OiB7XHJcbiAgICAgICAgXCIvdmlkZW8tYXBpXCI6IHtcclxuICAgICAgICAgIC8vICcvYXBpJ1x1NjYyRlx1NEVFM1x1NzQwNlx1NjgwN1x1OEJDNlx1RkYwQ1x1NzUyOFx1NEU4RVx1NTQ0QVx1OEJDOW5vZGVcdUZGMEN1cmxcdTUyNERcdTk3NjJcdTY2MkYvYXBpXHU3Njg0XHU1QzMxXHU2NjJGXHU0RjdGXHU3NTI4XHU0RUUzXHU3NDA2XHU3Njg0XHJcbiAgICAgICAgICB0YXJnZXQ6IGAke2Vudi5WSVRFX0FQUF9CQVNFX0RPTUFJTn06ODA4MmAsIC8vXHU4RkQ5XHU5MUNDXHU1ODZCXHU1MTY1XHU0RjYwXHU4OTgxXHU4QkY3XHU2QzQyXHU3Njg0XHU2M0E1XHU1M0UzXHU3Njg0XHU1MjREXHU3RjAwXHJcbiAgICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vXHU2NjJGXHU1NDI2XHU4REU4XHU1N0RGXHJcbiAgICAgICAgICByZXdyaXRlOiAocGF0aCkgPT4gcGF0aC5yZXBsYWNlKC9eXFwvdmlkZW8tYXBpLywgXCJcIiksXHJcbiAgICAgICAgfSxcclxuICAgICAgICBcIi9zeXN0ZW0tYXBpXCI6IHtcclxuICAgICAgICAgIC8vICcvYXBpJ1x1NjYyRlx1NEVFM1x1NzQwNlx1NjgwN1x1OEJDNlx1RkYwQ1x1NzUyOFx1NEU4RVx1NTQ0QVx1OEJDOW5vZGVcdUZGMEN1cmxcdTUyNERcdTk3NjJcdTY2MkYvYXBpXHU3Njg0XHU1QzMxXHU2NjJGXHU0RjdGXHU3NTI4XHU0RUUzXHU3NDA2XHU3Njg0XHJcbiAgICAgICAgICB0YXJnZXQ6IGAke2Vudi5WSVRFX0FQUF9CQVNFX0RPTUFJTn06ODA4MGAsIC8vXHU4RkQ5XHU5MUNDXHU1ODZCXHU1MTY1XHU0RjYwXHU4OTgxXHU4QkY3XHU2QzQyXHU3Njg0XHU2M0E1XHU1M0UzXHU3Njg0XHU1MjREXHU3RjAwXHJcbiAgICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vXHU2NjJGXHU1NDI2XHU4REU4XHU1N0RGXHJcbiAgICAgICAgICByZXdyaXRlOiAocGF0aCkgPT4gcGF0aC5yZXBsYWNlKC9eXFwvc3lzdGVtLWFwaS8sIFwiXCIpLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAgXCIvZmlsZS1hcGlcIjoge1xyXG4gICAgICAgICAgLy8gJy9hcGknXHU2NjJGXHU0RUUzXHU3NDA2XHU2ODA3XHU4QkM2XHVGRjBDXHU3NTI4XHU0RThFXHU1NDRBXHU4QkM5bm9kZVx1RkYwQ3VybFx1NTI0RFx1OTc2Mlx1NjYyRi9hcGlcdTc2ODRcdTVDMzFcdTY2MkZcdTRGN0ZcdTc1MjhcdTRFRTNcdTc0MDZcdTc2ODRcclxuICAgICAgICAgIHRhcmdldDogYCR7ZW52LlZJVEVfQVBQX0JBU0VfRE9NQUlOfTo4MDgxYCwgLy9cdThGRDlcdTkxQ0NcdTU4NkJcdTUxNjVcdTRGNjBcdTg5ODFcdThCRjdcdTZDNDJcdTc2ODRcdTYzQTVcdTUzRTNcdTc2ODRcdTUyNERcdTdGMDBcclxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSwgLy9cdTY2MkZcdTU0MjZcdThERThcdTU3REZcclxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC9maWxlLWFwaS8sIFwiXCIpLFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0sXHJcbiAgICB9LFxyXG4gIH0pO1xyXG59O1xyXG4iXSwKICAibWFwcGluZ3MiOiAiO0FBQXlTLFNBQVMsY0FBYyxlQUFlO0FBQy9VLFNBQVMsd0JBQXdCO0FBQ2pDLE9BQU8sU0FBUztBQUNoQixPQUFPLGVBQWU7QUFDdEIsT0FBTyxjQUFjO0FBQ3JCLE9BQU8sZ0JBQWdCO0FBQ3ZCLE9BQU8sZ0JBQWdCO0FB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
