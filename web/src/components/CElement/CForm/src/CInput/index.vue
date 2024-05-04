<!-- input -->
<template>
  <el-input
    v-model="value"
    v-bind="$attrs"
    :size="size"
    :style="`width:${width};`"
    :placeholder="placeholder"
    :clearable="clearable"
    :suffix-icon="suffixIcon"
  />
</template>

<script lang="ts" setup>
import { Edit } from "@element-plus/icons-vue";

defineProps({
  suffixIcon: {
    text: "图标",
    type: [Object] as any,
    default: () => {
      return Edit;
    },
  },
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
      return $t("form.please input", "请输入");
    },
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
