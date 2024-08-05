<!-- select -->
<template>
  <el-select-v2
    v-model="value"
    :options="option"
    v-bind="$attrs"
    :placeholder="placeholder"
    :filterable="filterable"
    :clearable="clearable"
    :size="size"
    :style="`width:${width};`"
  />
</template>

<script lang="ts" setup>
const prop = defineProps({
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
  options: {
    text: "选项列表",
    type: [Array, Object, Function, String] as any,
    default: () => {
      return [];
    },
  },
});

const value: any = defineModel();

const option = computed(() => {
  if (typeof prop.options === "function") {
    return prop.options();
  } else if (typeof prop.options === "object") {
    return prop.options;
  }
});
</script>

<style lang="scss" scoped></style>
