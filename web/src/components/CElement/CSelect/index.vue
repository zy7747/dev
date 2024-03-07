<!-- select -->
<template>
  <el-select-v2
    v-model="value"
    :options="options"
    v-bind="$attrs"
    :placeholder="placeholder"
    :filterable="filterable"
    :clearable="clearable"
    :size="size"
    :style="`width:${width};`"
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
  clearable: {
    text: "可清除",
    type: [Boolean],
    default: () => {
      return true;
    },
  },
  filterable: {
    text: "筛选",
    type: [Boolean],
    default: () => {
      return true;
    },
  },
  placeholder: {
    text: "提示语句",
    type: [String],
    default: () => {
      return "请选择";
    },
  },
});

const value: any = defineModel();
const options: any = defineModel("options");
</script>

<style lang="scss" scoped></style>
