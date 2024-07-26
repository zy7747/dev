import { createApp } from "vue";
import App from "./App.vue";

//components
import setupComponents from "@/components";
//router
import { setupRouter } from "@/router";
//pinia
import { createPinia } from "pinia";
//VXETable
import useTable from "@/plugins/vxe-table";
//directives
import setupDirectives from "@/directives";
//language
import { i18n } from "@/language/index";
//style
import "@/styles/index.scss";
import "virtual:windi.css";
//hover.css
import "hover.css";
//svg
import "virtual:svg-icons-register";
//permission
import "@/permission";
//plugins
import "@/plugins";
//piniaPersist
import piniaPersist from "pinia-plugin-persist";

const app = createApp(App);

const pinia = createPinia();
pinia.use(piniaPersist);

async function create() {
  //router
  setupRouter(app);
  //组成全局组件
  setupComponents(app);
  //VXETable
  useTable(app);
  //directives
  setupDirectives(app);
  //翻译
  app.use(i18n);
  //pinia
  app.use(pinia);
  //app
  app.mount("#app");
}

create();
