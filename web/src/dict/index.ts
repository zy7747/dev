import { useDictStore } from "@/store/dict";

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
  const dictStore = useDictStore();
  const dictMap: any = dictStore.dictMap;
  //获取字典
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];

  return dict;
}

export function DictService(dictCode: string, params?: any) {
  //获取字典

  return serviceMap[dictCode](params);
}

export function getDictTag(dictCode: string, value: string) {
  const dictStore = useDictStore();
  const dictMap: any = dictStore.dictMap;
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];
  const i = dict.find((item: any) => item.value === value);

  return i ? i : { label: "", value: "", color: null };
}

export function getDictName(dictCode: string, value: string) {
  const dictStore = useDictStore();
  const dictMap: any = dictStore.dictMap;

  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];
  const i = dict.find((item: any) => item.value === value);

  return i.label;
}

export function useDict(dictMap: any = {}) {
  const dict: any = reactive({});
  const dictStore = useDictStore();

  Object.keys(dictMap).forEach((key: any) => {
    Object.assign(dict, { [key]: [] });
  });

  Object.keys(dictMap).forEach((key: any) => {
    dictMap[key].then((res: any) => {
      Object.assign(dict, { [key]: res });
      dictStore.setDict(key, res);
    });
  });

  return {
    dict,
  };
}
