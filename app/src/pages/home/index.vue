<!--  -->
<template>
  <div class="">
    <up-input placeholder="请输入内容" border="surround" v-model="a"></up-input>
    <up-input placeholder="请输入内容" border="surround" v-model="b"></up-input>
    <up-button text="求和" @click="summation"></up-button>
    <div>{{ sum }}</div>
  </div>
</template>

<script lang="ts" setup>
const a = ref();
const b = ref();
const sum = ref();

function summation() {
  sum.value = Number(a.value) + Number(b.value);
}
</script>

<style lang="scss" scoped></style>
