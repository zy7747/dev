<!--  -->
<template>
  <el-tag v-if="dict.color && dict.color != 'default'" :type="dict.color">
    {{ dict.label }}
  </el-tag>
  <span v-else>{{ dict.label }}</span>
</template>

<script lang="ts" setup>
const value: any = defineModel();
const { params } = defineProps({
  params: {
    text: "列参",
    type: Object as any,
  },
});

const dict: any = getDictTag(params.translate, unref(value));
</script>

<style lang="scss" scoped></style>
