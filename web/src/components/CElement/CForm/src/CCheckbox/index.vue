<!--  -->
<template>
  <el-checkbox-group v-model="value">
    <el-checkbox :label="item.value" :size="size" v-for="item in options">
      {{ item.label }}
    </el-checkbox>
  </el-checkbox-group>
</template>

<script lang="ts" setup>
defineProps({
  size: {
    text: "多选框大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
});
const value: any = defineModel();
const options: any = defineModel("options");
</script>

<style lang="scss" scoped></style>
