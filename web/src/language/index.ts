import { createI18n } from "vue-i18n";
// 从语言包文件中导入语言包对象
import zh from "./locales/zh.json";
import en from "./locales/en.json";
import jp from "./locales/jp.json";
import kr from "./locales/kr.json";

const i18n = createI18n({
  //这里是语种的持久化，刷新也存在
  locale: localStorage.getItem("language") || "zh", // 默认是中文
  fallbackLocale: "zh", // 语言切换的时候是英文
  globalInjection: true, //全局配置
  legacy: false, //vue3写法
  messages: { en, zh, jp, kr }, //需要做国际化的语种
});

export default i18n;
