import { useDictStore } from "@/store/dict";

const service = import.meta.glob(["@/dict/service/**.ts"], {
  eager: true,
});

const useDict = useDictStore();

const dictMap: any = useDict.dictMap;

export function Dict(dictCode: string) {
  //获取字典
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];

  return dict;
}

export function DictService(dictCode: string) {
  //获取字典
  console.log(dictCode, service);
}
