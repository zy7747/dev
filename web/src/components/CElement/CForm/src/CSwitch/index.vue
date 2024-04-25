<!--  -->
<template>
  <el-switch v-model="value" :active-icon="Check" :inactive-icon="Close" />
</template>

<script lang="ts" setup>
const value: any = defineModel();
import { Check, Close } from "@element-plus/icons-vue";
</script>

<style lang="scss" scoped></style>
