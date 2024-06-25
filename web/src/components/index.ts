import SvgIcon from "./SvgIcon/index.vue";
import Collapse from "./Collapse/index.vue";
import Pagination from "./Pagination/index.vue";
import VideoPlayer from "./Player/video.vue";
import Tools from "./Tools/index.vue";
import DictTag from "./DictTag/index.vue";
import Modal from "./Modal/index.vue";
import Episode from "./Episode/index.vue";

import CTable from "./CElement/CTable/index.vue";
import CGrid from "./CElement/CGrid/index.vue";
import CPage from "./CElement/CPage/index.vue";

import { setupForm } from "./CElement/CForm/index.ts";
import { setupElement } from "./CElement/index.ts";

function setupComponents(app: any) {
  app.component("SvgIcon", SvgIcon);
  app.component("Tools", Tools);
  app.component("Collapse", Collapse);
  app.component("Pagination", Pagination);
  app.component("VideoPlayer", VideoPlayer);
  app.component("Episode", Episode);
  app.component("DictTag", DictTag);

  app.component("CModal", Modal);
  app.component("CTable", CTable);

  app.component("CPage", CPage);
  app.component("CGrid", CGrid);

  setupForm(app);
  setupElement(app);

  return app;
}

export default setupComponents;
