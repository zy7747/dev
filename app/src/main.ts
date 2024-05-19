import { createSSRApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import uviewPlus from "uview-plus";

import "@/styles/index.scss";
import "@/permission";

//组件
import i18n from "@/language/index";

export function createApp() {
  const app = createSSRApp(App);

  app.use(uviewPlus);

  app.use(i18n);

  app.use(createPinia());

  return {
    app,
  };
}
