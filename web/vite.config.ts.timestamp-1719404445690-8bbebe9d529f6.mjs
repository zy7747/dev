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
              ["useDict", "useDict"]
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
            "@/hooks/useModal": [["showModal", "showModal"]]
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
      extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".scss"]
      // 使用路径别名时想要省略的后缀名，可以自己 增减
    },
    server: {
      host: true,
      port: 8e3,
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
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFx6eVxcXFxEZXNrdG9wXFxcXHByb2plY3RcXFxcZGV2XFxcXHdlYlwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiQzpcXFxcVXNlcnNcXFxcenlcXFxcRGVza3RvcFxcXFxwcm9qZWN0XFxcXGRldlxcXFx3ZWJcXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0M6L1VzZXJzL3p5L0Rlc2t0b3AvcHJvamVjdC9kZXYvd2ViL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnLCBsb2FkRW52IH0gZnJvbSBcInZpdGVcIjtcclxuaW1wb3J0IHsgY3JlYXRlSHRtbFBsdWdpbiB9IGZyb20gXCJ2aXRlLXBsdWdpbi1odG1sXCI7XHJcbmltcG9ydCB2dWUgZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZVwiO1xyXG5pbXBvcnQgc3ZnTG9hZGVyIGZyb20gXCJ2aXRlLXN2Zy1sb2FkZXJcIjtcclxuaW1wb3J0IFdpbmRpQ1NTIGZyb20gXCJ2aXRlLXBsdWdpbi13aW5kaWNzc1wiO1xyXG5pbXBvcnQgQXV0b0ltcG9ydCBmcm9tIFwidW5wbHVnaW4tYXV0by1pbXBvcnQvdml0ZVwiO1xyXG5pbXBvcnQgQ29tcG9uZW50cyBmcm9tIFwidW5wbHVnaW4tdnVlLWNvbXBvbmVudHMvdml0ZVwiO1xyXG5pbXBvcnQgSWNvbnMgZnJvbSBcInVucGx1Z2luLWljb25zL3ZpdGVcIjtcclxuaW1wb3J0IHsgRWxlbWVudFBsdXNSZXNvbHZlciB9IGZyb20gXCJ1bnBsdWdpbi12dWUtY29tcG9uZW50cy9yZXNvbHZlcnNcIjtcclxuaW1wb3J0IFZ1ZURldlRvb2xzIGZyb20gXCJ2aXRlLXBsdWdpbi12dWUtZGV2dG9vbHNcIjtcclxuaW1wb3J0IHZ1ZUpzeCBmcm9tIFwiQHZpdGVqcy9wbHVnaW4tdnVlLWpzeFwiOyAvLyBcdTZERkJcdTUyQTBcdThGRDlcdTRFMDBcdTUzRTVcclxuaW1wb3J0IHsgY3JlYXRlU3ZnSWNvbnNQbHVnaW4gfSBmcm9tIFwidml0ZS1wbHVnaW4tc3ZnLWljb25zXCI7XHJcbmltcG9ydCB7IHJlc29sdmUgfSBmcm9tIFwicGF0aFwiO1xyXG4vLyBpbXBvcnQgYmFzaWNTc2wgZnJvbSBcIkB2aXRlanMvcGx1Z2luLWJhc2ljLXNzbFwiO1xyXG5jb25zdCBnZXRUYXJnZXQgPSAobW9kZTogc3RyaW5nLCB0YXJnZXQ6IHN0cmluZykgPT4ge1xyXG4gIHJldHVybiBsb2FkRW52KG1vZGUsIHByb2Nlc3MuY3dkKCkpW3RhcmdldF07XHJcbn07XHJcblxyXG5leHBvcnQgZGVmYXVsdCAoeyBtb2RlIH06IGFueSkgPT4ge1xyXG4gIGNvbnN0IGVudiA9IGxvYWRFbnYobW9kZSwgcHJvY2Vzcy5jd2QoKSk7XHJcbiAgcmV0dXJuIGRlZmluZUNvbmZpZyh7XHJcbiAgICBiYXNlOiBcIi9cIixcclxuICAgIHBsdWdpbnM6IFtcclxuICAgICAgVnVlRGV2VG9vbHMoKSxcclxuICAgICAgdnVlKCksXHJcbiAgICAgIHZ1ZUpzeCgpLFxyXG4gICAgICAvLyBiYXNpY1NzbCgpLFxyXG4gICAgICBXaW5kaUNTUygpLFxyXG4gICAgICBzdmdMb2FkZXIoKSxcclxuICAgICAgY3JlYXRlU3ZnSWNvbnNQbHVnaW4oe1xyXG4gICAgICAgIGljb25EaXJzOiBbXHJcbiAgICAgICAgICByZXNvbHZlKHByb2Nlc3MuY3dkKCksIFwic3JjL2ljb25zL2FwcFwiKSxcclxuICAgICAgICAgIHJlc29sdmUocHJvY2Vzcy5jd2QoKSwgXCJzcmMvaWNvbnMvZmxhZ1wiKSxcclxuICAgICAgICAgIHJlc29sdmUocHJvY2Vzcy5jd2QoKSwgXCJzcmMvaWNvbnMvc3lzdGVtXCIpLFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgLy8gXHU2MzA3XHU1QjlBc3ltYm9sSWRcdTY4M0NcdTVGMEZcdUZGMDhcdThGRDlcdTkxQ0NcdTc2ODRcdTkxNERcdTdGNkVcdTRFMEU2LjJcdTZCNjVcdTlBQTRcdTRFMkRcdTc2ODRcdTVGMTVcdTUxNjVzdmdcdTdFQzRcdTRFRjZcdTc2ODRuYW1lXHU5MTREXHU3RjZFXHU5ODc5XHU1MTk5XHU2Q0Q1XHU2NzA5XHU1MTczXHVGRjA5XHJcbiAgICAgICAgc3ltYm9sSWQ6IFwiW25hbWVdXCIsXHJcbiAgICAgIH0pLFxyXG4gICAgICBBdXRvSW1wb3J0KHtcclxuICAgICAgICBpbXBvcnRzOiBbXHJcbiAgICAgICAgICBcInZ1ZVwiLFxyXG4gICAgICAgICAgXCJ2dWUtcm91dGVyXCIsXHJcbiAgICAgICAgICBcInBpbmlhXCIsXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9wbHVnaW5zL2Rvd25sb2FkXCI6IFtbXCJkZWZhdWx0XCIsIFwiRG93bmxvYWRcIl1dLCAvL1x1NTE2OFx1NUM0MFx1NEUwQlx1OEY3RFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL3BsdWdpbnMvc2VydmljZVwiOiBbW1wiZGVmYXVsdFwiLCBcIlNlcnZpY2VcIl1dLCAvL2FwaVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2xhbmd1YWdlL2luZGV4XCI6IFtbXCJkZWZhdWx0XCIsIFwiJHRcIl1dLCAvL1x1N0ZGQlx1OEJEMVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2RpY3RcIjogW1xyXG4gICAgICAgICAgICAgIFtcIkRpY3RcIiwgXCJEaWN0XCJdLFxyXG4gICAgICAgICAgICAgIFtcIkRpY3RTZXJ2aWNlXCIsIFwiRGljdFNlcnZpY2VcIl0sXHJcbiAgICAgICAgICAgICAgW1widXNlRGljdFwiLCBcInVzZURpY3RcIl0sXHJcbiAgICAgICAgICAgIF0sIC8vXHU1QjU3XHU1MTc4XHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdGlwc1wiOiBbXHJcbiAgICAgICAgICAgICAgW1wicmVtb3ZlU3VjY2Vzc1wiLCBcInJlbW92ZVN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wic3VibWl0U3VjY2Vzc1wiLCBcInN1Ym1pdFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wiaW1wb3J0U3VjY2Vzc1wiLCBcImltcG9ydFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wiZXhwb3J0U3VjY2Vzc1wiLCBcImV4cG9ydFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgIF0sIC8vXHU2M0QwXHU3OTNBXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdXNlUGFnZVwiOiBbW1wiZGVmYXVsdFwiLCBcInVzZVBhZ2VcIl1dLCAvL0hvb2tcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9ob29rcy91c2VUYWJsZVwiOiBbW1wiZGVmYXVsdFwiLCBcInVzZVRhYmxlXCJdXSwgLy9Ib29rXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdXNlTW9kYWxcIjogW1tcInNob3dNb2RhbFwiLCBcInNob3dNb2RhbFwiXV0sIC8vSG9va1xyXG4gICAgICAgICAgfSxcclxuICAgICAgICBdLFxyXG4gICAgICAgIHJlc29sdmVyczogW0VsZW1lbnRQbHVzUmVzb2x2ZXIoKV0sXHJcbiAgICAgIH0pLFxyXG4gICAgICBDb21wb25lbnRzKHtcclxuICAgICAgICByZXNvbHZlcnM6IFtFbGVtZW50UGx1c1Jlc29sdmVyKCldLFxyXG4gICAgICB9KSxcclxuICAgICAgSWNvbnMoe1xyXG4gICAgICAgIGNvbXBpbGVyOiBcInZ1ZTNcIixcclxuICAgICAgICBhdXRvSW5zdGFsbDogdHJ1ZSxcclxuICAgICAgfSksXHJcbiAgICAgIGNyZWF0ZUh0bWxQbHVnaW4oe1xyXG4gICAgICAgIGluamVjdDoge1xyXG4gICAgICAgICAgZGF0YToge1xyXG4gICAgICAgICAgICAvL1x1ODNCN1x1NTNENlx1NjgwN1x1OTg5OFx1NTNEOFx1OTFDRlxyXG4gICAgICAgICAgICB0aXRsZTogZ2V0VGFyZ2V0KG1vZGUsIFwiVklURV9BUFBfVElUTEVcIiksXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0pLFxyXG4gICAgXSxcclxuICAgIHJlc29sdmU6IHtcclxuICAgICAgYWxpYXM6IHtcclxuICAgICAgICBcIkBcIjogcmVzb2x2ZShfX2Rpcm5hbWUsIFwic3JjXCIpLCAvLyBcdThERUZcdTVGODRcdTUyMkJcdTU0MERcclxuICAgICAgfSxcclxuICAgICAgZXh0ZW5zaW9uczogW1wiLm1qc1wiLCBcIi5qc1wiLCBcIi50c1wiLCBcIi5qc3hcIiwgXCIudHN4XCIsIFwiLmpzb25cIiwgXCIuc2Nzc1wiXSwgLy8gXHU0RjdGXHU3NTI4XHU4REVGXHU1Rjg0XHU1MjJCXHU1NDBEXHU2NUY2XHU2MEYzXHU4OTgxXHU3NzAxXHU3NTY1XHU3Njg0XHU1NDBFXHU3RjAwXHU1NDBEXHVGRjBDXHU1M0VGXHU0RUU1XHU4MUVBXHU1REYxIFx1NTg5RVx1NTFDRlxyXG4gICAgfSxcclxuICAgIHNlcnZlcjoge1xyXG4gICAgICBob3N0OiB0cnVlLFxyXG4gICAgICBwb3J0OiA4MDAwLFxyXG4gICAgICAvLyBcdThCQkVcdTdGNkVcdTRFRTNcdTc0MDZcclxuICAgICAgcHJveHk6IHtcclxuICAgICAgICBcIi92aWRlby1hcGlcIjoge1xyXG4gICAgICAgICAgLy8gJy9hcGknXHU2NjJGXHU0RUUzXHU3NDA2XHU2ODA3XHU4QkM2XHVGRjBDXHU3NTI4XHU0RThFXHU1NDRBXHU4QkM5bm9kZVx1RkYwQ3VybFx1NTI0RFx1OTc2Mlx1NjYyRi9hcGlcdTc2ODRcdTVDMzFcdTY2MkZcdTRGN0ZcdTc1MjhcdTRFRTNcdTc0MDZcdTc2ODRcclxuICAgICAgICAgIHRhcmdldDogYCR7ZW52LlZJVEVfQVBQX0JBU0VfRE9NQUlOfTo4MDgyYCwgLy9cdThGRDlcdTkxQ0NcdTU4NkJcdTUxNjVcdTRGNjBcdTg5ODFcdThCRjdcdTZDNDJcdTc2ODRcdTYzQTVcdTUzRTNcdTc2ODRcdTUyNERcdTdGMDBcclxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSwgLy9cdTY2MkZcdTU0MjZcdThERThcdTU3REZcclxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC92aWRlby1hcGkvLCBcIlwiKSxcclxuICAgICAgICB9LFxyXG4gICAgICAgIFwiL3N5c3RlbS1hcGlcIjoge1xyXG4gICAgICAgICAgLy8gJy9hcGknXHU2NjJGXHU0RUUzXHU3NDA2XHU2ODA3XHU4QkM2XHVGRjBDXHU3NTI4XHU0RThFXHU1NDRBXHU4QkM5bm9kZVx1RkYwQ3VybFx1NTI0RFx1OTc2Mlx1NjYyRi9hcGlcdTc2ODRcdTVDMzFcdTY2MkZcdTRGN0ZcdTc1MjhcdTRFRTNcdTc0MDZcdTc2ODRcclxuICAgICAgICAgIHRhcmdldDogYCR7ZW52LlZJVEVfQVBQX0JBU0VfRE9NQUlOfTo4MDgwYCwgLy9cdThGRDlcdTkxQ0NcdTU4NkJcdTUxNjVcdTRGNjBcdTg5ODFcdThCRjdcdTZDNDJcdTc2ODRcdTYzQTVcdTUzRTNcdTc2ODRcdTUyNERcdTdGMDBcclxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSwgLy9cdTY2MkZcdTU0MjZcdThERThcdTU3REZcclxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC9zeXN0ZW0tYXBpLywgXCJcIiksXHJcbiAgICAgICAgfSxcclxuICAgICAgICBcIi9maWxlLWFwaVwiOiB7XHJcbiAgICAgICAgICAvLyAnL2FwaSdcdTY2MkZcdTRFRTNcdTc0MDZcdTY4MDdcdThCQzZcdUZGMENcdTc1MjhcdTRFOEVcdTU0NEFcdThCQzlub2RlXHVGRjBDdXJsXHU1MjREXHU5NzYyXHU2NjJGL2FwaVx1NzY4NFx1NUMzMVx1NjYyRlx1NEY3Rlx1NzUyOFx1NEVFM1x1NzQwNlx1NzY4NFxyXG4gICAgICAgICAgdGFyZ2V0OiBgJHtlbnYuVklURV9BUFBfQkFTRV9ET01BSU59OjgwODFgLCAvL1x1OEZEOVx1OTFDQ1x1NTg2Qlx1NTE2NVx1NEY2MFx1ODk4MVx1OEJGN1x1NkM0Mlx1NzY4NFx1NjNBNVx1NTNFM1x1NzY4NFx1NTI0RFx1N0YwMFxyXG4gICAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLCAvL1x1NjYyRlx1NTQyNlx1OERFOFx1NTdERlxyXG4gICAgICAgICAgcmV3cml0ZTogKHBhdGgpID0+IHBhdGgucmVwbGFjZSgvXlxcL2ZpbGUtYXBpLywgXCJcIiksXHJcbiAgICAgICAgfSxcclxuICAgICAgfSxcclxuICAgIH0sXHJcbiAgfSk7XHJcbn07XHJcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBeVMsU0FBUyxjQUFjLGVBQWU7QUFDL1UsU0FBUyx3QkFBd0I7QUFDakMsT0FBTyxTQUFTO0FBQ2hCLE9BQU8sZUFBZTtBQUN0QixPQUFPLGNBQWM7QUFDckIsT0FBTyxnQkFBZ0I7QUFDdkIsT0FBTyxnQkFBZ0I7QUFDdkIsT0FBTyxXQUFXO0FBQ2xCLFNBQVMsMkJBQTJCO0FBQ3BDLE9BQU8saUJBQWlCO0FBQ3hCLE9BQU8sWUFBWTtBQUNuQixTQUFTLDRCQUE0QjtBQUNyQyxTQUFTLGVBQWU7QUFaeEIsSUFBTSxtQ0FBbUM7QUFjekMsSUFBTSxZQUFZLENBQUMsTUFBYyxXQUFtQjtBQUNsRCxTQUFPLFFBQVEsTUFBTSxRQUFRLElBQUksQ0FBQyxFQUFFLE1BQU07QUFDNUM7QUFFQSxJQUFPLHNCQUFRLENBQUMsRUFBRSxLQUFLLE1BQVc7QUFDaEMsUUFBTSxNQUFNLFFBQVEsTUFBTSxRQUFRLElBQUksQ0FBQztBQUN2QyxTQUFPLGFBQWE7QUFBQSxJ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
