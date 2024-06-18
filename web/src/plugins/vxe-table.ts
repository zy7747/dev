import VxeUITable from "vxe-table";
import VXETablePluginExportXLSX from "vxe-table-plugin-export-xlsx";
import VxeUI from "vxe-pc-ui";
import ExcelJS from "exceljs";

import "vxe-table/lib/style.css";
import "vxe-pc-ui/lib/style.css";

import type { App } from "vue";

VxeUITable.use(VXETablePluginExportXLSX, {
  ExcelJS,
});

function useTable(app: App) {
  app.use(VxeUITable);
  app.use(VxeUI);
}

export default useTable;
