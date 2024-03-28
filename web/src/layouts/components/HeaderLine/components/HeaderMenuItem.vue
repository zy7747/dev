<!--  -->
<template>
  <el-sub-menu v-if="menu.children && menu.children.length" :index="menu.id">
    <template #title>
      <svg-icon name="app-哔哩哔哩" class="icon" />
      <el-text class="primary">{{ menu.title }}</el-text>
    </template>

    <template v-for="item in menu.children">
      <HeaderMenuItem :menu="item"></HeaderMenuItem>
    </template>
  </el-sub-menu>

  <el-menu-item v-else :persistent="false" :index="menu.id">
    <svg-icon name="app-哔哩哔哩" class="icon" />
    <template #title>
      <el-text class="primary">{{ menu.title }}</el-text>
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
</script>

<style lang="scss" scoped></style>
