<!--  -->
<template>
  <div class="sidebar">
    <el-menu
      active-text-color="#409eff"
      :collapse="!collapse"
      mode="vertical"
      :default-active="activeMenu"
      :unique-opened="true"
      :collapse-transition="true"
    >
      <template v-for="item in filterList">
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
const query = ref("");

const prop = defineProps({
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

const filterList = computed(() => {
  const text = unref(query);
  if (text && text !== "") {
    return unref(list).filter((item: any) => {
      return item.title.indexOf(text) !== -1;
    });
  } else {
    return unref(list);
  }
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
  background-color: transparent !important;
  border-right: 0;
}

::v-deep(.el-sub-menu__title) {
  height: 40px;
  border-radius: 5px;
  padding: 0 !important;
  background-color: transparent !important;
  // justify-content: center;
}

.sidebar {
  margin: 15px 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>

<style lang="scss">
.el-menu {
  background-color: transparent !important;
  border-right: 0;
}

.el-sub-menu__title {
  padding: 0 !important;
}
</style>

<style lang="scss">
.el-menu-item {
  height: 40px !important;
  padding: 0 !important;
  border-radius: 5px;
  // justify-content: center;
}
</style>
