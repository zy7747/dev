import SvgIcon from "./SvgIcon/index.vue";
import Collapse from "./Collapse/index.vue";
import Pagination from "./Pagination/index.vue";
import VideoPlay from "./Player/video.vue";
import Tools from "./Tools/index.vue";
import DictTag from "./DictTag/index.vue";
import IconSelect from "./IconSelect/index.vue";

import CSelect from "./CElement/CSelect/index.vue";
import CSwitch from "./CElement/CSwitch/index.vue";
import CInput from "./CElement/CInput/index.vue";
import CButton from "./CElement/CButton/index.vue";
import CRadio from "./CElement/CRadio/index.vue";
import CDropdown from "./CElement/CDropdown/index.vue";
import CCheckbox from "./CElement/CCheckbox/index.vue";

import CTable from "./CElement/CTable/index.vue";
import CGrid from "./CElement/CGrid/index.vue";
import CPage from "./CElement/CPage/index.vue";
import CCarousel from "./CElement/CCarousel/index.vue";
import CSchema from "./CElement/CSchema/index.vue";
import CTabs from "./CElement/CTabs/index.vue";
import CTree from "./CElement/CTree/index.vue";
import { Icon } from "@iconify/vue";

function setupComponents(app: any) {
  app.component("SvgIcon", SvgIcon);
  app.component("Tools", Tools);
  app.component("Collapse", Collapse);
  app.component("Pagination", Pagination);
  app.component("VideoPlay", VideoPlay);
  app.component("DictTag", DictTag);
  app.component("IconSelect", IconSelect);
  app.component("Icon", Icon);

  app.component("CSelect", CSelect);
  app.component("CRadio", CRadio);
  app.component("CInput", CInput);
  app.component("CButton", CButton);
  app.component("CCheckbox", CCheckbox);
  app.component("CDropdown", CDropdown);
  app.component("CSwitch", CSwitch);
  app.component("CTabs", CTabs);

  app.component("CTable", CTable);
  app.component("CTree", CTree);
  app.component("CPage", CPage);
  app.component("CGrid", CGrid);
  app.component("CSchema", CSchema);
  app.component("CCarousel", CCarousel);

  return app;
}

export default setupComponents;
