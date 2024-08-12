<!--  -->
<template>
  <c-tabs
    type="border-card"
    v-if="prop.tableOption.tableConfig && prop.tableOption.tableConfig.length"
    @tabChange="tabChange"
    v-model="active"
    :options="prop.tableOption.tableConfig"
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
const prop = defineProps({
  tableOption: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

const tableRef: any = ref<any>([]);
const active: any = ref(0);

//循环ref获取
function setTableRef(el: any, index: number) {
  unref(tableRef)[unref(index)] = el;
}

function tabChange() {
  console.log(123);
}
</script>

<style lang="scss" scoped></style>
