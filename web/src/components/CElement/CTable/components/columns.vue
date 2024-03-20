<!--  -->
<template>
  <el-avatar
    v-if="type === 'img'"
    shape="square"
    :size="50"
    :src="fileUrl + value"
  />
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;

defineProps({
  type: {
    text: "类型",
    type: [String],
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
