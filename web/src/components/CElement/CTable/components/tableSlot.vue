<!--  -->
<template>
  <div class="">
    <div v-if="type === 'img'">
      {{ "图片" + value }}
    </div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  type: {
    text: "类型",
    type: [String],
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
