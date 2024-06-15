<!--  -->
<template>
  <el-date-picker
    v-model="value"
    v-bind="$attrs"
    :placeholder="placeholder"
    :size="size"
  />
</template>

<script lang="ts" setup>
defineProps({
  size: {
    text: "大小",
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
  placeholder: {
    text: "提示语句",
    type: [String],
    default: () => {
      return $t("form.please select", "请选择");
    },
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
