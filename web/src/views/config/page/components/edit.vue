<!--  -->
<template>
  <c-tables v-model="active" :tableConfig="editableTabs">
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
  </c-tables>
</template>

<script lang="ts" setup>
const pageData: any = defineModel();
const tableRef = ref();
const active = ref(0);
const tableName: any = ref("");

const { dict } = useDict({
  dictList: DictService("dictList"),
  tableList: DictService("getTableList"),
});

// const str = "[{attr:1}]";

// console.log(eval(str));

// const sum = new Function("a", "const c = 5;return a + c");

// console.log(sum(123));

const editableTabs: any = computed(() => {
  return unref(pageData).tables.map((item: any, index: number) => {
    return {
      title: item.title,
      name: item.name,
      isDrop: true,
      tools: [
        // {
        //   slot: "tools_tableImport",
        // },
        {
          text: "按表导入",
          click() {
            Service.config.table
              .getTableColumn({
                tableName: unref(tableName),
              })
              .then((res: any) => {
                if (res.data.length) {
                  item.dialogForm.unshift(
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
            // unref(tableRef)[index].addLine();
          },
        },
        {
          operation: "remove",
          click() {
            const checkboxData = unref(tableRef)[unref(index)].checkboxData();

            const data = item.dialogForm.filter((item: any) => {
              const has = checkboxData.every(
                (i: any) => i._row_index !== item._row_index
              );

              return has;
            });

            item.dialogForm.splice(0);

            item.dialogForm.push(...data);
          },
        },
      ],
      actions: [
        {
          operation: "operate",
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
            type: "input",
            params: {},
          },
        },
        {
          title: "是否必填", //基本宽度
          field: "rules",
          sortable: true,
          isFilters: true,
          form: {
            type: "select",
            params: {},
          },
        },
        {
          title: "操作",
          cType: "action",
          fixed: "right",
        },
      ],
      data: item.dialogForm,
    };
  });
});
</script>

<style lang="scss" scoped></style>
