<!--  -->
<template>
  <el-carousel @change="handleChange">
    <el-carousel-item v-for="item in options" :key="item">
      <slot name="content" :item="item" />
    </el-carousel-item>
  </el-carousel>
</template>

<script lang="ts" setup>
const active: any = ref<any>(0);

defineProps({
  options: {
    text: "list",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});

function handleChange(index: Number) {
  active.value = index;
}
</script>

<style lang="scss" scoped></style>
