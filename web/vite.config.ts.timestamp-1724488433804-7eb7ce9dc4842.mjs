// vite.config.ts
import { defineConfig, loadEnv } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite@5.1.4_sass@1.71.1/node_modules/vite/dist/node/index.js";
import { createHtmlPlugin } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-html@3.2.2_vite@5.1.4/node_modules/vite-plugin-html/dist/index.mjs";
import vue from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/@vitejs+plugin-vue@5.0.4_vite@5.1.4_vue@3.4.19/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import svgLoader from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-svg-loader@5.1.0_vue@3.4.19/node_modules/vite-svg-loader/index.js";
import Icons from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-icons@0.18.5/node_modules/unplugin-icons/dist/vite.js";
import { createSvgIconsPlugin } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-svg-icons@2.0.1_vite@5.1.4/node_modules/vite-plugin-svg-icons/dist/index.mjs";
import WindiCSS from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/vite-plugin-windicss@1.9.3_vite@5.1.4/node_modules/vite-plugin-windicss/dist/index.mjs";
import AutoImport from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-auto-import@0.17.5_@vueuse+core@10.8.0/node_modules/unplugin-auto-import/dist/vite.js";
import Components from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/vite.js";
import { ElementPlusResolver } from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/resolvers.js";
import vueJsx from "file:///C:/Users/zy/Desktop/project/dev/web/node_modules/.pnpm/@vitejs+plugin-vue-jsx@4.0.0_vite@5.1.4_vue@3.4.19/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
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
      vue(),
      vueJsx(),
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
              ["exportSuccess", "exportSuccess"],
              ["validateFail", "validateFail"],
              ["uploadSuccess", "uploadSuccess"]
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
            "@/utils/formatData": [["default", "FormatData"]]
            //Hook
          },
          {
            "@/hooks/useModal": [
              ["showModal", "showModal"],
              ["closeModal", "closeModal"]
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
        ".scss",
        "/index.vue",
        "/index.ts"
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
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFx6eVxcXFxEZXNrdG9wXFxcXHByb2plY3RcXFxcZGV2XFxcXHdlYlwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiQzpcXFxcVXNlcnNcXFxcenlcXFxcRGVza3RvcFxcXFxwcm9qZWN0XFxcXGRldlxcXFx3ZWJcXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0M6L1VzZXJzL3p5L0Rlc2t0b3AvcHJvamVjdC9kZXYvd2ViL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnLCBsb2FkRW52IH0gZnJvbSBcInZpdGVcIjtcclxuaW1wb3J0IHsgY3JlYXRlSHRtbFBsdWdpbiB9IGZyb20gXCJ2aXRlLXBsdWdpbi1odG1sXCI7XHJcbmltcG9ydCB2dWUgZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZVwiO1xyXG5pbXBvcnQgc3ZnTG9hZGVyIGZyb20gXCJ2aXRlLXN2Zy1sb2FkZXJcIjtcclxuaW1wb3J0IEljb25zIGZyb20gXCJ1bnBsdWdpbi1pY29ucy92aXRlXCI7XHJcbmltcG9ydCB7IGNyZWF0ZVN2Z0ljb25zUGx1Z2luIH0gZnJvbSBcInZpdGUtcGx1Z2luLXN2Zy1pY29uc1wiO1xyXG5pbXBvcnQgV2luZGlDU1MgZnJvbSBcInZpdGUtcGx1Z2luLXdpbmRpY3NzXCI7XHJcbmltcG9ydCBBdXRvSW1wb3J0IGZyb20gXCJ1bnBsdWdpbi1hdXRvLWltcG9ydC92aXRlXCI7XHJcbmltcG9ydCBDb21wb25lbnRzIGZyb20gXCJ1bnBsdWdpbi12dWUtY29tcG9uZW50cy92aXRlXCI7XHJcbmltcG9ydCB7IEVsZW1lbnRQbHVzUmVzb2x2ZXIgfSBmcm9tIFwidW5wbHVnaW4tdnVlLWNvbXBvbmVudHMvcmVzb2x2ZXJzXCI7XHJcbmltcG9ydCB2dWVKc3ggZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZS1qc3hcIjsgLy8gXHU2REZCXHU1MkEwXHU4RkQ5XHU0RTAwXHU1M0U1XHJcblxyXG5pbXBvcnQgeyByZXNvbHZlIH0gZnJvbSBcInBhdGhcIjtcclxuY29uc3QgZ2V0VGFyZ2V0ID0gKG1vZGU6IHN0cmluZywgdGFyZ2V0OiBzdHJpbmcpID0+IHtcclxuICByZXR1cm4gbG9hZEVudihtb2RlLCBwcm9jZXNzLmN3ZCgpKVt0YXJnZXRdO1xyXG59O1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgKHsgbW9kZSB9OiBhbnkpID0+IHtcclxuICBjb25zdCBlbnYgPSBsb2FkRW52KG1vZGUsIHByb2Nlc3MuY3dkKCkpO1xyXG4gIHJldHVybiBkZWZpbmVDb25maWcoe1xyXG4gICAgYmFzZTogXCIvc3lzdGVtXCIsXHJcbiAgICBwbHVnaW5zOiBbXHJcbiAgICAgIHZ1ZSgpLFxyXG4gICAgICB2dWVKc3goKSxcclxuICAgICAgV2luZGlDU1MoKSxcclxuICAgICAgc3ZnTG9hZGVyKCksXHJcbiAgICAgIGNyZWF0ZVN2Z0ljb25zUGx1Z2luKHtcclxuICAgICAgICBpY29uRGlyczogW1xyXG4gICAgICAgICAgcmVzb2x2ZShwcm9jZXNzLmN3ZCgpLCBcInNyYy9pY29ucy9hcHBcIiksXHJcbiAgICAgICAgICByZXNvbHZlKHByb2Nlc3MuY3dkKCksIFwic3JjL2ljb25zL2ZsYWdcIiksXHJcbiAgICAgICAgICByZXNvbHZlKHByb2Nlc3MuY3dkKCksIFwic3JjL2ljb25zL3N5c3RlbVwiKSxcclxuICAgICAgICBdLFxyXG4gICAgICAgIC8vIFx1NjMwN1x1NUI5QXN5bWJvbElkXHU2ODNDXHU1RjBGXHVGRjA4XHU4RkQ5XHU5MUNDXHU3Njg0XHU5MTREXHU3RjZFXHU0RTBFNi4yXHU2QjY1XHU5QUE0XHU0RTJEXHU3Njg0XHU1RjE1XHU1MTY1c3ZnXHU3RUM0XHU0RUY2XHU3Njg0bmFtZVx1OTE0RFx1N0Y2RVx1OTg3OVx1NTE5OVx1NkNENVx1NjcwOVx1NTE3M1x1RkYwOVxyXG4gICAgICAgIHN5bWJvbElkOiBcIltuYW1lXVwiLFxyXG4gICAgICB9KSxcclxuICAgICAgQXV0b0ltcG9ydCh7XHJcbiAgICAgICAgaW1wb3J0czogW1xyXG4gICAgICAgICAgXCJ2dWVcIixcclxuICAgICAgICAgIFwidnVlLXJvdXRlclwiLFxyXG4gICAgICAgICAgXCJwaW5pYVwiLFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvcGx1Z2lucy9kb3dubG9hZFwiOiBbW1wiZGVmYXVsdFwiLCBcIkRvd25sb2FkXCJdXSwgLy9cdTUxNjhcdTVDNDBcdTRFMEJcdThGN0RcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9wbHVnaW5zL3NlcnZpY2VcIjogW1tcImRlZmF1bHRcIiwgXCJTZXJ2aWNlXCJdXSwgLy9hcGlcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9sYW5ndWFnZS9pbmRleFwiOiBbW1wiZGVmYXVsdFwiLCBcIiR0XCJdXSwgLy9cdTdGRkJcdThCRDFcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgICB7XHJcbiAgICAgICAgICAgIFwiQC9kaWN0XCI6IFtcclxuICAgICAgICAgICAgICBbXCJEaWN0XCIsIFwiRGljdFwiXSxcclxuICAgICAgICAgICAgICBbXCJEaWN0U2VydmljZVwiLCBcIkRpY3RTZXJ2aWNlXCJdLFxyXG4gICAgICAgICAgICAgIFtcInVzZURpY3RcIiwgXCJ1c2VEaWN0XCJdLFxyXG4gICAgICAgICAgICAgIFtcImdldERpY3RUYWdcIiwgXCJnZXREaWN0VGFnXCJdLFxyXG4gICAgICAgICAgICAgIFtcImdldERpY3ROYW1lXCIsIFwiZ2V0RGljdE5hbWVcIl0sXHJcbiAgICAgICAgICAgIF0sIC8vXHU1QjU3XHU1MTc4XHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdGlwc1wiOiBbXHJcbiAgICAgICAgICAgICAgW1wicmVtb3ZlU3VjY2Vzc1wiLCBcInJlbW92ZVN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wic3VibWl0U3VjY2Vzc1wiLCBcInN1Ym1pdFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wiaW1wb3J0U3VjY2Vzc1wiLCBcImltcG9ydFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1wiZXhwb3J0U3VjY2Vzc1wiLCBcImV4cG9ydFN1Y2Nlc3NcIl0sXHJcbiAgICAgICAgICAgICAgW1widmFsaWRhdGVGYWlsXCIsIFwidmFsaWRhdGVGYWlsXCJdLFxyXG4gICAgICAgICAgICAgIFtcInVwbG9hZFN1Y2Nlc3NcIiwgXCJ1cGxvYWRTdWNjZXNzXCJdLFxyXG4gICAgICAgICAgICBdLCAvL1x1NjNEMFx1NzkzQVxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2hvb2tzL3VzZVBhZ2VcIjogW1tcImRlZmF1bHRcIiwgXCJ1c2VQYWdlXCJdXSwgLy9Ib29rXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdXNlVGFibGVcIjogW1tcImRlZmF1bHRcIiwgXCJ1c2VUYWJsZVwiXV0sIC8vSG9va1xyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL3V0aWxzL2Zvcm1hdERhdGFcIjogW1tcImRlZmF1bHRcIiwgXCJGb3JtYXREYXRhXCJdXSwgLy9Ib29rXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdXNlTW9kYWxcIjogW1xyXG4gICAgICAgICAgICAgIFtcInNob3dNb2RhbFwiLCBcInNob3dNb2RhbFwiXSxcclxuICAgICAgICAgICAgICBbXCJjbG9zZU1vZGFsXCIsIFwiY2xvc2VNb2RhbFwiXSxcclxuICAgICAgICAgICAgXSwgLy9Ib29rXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIF0sXHJcbiAgICAgICAgcmVzb2x2ZXJzOiBbRWxlbWVudFBsdXNSZXNvbHZlcigpXSxcclxuICAgICAgfSksXHJcbiAgICAgIENvbXBvbmVudHMoe1xyXG4gICAgICAgIHJlc29sdmVyczogW0VsZW1lbnRQbHVzUmVzb2x2ZXIoKV0sXHJcbiAgICAgIH0pLFxyXG4gICAgICBJY29ucyh7XHJcbiAgICAgICAgY29tcGlsZXI6IFwidnVlM1wiLFxyXG4gICAgICAgIGF1dG9JbnN0YWxsOiB0cnVlLFxyXG4gICAgICB9KSxcclxuICAgICAgY3JlYXRlSHRtbFBsdWdpbih7XHJcbiAgICAgICAgaW5qZWN0OiB7XHJcbiAgICAgICAgICBkYXRhOiB7XHJcbiAgICAgICAgICAgIC8vXHU4M0I3XHU1M0Q2XHU2ODA3XHU5ODk4XHU1M0Q4XHU5MUNGXHJcbiAgICAgICAgICAgIHRpdGxlOiBnZXRUYXJnZXQobW9kZSwgXCJWSVRFX0FQUF9USVRMRVwiKSxcclxuICAgICAgICAgIH0sXHJcbiAgICAgICAgfSxcclxuICAgICAgfSksXHJcbiAgICBdLFxyXG4gICAgcmVzb2x2ZToge1xyXG4gICAgICBhbGlhczoge1xyXG4gICAgICAgIFwiQFwiOiByZXNvbHZlKF9fZGlybmFtZSwgXCJzcmNcIiksIC8vIFx1OERFRlx1NUY4NFx1NTIyQlx1NTQwRFxyXG4gICAgICB9LFxyXG4gICAgICBleHRlbnNpb25zOiBbXHJcbiAgICAgICAgXCIubWpzXCIsXHJcbiAgICAgICAgXCIuanNcIixcclxuICAgICAgICBcIi50c1wiLFxyXG4gICAgICAgIFwidnVlXCIsXHJcbiAgICAgICAgXCIuanN4XCIsXHJcbiAgICAgICAgXCIudHN4XCIsXHJcbiAgICAgICAgXCIuanNvblwiLFxyXG4gICAgICAgIFwiLnNjc3NcIixcclxuICAgICAgICBcIi9pbmRleC52dWVcIixcclxuICAgICAgICBcIi9pbmRleC50c1wiLFxyXG4gICAgICBdLCAvLyBcdTRGN0ZcdTc1MjhcdThERUZcdTVGODRcdTUyMkJcdTU0MERcdTY1RjZcdTYwRjNcdTg5ODFcdTc3MDFcdTc1NjVcdTc2ODRcdTU0MEVcdTdGMDBcdTU0MERcdUZGMENcdTUzRUZcdTRFRTVcdTgxRUFcdTVERjEgXHU1ODlFXHU1MUNGXHJcbiAgICB9LFxyXG4gICAgc2VydmVyOiB7XHJcbiAgICAgIGhvc3Q6IHRydWUsXHJcbiAgICAgIHBvcnQ6IDgwLFxyXG4gICAgICAvLyBcdThCQkVcdTdGNkVcdTRFRTNcdTc0MDZcclxuICAgICAgcHJveHk6IHtcclxuICAgICAgICBcIi92aWRlby1hcGlcIjoge1xyXG4gICAgICAgICAgLy8gJy9hcGknXHU2NjJGXHU0RUUzXHU3NDA2XHU2ODA3XHU4QkM2XHVGRjBDXHU3NTI4XHU0RThFXHU1NDRBXHU4QkM5bm9kZVx1RkYwQ3VybFx1NTI0RFx1OTc2Mlx1NjYyRi9hcGlcdTc2ODRcdTVDMzFcdTY2MkZcdTRGN0ZcdTc1MjhcdTRFRTNcdTc0MDZcdTc2ODRcclxuICAgICAgICAgIHRhcmdldDogYCR7ZW52LlZJVEVfQVBQX0JBU0VfRE9NQUlOfTo4MDgyYCwgLy9cdThGRDlcdTkxQ0NcdTU4NkJcdTUxNjVcdTRGNjBcdTg5ODFcdThCRjdcdTZDNDJcdTc2ODRcdTYzQTVcdTUzRTNcdTc2ODRcdTUyNERcdTdGMDBcclxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSwgLy9cdTY2MkZcdTU0MjZcdThERThcdTU3REZcclxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC92aWRlby1hcGkvLCBcIlwiKSxcclxuICAgICAgICB9LFxyXG4gICAgICAgIFwiL3N5c3RlbS1hcGlcIjoge1xyXG4gICAgICAgICAgLy8gJy9hcGknXHU2NjJGXHU0RUUzXHU3NDA2XHU2ODA3XHU4QkM2XHVGRjBDXHU3NTI4XHU0RThFXHU1NDRBXHU4QkM5bm9kZVx1RkYwQ3VybFx1NTI0RFx1OTc2Mlx1NjYyRi9hcGlcdTc2ODRcdTVDMzFcdTY2MkZcdTRGN0ZcdTc1MjhcdTRFRTNcdTc0MDZcdTc2ODRcclxuICAgICAgICAgIHRhcmdldDogYCR7ZW52LlZJVEVfQVBQX0JBU0VfRE9NQUlOfTo4MDgwYCwgLy9cdThGRDlcdTkxQ0NcdTU4NkJcdTUxNjVcdTRGNjBcdTg5ODFcdThCRjdcdTZDNDJcdTc2ODRcdTYzQTVcdTUzRTNcdTc2ODRcdTUyNERcdTdGMDBcclxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSwgLy9cdTY2MkZcdTU0MjZcdThERThcdTU3REZcclxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoL15cXC9zeXN0ZW0tYXBpLywgXCJcIiksXHJcbiAgICAgICAgfSxcclxuICAgICAgICBcIi9maWxlLWFwaVwiOiB7XHJcbiAgICAgICAgICAvLyAnL2FwaSdcdTY2MkZcdTRFRTNcdTc0MDZcdTY4MDdcdThCQzZcdUZGMENcdTc1MjhcdTRFOEVcdTU0NEFcdThCQzlub2RlXHVGRjBDdXJsXHU1MjREXHU5NzYyXHU2NjJGL2FwaVx1NzY4NFx1NUMzMVx1NjYyRlx1NEY3Rlx1NzUyOFx1NEVFM1x1NzQwNlx1NzY4NFxyXG4gICAgICAgICAgdGFyZ2V0OiBgJHtlbnYuVklURV9BUFBfQkFTRV9ET01BSU59OjgwODFgLCAvL1x1OEZEOVx1OTFDQ1x1NTg2Qlx1NTE2NVx1NEY2MFx1ODk4MVx1OEJGN1x1NkM0Mlx1NzY4NFx1NjNBNVx1NTNFM1x1NzY4NFx1NTI0RFx1N0YwMFxyXG4gICAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLCAvL1x1NjYyRlx1NTQyNlx1OERFOFx1NTdERlxyXG4gICAgICAgICAgcmV3cml0ZTogKHBhdGgpID0+IHBhdGgucmVwbGFjZSgvXlxcL2ZpbGUtYXBpLywgXCJcIiksXHJcbiAgICAgICAgfSxcclxuICAgICAgfSxcclxuICAgIH0sXHJcbiAgfSk7XHJcbn07XHJcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBeVMs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
