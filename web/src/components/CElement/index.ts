import CButton from "./src/CButton/index.vue";
import CDropdown from "./src/CDropdown/index.vue";
import CTabs from "./src/CTabs/index.vue";
import CTree from "./src/CTree/index.vue";
import CCarousel from "./src/CCarousel/index.vue";

export function setupElement(app: any) {
  app.component("CButton", CButton);
  app.component("CDropdown", CDropdown);
  app.component("CTabs", CTabs);
  app.component("CCarousel", CCarousel);
  app.component("CTree", CTree);
}
