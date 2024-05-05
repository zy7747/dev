<!--  -->
<template>
  <el-pagination
    small
    background
    v-model:current-page="page"
    :page-size="size"
    :page-sizes="[10, 20, 50, 100, 500, 1000]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script lang="ts" setup>
const emit = defineEmits(["query"]);
defineProps({
  total: {
    text: "条数",
    type: [Number],
  },
});

const page = ref(1);
const size = ref(10);

function handleSizeChange(val: number) {
  size.value = val;
  emit("query");
}
function handleCurrentChange(val: number) {
  page.value = val;
  emit("query");
}

function reset() {
  page.value = 1;
  size.value = 10;
}

defineExpose({
  page,
  size,
  reset,
});
</script>

<style lang="scss" scoped></style>
