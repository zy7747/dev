<!--  -->
<template>
  <div class="sidebar">
    <el-menu
      :collapse="!collapse"
      mode="vertical"
      text-color="black"
      active-text-color="#eec54e"
      :backgroundColor="backgroundColor"
      :default-active="activeMenu"
      :unique-opened="true"
      :collapse-transition="true"
    >
      <template v-for="item in list">
        <MenuItem :menu="item"></MenuItem>
      </template>
    </el-menu>
  </div>
</template>

<script lang="ts" setup>
import { useUserStore } from "@/store/user";
import MenuItem from "./Item.vue";

const userStore = useUserStore();
const Route = useRoute();

const prop = defineProps({
  backgroundColor: {
    text: "背景颜色",
    type: [String],
    default: "#FFF",
  },
  width: {
    text: "宽度",
    type: [Number],
  },
  collapse: {
    text: "菜单伸缩开关",
    type: [Boolean],
  },
});

const list: any = computed(() => {
  return userStore.menu;
});

const activeMenu: any = computed(() => {
  const { path } = Route;

  return path;
});

const asWidth = computed(() => {
  return prop.width + "px";
});
</script>

<style lang="scss" scoped>
.el-menu:not(.el-menu--collapse) {
  --menuWidth: v-bind(asWidth);
  width: var(--menuWidth);
}

.el-menu {
  border-right: none;
}
</style>
