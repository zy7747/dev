<!-- radio -->
<template>
  <el-radio-group v-model="value">
    <el-radio :label="item.value" :size="size" v-for="item in option">
      {{ item.label }}
    </el-radio>
  </el-radio-group>
</template>

<script lang="ts" setup>
const { options } = defineProps({
  size: {
    text: "单选框大小",
    type: [String] as any,
    default: () => {
      return "default";
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
  if (typeof options === "function") {
    return options();
  } else if (typeof options === "object") {
    return options;
  }
});
</script>

<style lang="scss" scoped></style>
