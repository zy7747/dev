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
.icon {
  margin: 0 5px;
  width: 17px;
  height: 17px;
}
</style>
