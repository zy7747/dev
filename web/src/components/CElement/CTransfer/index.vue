<!--  -->
<template>
  <el-transfer v-model="value" :data="options" />
</template>

<script lang="ts" setup>
const value: any = defineModel();
const options: any = defineModel("options");
</script>

<style lang="scss" scoped></style>
