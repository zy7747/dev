<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();

const { pageData, active } = defineProps({
  active: {
    text: "active",
    type: [Number] as any,
  },
  pageData: {
    text: "页面数据",
    type: [Object] as any,
  },
});

function getGrid() {
  unref(gridRef).render(pageData);
}

watch(
  active,
  () => {
    nextTick(() => {
      getGrid();
    });
  },
  { immediate: true }
);
</script>

<style lang="scss" scoped></style>
