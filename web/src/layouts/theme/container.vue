<!--  -->
<template>
  <el-radio-group v-model="container">
    <el-radio-button
      v-for="item in options"
      :label="item.label"
      :value="item.value"
      :key="item.value"
    />
  </el-radio-group>
</template>

<script lang="ts" setup>
const container: any = defineModel("container");

const options: any = ref([
  { label: "Left", value: "Left" },
  { label: "Top", value: "Top" },
  { label: "Right", value: "Right" },
  { label: "Normal", value: "Normal" },
]);
</script>

<style lang="scss" scoped></style>
