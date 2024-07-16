export function dictList() {
  return Service.configuration.dict.list().then((res: any) => {
    return res.data.map((item: any) => {
      return { label: item.dictName, value: item.dictCode };
    });
  });
}
