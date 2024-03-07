<!--  -->
<template>
  <div class="container">
    <ul class="list">
      <li
        class="item"
        v-for="(item, index) in list"
        :key="index"
        @click="handleCheck(item)"
      >
        {{ item.label }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { Normal, Left, Right, Top } from "../container";
const container = defineModel();

const list = ref<any>([
  { label: "左侧菜单", value: markRaw(Left) },
  { label: "顶部菜单", value: markRaw(Top) },
  { label: "右菜单", value: markRaw(Right) },
  { label: "常规", value: markRaw(Normal) },
]);

function handleCheck(item: any) {
  container.value = item.value;
}
</script>

<style lang="scss" scoped>
.list {
  display: flex;
  .item {
    margin: 0 5px;
    width: 50px;
    height: 50px;
    border-radius: 3px;
    border: 1px solid rgb(0, 255, 21);
  }

  .item:hover {
    cursor: pointer;
    border: 1px solid blue;
  }
}
</style>
