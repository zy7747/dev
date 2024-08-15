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

      <c-table :ref="(el) => setTableRef(el, index)" :tableConfig="table">
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
    </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
const pageData: any = defineModel();
const tableRef: any = ref<any>([]);
const tableName: any = ref("");
const { dict } = useDict({
  tableList: DictService("getTableList"),
});

const formConfig = ref({
  formParams: [
    {
      label: "tab名称",
      prop: "title",
      type: "input",
    },
    {
      label: "接口",
      prop: "api",
      type: "input",
    },
    {
      label: "权限",
      prop: "permissions",
      type: "input",
    },
  ],
});

//循环ref获取
function setTableRef(el: any, index: number) {
  unref(tableRef)[unref(index)] = el;
}

const editableTabs: any = computed(() => {
  return pageData.value.tables.map((item: any, index: number) => {
    return {
      title: item.title,
      name: item.name,
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
                  pageData.value.tables[
                    pageData.value.tabIndex
                  ].tableColumn.push(
                    ...res.data.map((item: any) => {
                      return {
                        title: item.COLUMN_COMMENT,
                        field: FormatData.toHump(item.COLUMN_NAME),
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
            unref(tableRef)[pageData.value.tabIndex].addLine({
              sort: unref(pageData).form.length,
            });
          },
        },
        {
          operation: "remove",
          click() {
            const checkboxData = unref(tableRef)[unref(index)].checkboxData();

            const data = item.tableColumn.filter((item: any) => {
              const has = checkboxData.every(
                (i: any) => i._row_index !== item._row_index
              );

              return has;
            });

            item.tableColumn.splice(0);

            item.tableColumn.push(...data);
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
          field: "title",
          sortable: true,
          isFilters: true,
        },
        {
          title: "字段值", //字段值
          field: "field",
          sortable: true,
          isFilters: true,
        },
        {
          title: "类型", //字段值
          field: "type",
          sortable: true,
          isFilters: true,
        },
        {
          title: "值集", //值集
          field: "options",
          sortable: true,
          isFilters: true,
        },
        {
          title: "是否需要校验必填", //是否需要校验必填
          field: "rules",
          sortable: true,
          isFilters: true,
        },
        {
          title: "是否需要筛选", //是否需要筛选
          field: "isFilters",
          sortable: true,
          isFilters: true,
        },
        {
          title: "是否需要排序", //是否需要排序
          field: "sortable",
          sortable: true,
          isFilters: true,
        },
        {
          title: "表格栏位宽度", //基本宽度
          field: "width",
          sortable: true,
          isFilters: true,
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
      data: item.tableColumn,
    };
  });
});

const handleTabsEdit = (_targetName: any, action: "remove" | "add") => {
  if (action === "add") {
    const len = editableTabs.value.length;
    const newTabName = `${len ? len : 0}`;

    unref(pageData).tables.push({
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
