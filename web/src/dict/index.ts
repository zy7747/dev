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

const useDict = useDictStore();

const dictMap: any = useDict.dictMap;

export function Dict(dictCode: string) {
  //获取字典
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];

  return dict;
}

export function DictService(dictCode: string, params?: any) {
  //获取字典

  return serviceMap[dictCode](params);
}
