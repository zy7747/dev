<!-- input -->
<template>
  <el-input
    v-model="value"
    v-bind="$attrs"
    :size="size"
    :style="`width:${width};`"
    :placeholder="placeholder"
  />
</template>

<script lang="ts" setup>
defineProps({
  size: {
    text: "下拉框大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  width: {
    type: [String],
    default: "100%",
  },
  placeholder: {
    text: "提示语句",
    type: [String],
    default: () => {
      return "请输入";
    },
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
