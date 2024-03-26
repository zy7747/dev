import { defineStore } from "pinia";

export const useDictStore = defineStore({
  id: "dict", // id必填，且需要唯一
  state: () => {
    return {
      dictMap: {},
    };
  },
  actions: {
    getDictMap() {
      return Service.configuration.dict.dictMap().then((response: any) => {
        if (response.code === 200) {
          this.dictMap = response.data;
        }
      });
    },
  },
});
