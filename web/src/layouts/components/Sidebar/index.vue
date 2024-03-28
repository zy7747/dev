<!--  -->
<template>
  <div class="sidebar">
    <el-menu :collapse="!collapse" :background-color="backgroundColor">
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

const asWidth = computed(() => {
  return prop.width + "px";
});
</script>

<style lang="scss" scoped>
.el-menu:not(.el-menu--collapse) {
  --menuWidth: v-bind(asWidth);
  width: var(--menuWidth);
}
</style>
