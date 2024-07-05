import { defineConfig } from "vite";
import uni from "@dcloudio/vite-plugin-uni";
import AutoImport from "unplugin-auto-import/vite";
import { resolve } from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    uni(),
    AutoImport({
      imports: [
        "vue",
        "pinia",
        {
          "@/plugins/service": [["default", "Service"]], //全局下载
        },
        {
          "@/language/index": [["default", "$t"]], //翻译
        },
      ],
    }),
  ],
  resolve: {
    alias: {
      "@": resolve(__dirname, "src"), // 路径别名
    },
    extensions: [".mjs", ".js", ".ts", "vue", ".jsx", ".tsx", ".json", ".scss"], // 使用路径别名时想要省略的后缀名，可以自己 增减
  },
});
