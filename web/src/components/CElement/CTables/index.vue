<!--  -->
<template>
  <c-tabs
    type="border-card"
    @tabChange="tabChange"
    v-model="active"
    :options="prop.tableConfig"
  >
    <template #content="{ item, index }">
      <c-table
        v-if="item.tableColumn"
        :ref="(el) => setTableRef(el, index)"
        :tableConfig="item"
      />

      <slot :name="item.slot" :item="item" :index="index" />
    </template>
  </c-tabs>
</template>

<script lang="ts" setup>
const emit = defineEmits(["tabChange"]);

const prop = defineProps({
  tableConfig: {
    text: "配置",
    type: [Array],
    default: () => {
      return [];
    },
  },
});

const tableRef: any = ref<any>([]);
const active: any = ref(0);

//循环ref获取
function setTableRef(el: any, index: number) {
  unref(tableRef)[unref(index)] = el;
}

//新增
function addLine(row: any) {
  unref(tableRef)[unref(active)].addLine(row);
}

//多选
function checkboxData() {
  return unref(tableRef)[unref(active)].checkboxData();
}

function tabChange() {
  emit("tabChange", active.value);
}

defineExpose({
  addLine,
  checkboxData,
});
</script>

<style lang="scss" scoped></style>
