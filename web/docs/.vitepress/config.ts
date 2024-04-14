import { defineConfig } from "vitepress";

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "开发文档",
  base: "/docs",
  description: "documentation",
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: "主页", link: "/" },
      { text: "简介", link: "/markdown-examples" },
    ],

    sidebar: [
      {
        text: "快捷跳转",
        items: [
          { text: "表单", link: "/markdown-examples" },
          { text: "表格", link: "/api-examples" },
        ],
      },
    ],

    socialLinks: [
      { icon: "github", link: "https://github.com/vuejs/vitepress" },
    ],
  },
});
