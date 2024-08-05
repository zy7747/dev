import { toHump } from "@/utils/formatData";

export function getTableList() {
  return Service.configuration.table.getTableList().then((res: any) => {
    return res.data.map((item: any) => {
      return {
        label: `${item.TABLE_COMMENT} (${toHump(item.TABLE_NAME)})`,
        value: toHump(item.TABLE_NAME),
      };
    });
  });
}
