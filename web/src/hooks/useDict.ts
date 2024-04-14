export default function useDict(dictMap: any = {}) {
  const dict: any = reactive({});

  Object.keys(dictMap).forEach((key: any) => {
    Object.assign(dict, { [key]: [] });
  });

  Object.keys(dictMap).forEach(async (key: any) => {
    const arr = await dictMap[key];

    Object.assign(dict, { [key]: arr });
  });

  return {
    dict,
  };
}
