<!--  -->
<template>
  <el-button v-bind="$attrs" :size="size" v-show="show">
    {{ text }}
  </el-button>
</template>

<script lang="ts" setup>
defineProps({
  text: {
    text: "按钮名称",
    type: [String],
    default: () => {
      return "";
    },
  },
  hasPermission: {
    text: "权限列表",
    type: [Array],
    default: () => {
      return [];
    },
  },
  size: {
    text: "按钮大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  show: {
    text: "是否显示",
    type: [Boolean, Function],
    default: () => {
      return true;
    },
  },
});
</script>

<style lang="scss" scoped></style>
