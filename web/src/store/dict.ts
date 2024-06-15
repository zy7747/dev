import { defineStore } from "pinia";

export const useDictStore = defineStore({
  id: "dict", // id必填，且需要唯一
  state: () => {
    return {
      dictMap: {} as any,
    };
  },
  actions: {
    getDictMap() {
      Service.configuration.dict.dictMap().then((response: any) => {
        if (response.code === 200) {
          for (const key in response.data) {
            this.dictMap[key] = response.data[key].map((item: any) => {
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
        }
      });
    },
    getService() {
      Service.user.list().then((response: any) => {
        if (response.code === 200) {
          this.dictMap["user"] = response.data.map((item: any) => {
            return { label: item.nickname, value: item.id, color: "success" };
          });
        }
      });
    },
  },
});
