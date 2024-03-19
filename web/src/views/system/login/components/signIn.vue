<!--  -->
<template>
  <div class="">
    登录页
    <c-button @click="handleCheck" text="去注册"></c-button>
  </div>
</template>

<script lang="ts" setup>
const show: any = defineModel();

function handleCheck() {
  show.value = "signUp";
}
</script>

<style lang="scss" scoped></style>
