<!--  -->
<template>
  <el-menu
    mode="horizontal"
    :default-active="activeMenu"
    :unique-opened="true"
    :collapse-transition="true"
    style="width: 25vw; background: transparent"
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
  border-bottom: 0px;
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 0px;
}
</style>
