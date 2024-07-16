import type { App } from "vue";
import VxeUITable from "vxe-table";
import VxeUI from "vxe-pc-ui";

import "vxe-table/lib/style.css";
import "vxe-pc-ui/lib/style.css";
import "@vxe-ui/plugin-render-element/dist/style.css";

import VxeUIPluginExportXLSX from "@vxe-ui/plugin-export-xlsx";
import VXETablePluginExportPDF from "@vxe-ui/plugin-export-xlsx";
import VxeUIPluginRenderElement from "@vxe-ui/plugin-render-element";

VxeUI.use(VxeUIPluginExportXLSX);
VxeUI.use(VXETablePluginExportPDF, {
  // 支持中文字体，字体库非常大，建议下载到项目本地
  fontName: "SourceHanSans-Normal",
  fonts: [
    {
      fontName: "SourceHanSans-Normal",
      fontUrl:
        "https://cdn.jsdelivr.net/npm/vxe-table-plugin-export-pdf/fonts/source-han-sans-normal.js",
    },
  ],
});
VxeUI.use(VxeUIPluginRenderElement);

function useTable(app: App) {
  app.use(VxeUITable);
  app.use(VxeUI);
}

export default useTable;
