import { defineConfig } from "vitepress";

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "技术文档",
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
        text: "adb",
        items: [{ text: "adb基础", link: "/adb使用" }],
      },
      {
        text: "mysql",
        items: [
          { text: "mysql安装", link: "/mysql安装" },
          { text: "mysql基础", link: "/mysql" },
        ],
      },
      {
        text: "命令与语法",
        items: [
          { text: "cmd命令", link: "/cmd命令" },
          { text: "linux命令", link: "/linux命令" },
          { text: "bat语法", link: "/bat语法" },
          { text: "git命令", link: "/git命令" },
        ],
      },
      {
        text: "项目创建流程",
        items: [{ text: "vue3项目创建", link: "/VUE3项目创建流程" }],
      },
      {
        text: "npm",
        items: [
          {
            text: "将自己的包上传至npm",
            link: "/functions/将自己的包上传至npm",
          },
        ],
      },
      {
        text: "基础知识",
        items: [
          {
            text: "HTML(超文本标记语言)",
            link: "/basic/HTML(超文本标记语言)",
          },
          {
            text: "JS",
            link: "/basic/JS",
          },
          {
            text: "CSS样式",
            link: "/basic/CSS样式",
          },
        ],
      },
    ],
    socialLinks: [
      { icon: "github", link: "https://github.com/vuejs/vitepress" },
    ],
  },
});
