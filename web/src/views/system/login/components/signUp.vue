<!--  -->
<template>
  <div class="">
    注册页
    <c-button @click="handleCheck" text="返回登录"></c-button>
  </div>
</template>

<script lang="ts" setup>
const show: any = defineModel();

function handleCheck() {
  show.value = "signIn";
}
</script>

<style lang="scss" scoped></style>
