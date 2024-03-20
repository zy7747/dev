<!--  -->
<template>
  <el-image
    v-if="type === 'img'"
    style="width: 50px; height: 50px; border-radius: 5px"
    :src="fileUrl + value"
    :zoom-rate="1.2"
    :max-scale="7"
    :min-scale="0.2"
    :preview-src-list="[fileUrl + value]"
    :initial-index="4"
    fit="cover"
  />

  <DictTag v-else-if="type === 'tag'" :options="options" v-model="value" />
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;

defineProps({
  type: {
    text: "类型",
    type: [String],
  },
  options: {
    text: "字典",
    type: [Array],
    default: () => {
      return [];
    },
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
