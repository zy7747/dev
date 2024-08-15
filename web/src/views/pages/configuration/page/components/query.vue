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

const { tableConfig, findCheckDataIndex, tableRef } = useTable({
  isDrop: true,
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
                ...res.data.map((item: any, index: any) => {
                  return {
                    prop: FormatData.toHump(item.COLUMN_NAME),
                    label: item.COLUMN_COMMENT,
                    type: "input",
                    sort: index,
                    span: 6,
                    require: 0,
                  };
                })
              );
            }
          });
      },
    },
    {
      operation: "add",
      click() {
        unref(tableRef).addLine({
          span: 6,
          type: "input",
          require: 0,
          sort: unref(pageData).form.length,
        });
      },
    },
    {
      operation: "remove",
      click() {
        const data = findCheckDataIndex(unref(pageData).form);

        unref(pageData).form.splice(0);

        unref(pageData).form.push(...data);
      },
    },
  ],
  tableColumn: [
    { type: "checkbox", width: 50 },
    {
      title: "序号", //基本宽度
      field: "sort",
      sortable: true,
      width: 100,
      form: {
        type: "number",
        params: {
          max: 100,
          min: 0,
        },
      },
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
      translate: "form_type",
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
      translate: "dictList",
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
      field: "require",
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
  ],
  actions: [
    {
      operation: "operate",
    },
  ],
  data: unref(pageData).form,
});
</script>

<style lang="scss" scoped></style>
