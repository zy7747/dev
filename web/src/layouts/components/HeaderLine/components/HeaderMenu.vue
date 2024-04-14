<!--  -->
<template>
  <el-menu
    mode="horizontal"
    text-color="black"
    active-text-color="#eec54e"
    :backgroundColor="backgroundColor"
    :default-active="activeMenu"
    :unique-opened="true"
    :collapse-transition="true"
    style="width: 550px"
  >
    <template v-for="item in list">
      <MenuItem :menu="item"></MenuItem>
    </template>
  </el-menu>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";
import MenuItem from "./HeaderMenuItem.vue";

const userStore = useUserStore();
const Route = useRoute();

defineProps({
  backgroundColor: {
    text: "背景颜色",
    type: [String],
    default: "#FFF",
  },
});

const list: any = computed(() => {
  return userStore.menu;
});

const activeMenu: any = computed(() => {
  const { path } = Route;

  return path;
});
</script>

<style lang="scss" scoped>
::v-deep(.el-sub-menu__title) {
  border-bottom: none !important;
  padding-left: 0 !important;
}

::v-deep(.el-menu) {
  border: none !important;
}

.el-menu--horizontal.el-menu {
  border-bottom: 0px solid black;
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 0px solid black;
}
</style>
