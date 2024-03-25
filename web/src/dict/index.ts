import { useDictStore } from "@/store/dict";
import lodash from "lodash";

const useDict = useDictStore();

const dictMap: any = useDict.dictMap;

export function Dict(dictCode: string) {
  //获取字典
  const dict = dictMap[dictCode] ? dictMap[dictCode] : [];

  return dict;
}
