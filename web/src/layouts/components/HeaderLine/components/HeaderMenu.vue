<!--  -->
<template>
  <el-menu mode="horizontal" :background-color="backgroundColor">
    <template v-for="item in list">
      <HeaderMenuItem :menu="item"></HeaderMenuItem>
    </template>
  </el-menu>
</template>

<script lang="ts" setup>
import HeaderMenuItem from "./HeaderMenuItem.vue";

defineProps({
  backgroundColor: {
    text: "背景颜色",
    type: [String],
    default: "#FFF",
  },
});

const list: any = [];
</script>

<style lang="scss" scoped></style>
