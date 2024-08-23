export function dictList() {
  return Service.config.dict.list().then((res: any) => {
    return res.data.map((item: any) => {
      return { label: item.dictName, value: item.dictCode };
    });
  });
}
