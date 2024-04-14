import { defineStore } from "pinia";

export const useThemeStore = defineStore({
  id: "theme", // id必填，且需要唯一
  state: () => {
    return {
      drawer: false,
    };
  },
  actions: {
    save(theme: any) {
      localStorage.setItem("theme", JSON.stringify(theme.value));
      this.drawer = false;
    },
    open() {
      this.drawer = true;
    },
  },
});
