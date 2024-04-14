<!--  -->
<template>
  <el-sub-menu v-if="menu.children && menu.children.length" :index="menu.path">
    <template #title>
      <svg-icon :name="menu.icon" class="icon" />
      <el-text>{{ menu.title }}</el-text>
    </template>

    <template v-for="item in menu.children">
      <MenuItem :menu="item"></MenuItem>
    </template>
  </el-sub-menu>

  <el-menu-item
    v-else
    @click="() => menuClick(menu)"
    :persistent="false"
    :index="menu.path"
  >
    <svg-icon :name="menu.icon" class="icon" />
    <template #title>
      {{ menu.title }}
    </template>
  </el-menu-item>
</template>

<script lang="ts" setup>
defineOptions({
  name: "MenuItem",
});

defineProps({
  menu: Object as any,
});

const Router = useRouter();

function menuClick(menu: any) {
  Router.push({
    path: menu.path,
  });
}
</script>

<style lang="scss" scoped>
::v-deep(.el-sub-menu__title) {
  background-color: #e7e8eb;
  justify-content: center !important;
  padding: 0 !important;
}

::v-deep(.el-sub-menu__icon-arrow) {
  display: none !important;
}
::v-deep(.el-menu-tooltip__trigger) {
  padding: 0;
  justify-content: center !important;
}
.el-menu-item {
  padding: 0 !important;
  justify-content: center;
}
.icon {
  margin: 0 6px;
  width: 18px;
  height: 18px;
}
</style>
