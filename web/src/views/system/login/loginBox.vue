<!--  -->
<template>
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
</template>

<script lang="ts" setup>
import qrCode from "./components/qrCode.vue";
import SignIn from "./components/signIn.vue";
import SignUp from "./components/signUp.vue";

const show: any = ref("signIn");

function handleCheck(type: string) {
  show.value = type;
}
</script>

<style lang="scss" scoped>
.content {
  width: 100%;
  height: 100%;
  .left-box {
    top: 0;
    left: 0;
    position: absolute;
    width: 35px;
    height: 35px;
  }
}
</style>
