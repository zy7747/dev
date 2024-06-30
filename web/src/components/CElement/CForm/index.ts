import CRadio from "./src/CRadio/index.vue";
import CCheckbox from "./src/CCheckbox/index.vue";
import CInput from "./src/CInput/index.vue";
import CSelect from "./src/CSelect/index.vue";
import CTreeSelect from "./src/CTreeSelect/index.vue";
import IconSelect from "./src/IconSelect/index.vue";
import CSwitch from "./src/CSwitch/index.vue";
import CNumber from "./src/CNumber/index.vue";
import CSchema from "./src/CSchema/index.vue";
import Editor from "./src/Editor/index.vue";
import CForm from "./index.vue";

export function setupForm(app: any) {
  app.component("CSwitch", CSwitch);
  app.component("IconSelect", IconSelect);
  app.component("CSelect", CSelect);
  app.component("Editor", Editor);
  app.component("CRadio", CRadio);
  app.component("CInput", CInput);
  app.component("CCheckbox", CCheckbox);
  app.component("CSchema", CSchema);
  app.component("CNumber", CNumber);
  app.component("CTreeSelect", CTreeSelect);
  app.component("CForm", CForm);
}
