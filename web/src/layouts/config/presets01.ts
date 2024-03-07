import { Normal } from "../container/index";

const theme = ref<any>({
  container: markRaw(Normal),
  headerBackgroundColor: "#d9e0ee",
  asideBackgroundColor: "#d9e0ee",
  breadcrumb: true,
  tagsView: true,
  headerHeight: 60,
  asideWidth: 200,
  collapse: true,
});

export default theme;
