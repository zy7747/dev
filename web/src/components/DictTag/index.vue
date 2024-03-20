<!--  -->
<template>
  <div class="">
    <el-tag type="primary">{{ value }}</el-tag>
  </div>
</template>

<script lang="ts" setup>
const value: any = defineModel();
const options: any = defineModel("options");
</script>

<style lang="scss" scoped></style>
