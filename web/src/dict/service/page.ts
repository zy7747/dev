export function getTableList() {
  return Service.configuration.table.getTableList().then((res: any) => {
    return res.data.map((item: any) => {
      return {
        label: `${item.TABLE_COMMENT} (${FormatData.toHump(item.TABLE_NAME)})`,
        value: FormatData.toHump(item.TABLE_NAME),
      };
    });
  });
}
