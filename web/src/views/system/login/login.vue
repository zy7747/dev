<!--  -->
<template>
  <Draggable
    class="fixed login flex justify-center"
    :initial-value="{ x: innerWidth / 1.5, y: 180 }"
    :prevent-default="true"
    :handle="handle"
  >
    <div ref="handle" class="handle" />
    <loginBox />
  </Draggable>
</template>

<script lang="ts" setup>
import { UseDraggable as Draggable } from "@vueuse/components";
import loginBox from "./loginBox.vue";

const innerWidth = window.innerWidth;
const handle = ref<HTMLElement | null>(null);
</script>

<style lang="scss" scoped>
.login {
  height: 500px;
  width: 400px;
  position: relative;
  padding: 0 15px;
  background: #f6f5f5;
  border-radius: 5px;
  overflow: hidden;
  &:before {
    content: "";
    position: absolute;
    top: 5px;
    left: 5px;
    border-top: 36px solid #f6f5f5; /* 三角形高度 */
    border-right: 36px solid #f6f5f5; /* 三角形颜色 */
    z-index: 1;
    clip-path: polygon(100% 100%, 0% 100%, 0% 100%, 120% 0%);
  }

  .handle {
    z-index: 1;
    position: absolute;
    width: 80%;
    height: 50px;
    cursor: grab;
  }
}
</style>
