<!--  -->
<template>
  <el-pagination
    small
    background
    v-model:current-page="page"
    :page-size="size"
    :page-sizes="[100, 200, 300, 400]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script lang="ts" setup>
defineProps({
  total: {
    text: "条数",
    type: [Number],
  },
});

const page = ref(1);
const size = ref(100);

function handleSizeChange(val: number) {
  size.value = val;
}
function handleCurrentChange(val: number) {
  page.value = val;
}

defineExpose({
  page,
  size,
});
</script>

<style lang="scss" scoped></style>
