// vite.config.ts
import { defineConfig, loadEnv } from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/vite@5.1.4_sass@1.71.1/node_modules/vite/dist/node/index.js";
import { createHtmlPlugin } from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/vite-plugin-html@3.2.2_vite@5.1.4/node_modules/vite-plugin-html/dist/index.mjs";
import vue from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/@vitejs+plugin-vue@5.0.4_vite@5.1.4_vue@3.4.19/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import svgLoader from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/vite-svg-loader@5.1.0_vue@3.4.19/node_modules/vite-svg-loader/index.js";
import WindiCSS from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/vite-plugin-windicss@1.9.3_vite@5.1.4/node_modules/vite-plugin-windicss/dist/index.mjs";
import AutoImport from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/unplugin-auto-import@0.17.5_@vueuse+core@10.8.0/node_modules/unplugin-auto-import/dist/vite.js";
import Components from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/vite.js";
import Icons from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/unplugin-icons@0.18.5/node_modules/unplugin-icons/dist/vite.js";
import { ElementPlusResolver } from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/unplugin-vue-components@0.26.0_vue@3.4.19/node_modules/unplugin-vue-components/dist/resolvers.js";
import { createSvgIconsPlugin } from "file:///C:/Users/zy/Desktop/dev/web/node_modules/.pnpm/vite-plugin-svg-icons@2.0.1_vite@5.1.4/node_modules/vite-plugin-svg-icons/dist/index.mjs";
import { resolve } from "path";
var __vite_injected_original_dirname = "C:\\Users\\zy\\Desktop\\dev\\web";
var getTarget = (mode, target) => {
  return loadEnv(mode, process.cwd())[target];
};
var vite_config_default = ({ mode }) => defineConfig({
  base: "/system/",
  plugins: [
    vue(),
    WindiCSS(),
    svgLoader(),
    createSvgIconsPlugin({
      iconDirs: [resolve("./src/icons")],
      // 指定symbolId格式（这里的配置与6.2步骤中的引入svg组件的name配置项写法有关）
      symbolId: "icon-[dir]-[name]"
    }),
    AutoImport({
      imports: [
        "vue",
        "vue-router",
        "pinia",
        {
          "@/plugins/service": [["default", "Service"]]
        },
        {
          "@/hooks/usePage": [["default", "usePage"]]
        },
        {
          "@/hooks/useTable": [["default", "useTable"]]
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
    // Listening on all local IPs
    host: true,
    port: 88
    // 设置代理
  }
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFx6eVxcXFxEZXNrdG9wXFxcXGRldlxcXFx3ZWJcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkM6XFxcXFVzZXJzXFxcXHp5XFxcXERlc2t0b3BcXFxcZGV2XFxcXHdlYlxcXFx2aXRlLmNvbmZpZy50c1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vQzovVXNlcnMvenkvRGVza3RvcC9kZXYvd2ViL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnLCBsb2FkRW52IH0gZnJvbSBcInZpdGVcIjtcclxuaW1wb3J0IHsgY3JlYXRlSHRtbFBsdWdpbiB9IGZyb20gXCJ2aXRlLXBsdWdpbi1odG1sXCI7XHJcbmltcG9ydCB2dWUgZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZVwiO1xyXG5pbXBvcnQgc3ZnTG9hZGVyIGZyb20gXCJ2aXRlLXN2Zy1sb2FkZXJcIjtcclxuaW1wb3J0IFdpbmRpQ1NTIGZyb20gXCJ2aXRlLXBsdWdpbi13aW5kaWNzc1wiO1xyXG5pbXBvcnQgQXV0b0ltcG9ydCBmcm9tIFwidW5wbHVnaW4tYXV0by1pbXBvcnQvdml0ZVwiO1xyXG5pbXBvcnQgQ29tcG9uZW50cyBmcm9tIFwidW5wbHVnaW4tdnVlLWNvbXBvbmVudHMvdml0ZVwiO1xyXG5pbXBvcnQgSWNvbnMgZnJvbSBcInVucGx1Z2luLWljb25zL3ZpdGVcIjtcclxuaW1wb3J0IHsgRWxlbWVudFBsdXNSZXNvbHZlciB9IGZyb20gXCJ1bnBsdWdpbi12dWUtY29tcG9uZW50cy9yZXNvbHZlcnNcIjtcclxuXHJcbmltcG9ydCB7IGNyZWF0ZVN2Z0ljb25zUGx1Z2luIH0gZnJvbSBcInZpdGUtcGx1Z2luLXN2Zy1pY29uc1wiO1xyXG5pbXBvcnQgeyByZXNvbHZlIH0gZnJvbSBcInBhdGhcIjtcclxuXHJcbmNvbnN0IGdldFRhcmdldCA9IChtb2RlOiBzdHJpbmcsIHRhcmdldDogc3RyaW5nKSA9PiB7XHJcbiAgcmV0dXJuIGxvYWRFbnYobW9kZSwgcHJvY2Vzcy5jd2QoKSlbdGFyZ2V0XTtcclxufTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0ICh7IG1vZGUgfTogYW55KSA9PlxyXG4gIGRlZmluZUNvbmZpZyh7XHJcbiAgICBiYXNlOiBcIi9zeXN0ZW0vXCIsXHJcbiAgICBwbHVnaW5zOiBbXHJcbiAgICAgIHZ1ZSgpLFxyXG4gICAgICBXaW5kaUNTUygpLFxyXG4gICAgICBzdmdMb2FkZXIoKSxcclxuICAgICAgY3JlYXRlU3ZnSWNvbnNQbHVnaW4oe1xyXG4gICAgICAgIGljb25EaXJzOiBbcmVzb2x2ZShcIi4vc3JjL2ljb25zXCIpXSxcclxuICAgICAgICAvLyBcdTYzMDdcdTVCOUFzeW1ib2xJZFx1NjgzQ1x1NUYwRlx1RkYwOFx1OEZEOVx1OTFDQ1x1NzY4NFx1OTE0RFx1N0Y2RVx1NEUwRTYuMlx1NkI2NVx1OUFBNFx1NEUyRFx1NzY4NFx1NUYxNVx1NTE2NXN2Z1x1N0VDNFx1NEVGNlx1NzY4NG5hbWVcdTkxNERcdTdGNkVcdTk4NzlcdTUxOTlcdTZDRDVcdTY3MDlcdTUxNzNcdUZGMDlcclxuICAgICAgICBzeW1ib2xJZDogXCJpY29uLVtkaXJdLVtuYW1lXVwiLFxyXG4gICAgICB9KSxcclxuICAgICAgQXV0b0ltcG9ydCh7XHJcbiAgICAgICAgaW1wb3J0czogW1xyXG4gICAgICAgICAgXCJ2dWVcIixcclxuICAgICAgICAgIFwidnVlLXJvdXRlclwiLFxyXG4gICAgICAgICAgXCJwaW5pYVwiLFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvcGx1Z2lucy9zZXJ2aWNlXCI6IFtbXCJkZWZhdWx0XCIsIFwiU2VydmljZVwiXV0sXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgICAge1xyXG4gICAgICAgICAgICBcIkAvaG9va3MvdXNlUGFnZVwiOiBbW1wiZGVmYXVsdFwiLCBcInVzZVBhZ2VcIl1dLFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICAgIHtcclxuICAgICAgICAgICAgXCJAL2hvb2tzL3VzZVRhYmxlXCI6IFtbXCJkZWZhdWx0XCIsIFwidXNlVGFibGVcIl1dLFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICBdLFxyXG4gICAgICAgIHJlc29sdmVyczogW0VsZW1lbnRQbHVzUmVzb2x2ZXIoKV0sXHJcbiAgICAgIH0pLFxyXG4gICAgICBDb21wb25lbnRzKHtcclxuICAgICAgICByZXNvbHZlcnM6IFtFbGVtZW50UGx1c1Jlc29sdmVyKCldLFxyXG4gICAgICB9KSxcclxuICAgICAgSWNvbnMoe1xyXG4gICAgICAgIGNvbXBpbGVyOiBcInZ1ZTNcIixcclxuICAgICAgICBhdXRvSW5zdGFsbDogdHJ1ZSxcclxuICAgICAgfSksXHJcbiAgICAgIGNyZWF0ZUh0bWxQbHVnaW4oe1xyXG4gICAgICAgIGluamVjdDoge1xyXG4gICAgICAgICAgZGF0YToge1xyXG4gICAgICAgICAgICAvL1x1ODNCN1x1NTNENlx1NjgwN1x1OTg5OFx1NTNEOFx1OTFDRlxyXG4gICAgICAgICAgICB0aXRsZTogZ2V0VGFyZ2V0KG1vZGUsIFwiVklURV9BUFBfVElUTEVcIiksXHJcbiAgICAgICAgICB9LFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0pLFxyXG4gICAgXSxcclxuICAgIHJlc29sdmU6IHtcclxuICAgICAgYWxpYXM6IHtcclxuICAgICAgICBcIkBcIjogcmVzb2x2ZShfX2Rpcm5hbWUsIFwic3JjXCIpLCAvLyBcdThERUZcdTVGODRcdTUyMkJcdTU0MERcclxuICAgICAgfSxcclxuICAgICAgZXh0ZW5zaW9uczogW1wiLm1qc1wiLCBcIi5qc1wiLCBcIi50c1wiLCBcIi5qc3hcIiwgXCIudHN4XCIsIFwiLmpzb25cIiwgXCIuc2Nzc1wiXSwgLy8gXHU0RjdGXHU3NTI4XHU4REVGXHU1Rjg0XHU1MjJCXHU1NDBEXHU2NUY2XHU2MEYzXHU4OTgxXHU3NzAxXHU3NTY1XHU3Njg0XHU1NDBFXHU3RjAwXHU1NDBEXHVGRjBDXHU1M0VGXHU0RUU1XHU4MUVBXHU1REYxIFx1NTg5RVx1NTFDRlxyXG4gICAgfSxcclxuICAgIHNlcnZlcjoge1xyXG4gICAgICAvLyBMaXN0ZW5pbmcgb24gYWxsIGxvY2FsIElQc1xyXG4gICAgICBob3N0OiB0cnVlLFxyXG4gICAgICBwb3J0OiA4OCxcclxuICAgICAgLy8gXHU4QkJFXHU3RjZFXHU0RUUzXHU3NDA2XHJcbiAgICB9LFxyXG4gIH0pO1xyXG4iXSwKICAibWFwcGluZ3MiOiAiO0FBQStRLFNBQVMsY0FBYyxlQUFlO0FBQ3JULFNBQVMsd0JBQXdCO0FBQ2pDLE9BQU8sU0FBUztBQUNoQixPQUFPLGVBQWU7QUFDdEIsT0FBTyxjQUFjO0FBQ3JCLE9BQU8sZ0JBQWdCO0FBQ3ZCLE9BQU8sZ0JBQWdCO0FBQ3ZCLE9BQU8sV0FBVztBQUNsQixTQUFTLDJCQUEyQjtBQUVwQyxTQUFTLDRCQUE0QjtBQUNyQyxTQUFTLGVBQWU7QUFYeEIsSUFBTSxtQ0FBbUM7QUFhekMsSUFBTSxZQUFZLENBQUMsTUFBYyxXQUFtQjtBQUNsRCxTQUFPLFFBQVEsTUFBTSxRQUFRLElBQUksQ0FBQyxFQUFFLE1BQU07QUFDNUM7QUFFQSxJQUFPLHNCQUFRLENBQUMsRUFBRSxLQUFLLE1BQ3JCLGFBQWE7QUFBQSxFQUNYLE1BQU07QUFBQSxFQUNOLFNBQVM7QUFBQSxJQUNQLElBQUk7QUFBQSxJQUNKLFNBQVM7QUFBQSxJQUNULFVBQVU7QUFBQSxJQUNWLHFCQUFxQjtBQUFBLE1BQ25CLFVBQVUsQ0FBQyxRQUFRLGFBQWEsQ0FBQztBQUFBO0FBQUEsTUFFakMsVUFBVTtBQUFBLElBQ1osQ0FBQztBQUFBLElBQ0QsV0FBVztBQUFBLE1BQ1QsU0FBUztBQUFBLFFBQ1A7QUFBQSxRQUNBO0FBQUEsUUFDQTtBQUFBLFFBQ0E7QUFBQSxVQUNFLHFCQUFxQixDQUFDLENBQUMsV0FBVyxTQUFTLENBQUM7QUFBQSxRQUM5QztBQUFBLFFBQ0E7QUFBQSxVQUNFLG1CQUFtQixDQUFDLENBQUMsV0FBVyxTQUFTLENBQUM7QUFBQSxRQUM1QztBQUFBLFFBQ0E7QUFBQSxVQUNFLG9CQUFvQixDQUFDLENBQUMsV0FBVyxVQUFVLENBQUM7QUFBQSxRQUM5QztBQUFBLE1BQ0Y7QUFBQSxNQUNBLFdBQVcsQ0FBQyxvQkFBb0IsQ0FBQztBQUFBLElBQ25DLENBQUM7QUFBQSxJQUNELFdBQVc7QUFBQSxNQUNULFdBQVcsQ0FBQyxvQkFBb0IsQ0FBQztBQUFBLElBQ25DLENBQUM7QUFBQSxJQUNELE1BQU07QUFBQSxNQUNKLFVBQVU7QUFBQSxNQUNWLGFBQWE7QUFBQSxJQUNmLENBQUM7QUFBQSxJQUNELGlCQUFpQjtBQUFBLE1BQ2YsUUFBUTtBQUFBLFFBQ04sTUFBTTtBQUFBO0FBQUEsVUFFSixPQUFPLFVBQVUsTUFBTSxnQkFBZ0I7QUFBQSxRQUN6QztBQUFBLE1BQ0Y7QUFBQSxJQUNGLENBQUM7QUFBQSxFQUNIO0FBQUEsRUFDQSxTQUFTO0FBQUEsSUFDUCxPQUFPO0FBQUEsTUFDTCxLQUFLLFFBQVEsa0NBQVcsS0FBSztBQUFBO0FBQUEsSUFDL0I7QUFBQSxJQUNBLFlBQVksQ0FBQyxRQUFRLE9BQU8sT0FBTyxRQUFRLFFBQVEsU0FBUyxPQUFPO0FBQUE7QUFBQSxFQUNyRTtBQUFBLEVBQ0EsUUFBUTtBQUFBO0FBQUEsSUFFTixNQUFNO0FBQUEsSUFDTixNQUFNO0FBQUE7QUFBQSxFQUVSO0FBQ0YsQ0FBQzsiLAogICJuYW1lcyI6IFtdCn0K
