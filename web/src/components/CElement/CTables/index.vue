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
      >
        <template v-slot:[item.slot]>
          <slot :name="item.slot" />
        </template>

        <template v-for="i in item.tools" v-slot:[i.slot]>
          <slot :name="i.slot" />
        </template>
      </c-table>

      <slot :name="item.slot" :item="item" :index="index" />
    </template>
  </c-tabs>
</template>

<script lang="ts" setup>
const emit = defineEmits(["tabChange"]);
const active: any = defineModel();
const tableRef: any = ref([]);
const prop = defineProps({
  tableConfig: {
    text: "配置",
    type: [Array],
    default: () => {
      return [];
    },
  },
});

//循环ref获取
function setTableRef(el: any, index: number) {
  unref(tableRef)[index] = el;
}

//新增
function addLine(row: any) {
  unref(tableRef)[unref(active)].addLine(row);
}

function query() {
  return unref(tableRef)[unref(active)].query();
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
  query,
});
</script>

<style lang="scss" scoped></style>
