<!-- 消息 -->
<template>
  <div class="">
    <u-tabs :list="tabs"></u-tabs>
  </div>
</template>

<script lang="ts" setup>
const tabs = ref([
  {
    name: "求和",
    value: "求和",
  },
  {
    name: "求差",
    value: "求差",
  },
]);
</script>

<style lang="scss" scoped></style>
