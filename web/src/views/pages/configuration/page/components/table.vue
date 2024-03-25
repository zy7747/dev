<!--  -->
<template>
  <el-tabs
    v-model="pageData.tabIndex"
    type="card"
    editable
    @edit="handleTabsEdit"
  >
    <el-tab-pane
      v-for="(table, index) in editableTabs"
      :key="table.name"
      :label="table.title"
      :name="table.name"
    >
      <c-form :model="pageData.tables[index]" :formConfig="formConfig" />
      <c-table ref="tableRef" :tableConfig="table" />
    </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
import type { TabPaneName } from "element-plus";
const pageData: any = defineModel();
const tableRef = ref();

const formConfig = ref({
  formParams: [
    {
      label: "tab名称",
      prop: "title",
      type: "input",
      span: 6,
    },
    {
      label: "接口",
      prop: "api",
      type: "input",
      span: 6,
    },
    {
      label: "权限",
      prop: "permissions",
      type: "input",
      span: 6,
    },
  ],
});
//新增列
const tableColumn = () => {
  return [
    { type: "checkbox", width: 50 },
    {
      title: "序号",
      type: "seq",
      width: 100,
    },
    {
      title: "字段名称", //字段名称
      field: "title",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "字段值", //字段值
      field: "field",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "类型", //字段值
      field: "type",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "值集", //值集
      field: "options",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要校验必填", //是否需要校验必填
      field: "rules",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要筛选", //是否需要筛选
      field: "isFilters",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要排序", //是否需要排序
      field: "sortable",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "表格栏位宽度", //基本宽度
      field: "width",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "操作",
      cType: "action",
      fixed: "right",
      width: 250,
    },
  ];
};

const editableTabs: any = computed(() => {
  return pageData.value.tables.map((item: any) => {
    return {
      title: item.title,
      name: item.name,
      tools: [
        {
          operation: "add",
          click() {
            unref(tableRef)[pageData.value.tabIndex].addLine();
          },
        },
      ],
      actions: [
        {
          operation: "operate",
        },
      ],
      tableColumn: tableColumn(),
      data: item.tableColumn,
    };
  });
});

const handleTabsEdit = (
  _targetName: TabPaneName | undefined,
  action: "remove" | "add"
) => {
  if (action === "add") {
    const len = editableTabs.value.length;
    const newTabName = `${len ? len : 0}`;

    pageData.value.tables.push({
      title: "New Tab",
      name: newTabName,
      api: "user",
      tableColumn: [],
      dialogForm: [],
    });

    pageData.value.tabIndex = newTabName;
  }
};
</script>

<style lang="scss" scoped></style>
