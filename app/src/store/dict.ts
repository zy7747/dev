import { dictAllList } from "@/apis/dict";
import { defineStore } from "pinia";

export const useDictStore = defineStore({
  id: "dict", // id必填，且需要唯一
  state: () => {
    return {
      dict: {},
    };
  },
  actions: {
    getDict() {
      return dictAllList().then((res: any) => {
        if (res.code === 200) {
          let dictList = res.data;

          for (const key in dictList) {
            dictList[key] = dictList[key].map((item: any) => {
              //字典类型转换
              if (item.dictType) {
                if (item.dictType === "string") {
                  return {
                    ...item,
                    value: item.value ? item.value.toString() : "",
                  };
                } else if (item.dictType === "number") {
                  return { ...item, value: Number(item.value) };
                }
              } else {
                return {
                  ...item,
                  dictType: "string",
                  value: item.value ? item.value.toString() : "",
                };
              }
            });
          }
          this.dict = dictList;
        }
      });
    },
  },
});
