<!--  -->
<template>
  <Draggable
    class="fixed login flex justify-center"
    :initial-value="{ x: innerWidth / 1.5, y: 180 }"
    :prevent-default="true"
    :handle="handle"
  >
    <div ref="handle" class="handle" />
    <ul class="content flex justify-center items-center">
      <li v-if="show === 'qrCode'">
        <a href="JavaScript:void(0)" @click="handleCheck('signIn')">
          <svg-icon :name="`system-电脑`" class="left-box" />
        </a>
        <qrCode />
      </li>

      <li v-else-if="show === 'signIn'">
        <a href="JavaScript:void(0)" @click="handleCheck('qrCode')">
          <svg-icon :name="`system-二维码`" class="left-box" />
        </a>
        <SignIn v-model="show" />
      </li>

      <li v-else-if="show === 'signUp'"><SignUp v-model="show" /></li>
    </ul>
  </Draggable>
</template>

<script lang="ts" setup>
import { UseDraggable as Draggable } from "@vueuse/components";

import qrCode from "./components/qrCode.vue";
import SignIn from "./components/signIn.vue";
import SignUp from "./components/signUp.vue";

const innerWidth = window.innerWidth;
const handle = ref<HTMLElement | null>(null);
const show: any = ref("signIn");

function handleCheck(type: string) {
  show.value = type;
}
</script>

<style lang="scss" scoped>
.login {
  height: 500px;
  width: 400px;
  position: relative;
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

  .content {
    width: 80%;
    height: 100%;
    .left-box {
      top: 0;
      left: 0;
      position: absolute;
      width: 35px;
      height: 35px;
    }
  }
}
</style>
