<!--  -->
<template>
  <el-pagination
    small
    background
    v-model:current-page="page"
    :page-size="size"
    :page-sizes="[20, 50, 100, 500, 1000]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script lang="ts" setup>
const emit = defineEmits(["query"]);
const prop = defineProps({
  total: {
    text: "条数",
    type: [Number],
  },
  pages: {
    text: "当页页码",
    type: [Number],
    default: 1,
  },
  sizes: {
    text: "条数",
    type: [Number],
    default: 20,
  },
});

const page = ref(prop.pages);
const size = ref(prop.sizes);

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
  size.value = prop.sizes;
}

defineExpose({
  page,
  size,
  reset,
});
</script>

<style lang="scss" scoped></style>
