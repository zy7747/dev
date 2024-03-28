<!--  -->
<template>
  <el-sub-menu v-if="menu.children && menu.children.length" :index="menu.id">
    <template #title>
      <svg-icon :name="'app-' + menu.icon" class="icon" />
      <el-text class="primary">{{ menu.title }}</el-text>
    </template>

    <template v-for="item in menu.children">
      <MenuItem :menu="item"></MenuItem>
    </template>
  </el-sub-menu>

  <el-menu-item
    @click="() => menuClick(menu)"
    v-else
    :persistent="false"
    :index="menu.id"
  >
    <svg-icon :name="'app-' + menu.icon" class="icon" />
    <template #title>
      <el-text class="primary">{{ menu.title }}</el-text>
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

const router = useRouter();

function menuClick(menu: any) {
  router.push({
    path: menu.path,
  });
}
</script>

<style lang="scss" scoped></style>
