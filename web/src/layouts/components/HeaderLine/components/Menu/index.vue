<!--  -->
<template>
  <el-menu
    mode="horizontal"
    :default-active="activeMenu"
    :unique-opened="true"
    :collapse-transition="true"
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
.el-menu {
  width: 25vw;
  background: transparent;
  border: none !important;
  padding: 0 !important;
}

::v-deep(.el-sub-menu__title) {
  margin-right: 15px;
  border-bottom: none !important;
  padding: 0 !important;
  &:hover {
    background-color: transparent !important;
  }
}

.el-menu--horizontal.el-menu {
  border-bottom: 0px;
}
.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: 0px;
}
</style>
