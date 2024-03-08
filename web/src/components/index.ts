import SvgIcon from "./SvgIcon/index.vue";
import Collapse from "./Collapse/index.vue";
import Pagination from "./Pagination/index.vue";

import CSelect from "./CElement/CSelect/index.vue";
import CInput from "./CElement/CInput/index.vue";
import CButton from "./CElement/CButton/index.vue";
import CTable from "./CElement/CTable/index.vue";
import CPage from "./CElement/CPage/index.vue";
import CCarousel from "./CElement/CCarousel/index.vue";
import CSchema from "./CElement/CSchema/index.vue";

function setupComponents(app: any) {
  app.component("SvgIcon", SvgIcon);
  app.component("Collapse", Collapse);
  app.component("Pagination", Pagination);

  app.component("CSelect", CSelect);
  app.component("CInput", CInput);
  app.component("CButton", CButton);
  app.component("CTable", CTable);
  app.component("CPage", CPage);
  app.component("CSchema", CSchema);
  app.component("CCarousel", CCarousel);

  return app;
}

export default setupComponents;
