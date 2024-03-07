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
import "@/utils";
//permission
import "@/permission";
//plugins
import "@/plugins";
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
