<!--  -->
<template>
  <div
    class="flex justify-between items-center"
    :style="{
      width: `calc(100% - ${width}px)`,
      overflow: 'hidden',
      color: 'white',
    }"
  >
    <div class="flex items-center">
      <HeaderMenu />
      <Search />
    </div>

    <ul class="tools">
      <li><Message /></li>
      <li><History /></li>
      <li><Collection /></li>
      <li><Language /></li>
      <li><UserInfo /></li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import {
  HeaderMenu,
  Language,
  UserInfo,
  Message,
  Collection,
  Search,
  History,
} from "./components/index.ts";

defineProps({
  width: {
    text: "宽度",
    type: [Number],
  },
});
</script>

<style lang="scss" scoped>
.tools {
  display: flex;
  justify-content: center;
  align-items: center;
  color: var(--el-text-color-regular);
  li {
    margin-right: 20px;
  }
}
</style>
