<!--  -->
<template>
  <div class="headerLine" :style="{ width: `calc(100% - ${width}px)` }">
    <HeaderMenu :backgroundColor="backgroundColor"></HeaderMenu>
    <div>工具栏</div>
  </div>
</template>

<script lang="ts" setup>
import HeaderMenu from "./components/HeaderMenu.vue";

defineProps({
  backgroundColor: {
    text: "背景颜色",
    type: [String],
  },
  width: {
    text: "宽度",
    type: [Number],
  },
});
</script>

<style lang="scss" scoped>
.headerLine {
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
