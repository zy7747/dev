<template>
  <c-table ref="tableRef" :tableConfig="tableConfig">
    <template #tools_tableImport>
      <div style="width: 250px" class="btnR">
        <c-schema
          :params="{ size: 'small' }"
          type="select"
          :options="() => dict.tableList"
          v-model="tableName"
        />
      </div>
    </template>
  </c-table>
</template>

<script lang="ts" setup>
const pageData: any = defineModel();
const tableName: any = ref("");

const { dict } = useDict({
  dictList: DictService("dictList"),
  tableList: DictService("getTableList"),
});

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
      field: "label",
      sortable: true,
      isFilters: true,
      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
      form: {
        type: "input",
        params: {},
      },
    },
    {
      title: "字段值", //字段值
      field: "prop",
      sortable: true,
      isFilters: true,
      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
      form: {
        type: "input",
        params: {},
      },
    },
    {
      title: "类型", //字段值
      field: "type",
      sortable: true,
      isFilters: true,

      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
      form: {
        type: "select",
        params: {
          options: Dict("form_type"),
        },
      },
    },
    {
      title: "字典", //值集
      field: "options",
      sortable: true,
      isFilters: true,
      form: {
        type: "select",
        params: {
          options: () => dict.dictList,
        },
      },
    },
    {
      title: "表单宽度", //基本宽度
      field: "span",
      sortable: true,
      isFilters: true,
      form: {
        type: "number",
        params: {
          max: 24,
          min: 0,
        },
      },
    },
    {
      title: "是否必填", //基本宽度
      field: "rules",
      sortable: true,
      isFilters: true,

      translate: "isNo",
      form: {
        type: "select",
        params: {
          options: Dict("isNo"),
        },
      },
    },
    {
      title: "操作",
      cType: "action",
      fixed: "right",
    },
  ];
};

const { tableConfig, tableRef } = useTable({
  tools: [
    {
      slot: "tools_tableImport",
    },
    {
      text: "按表导入",
      click() {
        Service.configuration.table
          .getTableColumn({
            tableName: unref(tableName),
          })
          .then((res: any) => {
            if (res.data.length) {
              unref(pageData).form.unshift(
                ...res.data.map((item: any) => {
                  return {
                    prop: item.COLUMN_NAME,
                    label: item.COLUMN_COMMENT,
                    type: "input",
                  };
                })
              );

              console.log(unref(pageData).form);
            }
          });
      },
    },
    {
      operation: "add",
      click() {
        unref(tableRef).addLine({});
      },
    },
    {
      operation: "remove",
      click() {},
    },
  ],
  tableColumn: tableColumn(),
  actions: [
    {
      operation: "operate",
    },
  ],
  data: unref(pageData).form,
});
</script>

<style lang="scss" scoped></style>
