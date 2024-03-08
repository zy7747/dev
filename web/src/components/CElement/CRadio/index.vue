<!-- select -->
<template>
  <el-radio-group v-model="value" class="ml-4">
    <el-radio :value="item.value" size="large" v-for="item in options">
      {{ item.label }}
    </el-radio>
  </el-radio-group>
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
