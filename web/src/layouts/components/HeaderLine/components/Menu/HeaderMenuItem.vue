<!--  -->
<template>
  <el-sub-menu v-if="menu.children && menu.children.length" :index="menu.path">
    <template #title>
      <svg-icon :name="menu.icon" class="icon" />
      {{ menu.title }}
    </template>

    <template v-for="item in menu.children">
      <HeaderMenuItem :menu="item"></HeaderMenuItem>
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
  name: "HeaderMenuItem",
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
::v-deep(.el-sub-menu.is-active .el-sub-menu__title) {
  border-bottom: none !important;
}

::v-deep(.el-sub-menu__icon-arrow) {
  display: none !important;
}

.icon {
  margin: 0 6px;
  width: 18px;
  height: 18px;
}
</style>
