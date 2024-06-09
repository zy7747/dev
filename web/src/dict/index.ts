import { useDictStore } from "@/store/dict";

const dictStore = useDictStore();
const dictMap: any = dictStore.dictMap;

const service = import.meta.glob(["@/dict/service/**.ts"], {
  eager: true,
});

const serviceMap: any = {};
Object.values(service).map((value: any) => {
  Object.keys(value).map((key: any) => {
    serviceMap[key] = value[key];
  });
});

export function Dict(dictCode: string) {
  //获取字典
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];

  return dict;
}

export function DictService(dictCode: string, params?: any) {
  //获取字典

  return serviceMap[dictCode](params);
}

export function useDict(dictMap: any = {}) {
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
