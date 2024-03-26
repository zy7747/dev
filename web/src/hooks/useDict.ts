export default function useDict(dictMap: any = {}) {
  const dict: any = reactive({});

  Object.keys(dictMap).forEach((key: any) => {
    Object.assign(dict, { [key]: [{ label: "100", value: 100 }] });
  });

  Object.keys(dictMap).forEach(async (key: any) => {
    const arr = await dictMap[key];

    Object.assign(dict, { [key]: arr });
  });

  return {
    dict,
  };
}
