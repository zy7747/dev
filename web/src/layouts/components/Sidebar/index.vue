<!--  -->
<template>
  <div class="sidebar">
    <c-input
      v-if="collapse"
      style="width: 200px; margin-bottom: 5px"
      v-model="query"
      :placeholder="$t('form.search')"
      :suffixIcon="Search"
    />
    <el-menu
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
import { Search } from "@element-plus/icons-vue";

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
}

.sidebar {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.el-menu {
  border-right: none;
}
</style>
