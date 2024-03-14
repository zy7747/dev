<!-- radio -->
<template>
  <el-radio-group v-model="value">
    <el-radio :label="item.value" :size="size" v-for="item in options">
      {{ item.label }}
    </el-radio>
  </el-radio-group>
</template>

<script lang="ts" setup>
defineProps({
  size: {
    text: "单选框大小",
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
