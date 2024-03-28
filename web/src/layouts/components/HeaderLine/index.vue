<!--  -->
<template>
  <div
    class="flex justify-between items-center"
    :style="{ width: `calc(100% - ${width}px)` }"
  >
    <HeaderMenu :backgroundColor="backgroundColor"></HeaderMenu>
    <div class="tools">
      <Language />
    </div>
  </div>
</template>

<script lang="ts" setup>
import HeaderMenu from "./components/HeaderMenu.vue";
import Language from "./components/Language.vue";

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
.tools {
  padding-right: 15px;
}
</style>
