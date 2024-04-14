<!-- 字典 dict -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse title="表格" v-if="pageData.editData.id">
        <template #content>
          <c-table ref="tableRef" :tableConfig="tableConfig" />
        </template>
      </Collapse>
    </template>
  </c-page>
</template>
<script lang="ts" setup>
defineOptions({
  name: "Dict",
});

const pageData: any = reactive({
  queryData: {},
  editData: {},
  dictList: [],
});

const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("dict.dict", "字典"),
    formConfig: {
      formParams: [
        {
          label: $t("dict.dictName", "字典名称"),
          prop: "dictName",
          type: "input",
          span: 6,
        },
        {
          label: $t("dict.dictCode", "字典编码"),
          prop: "dictCode",
          type: "input",
          span: 6,
        },
        {
          label: $t("table.status", "状态"),
          prop: "status",
          type: "select",
          options: Dict("code"),
          span: 6,
        },
      ],
    },
    tableConfig: [
      {
        title: $t("dict.dict", "字典"),
        tools: [
          {
            operation: "add",
            permission: ["dict:add"],
            click() {
              pageData.dictList.splice(0);
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: ["dict:remove"],
            click() {
              Service.configuration.dict.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["dict:import"],
            api(files: any) {
              return Service.configuration.dict.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["dict:export"],
            api() {
              return Service.configuration.dict.exports(pageData.queryData);
            },
            fileName: $t("dict.dict", "字典"),
          },
        ],
        tableColumn: [
          { type: "checkbox", width: 50, fixed: "left" },
          {
            title: $t("system.no", "序号"),
            type: "seq",
            width: 100,
            fixed: "left",
          },
          {
            title: $t("dict.dictName", "字典名称"),
            field: "dictName",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.dictCode", "字典编码"),
            field: "dictCode",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.dictType", "字典类型"),
            field: "dictType",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.status", "状态"),
            field: "status",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.creator", "创建人"),
            field: "creator",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.updater", "更新人"),
            field: "updater",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.createTime", "创建时间"),
            field: "createTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.updateTime", "更新时间"),
            field: "updateTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("system.action", "操作"),
            cType: "action",
            fixed: "right",
            width: 250,
          },
        ],
        actions: [
          {
            operation: "edit",
            permission: ["dict:edit"],
            click({ row }: any) {
              Service.configuration.dict
                .detail({ id: row.id })
                .then((res: any) => {
                  pageData.dictList.splice(0);
                  nextTick(() => {
                    pageData.dictList.push(...res.data.dictList);
                  });

                  unref(pageRef).handleOpen({
                    type: "edit",
                    data: res.data,
                  });
                });
            },
          },
          {
            operation: "detail",
            click({ row }: any) {
              Service.configuration.dict
                .detail({ id: row.id })
                .then((res: any) => {
                  unref(pageRef).handleOpen({
                    type: "detail",
                    data: res.data,
                  });
                });
            },
          },
          {
            operation: "remove",
            permission: ["dict:remove"],
            click({ row }: any) {
              Service.configuration.dict.remove([row.id]).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
        ],
        dialogConfig: {
          width: "1000px",
          formConfig: {
            formParams: [
              {
                label: $t("dict.dictName", "字典名称"),
                prop: "dictName",
                type: "input",
                span: 6,
              },
              {
                label: $t("dict.dictCode", "字典编码"),
                prop: "dictCode",
                type: "input",
                span: 6,
              },
              {
                label: $t("dict.dictType", "字典类型"),
                prop: "dictType",
                type: "input",
                span: 6,
              },
              {
                label: $t("table.status", "状态"),
                prop: "status",
                type: "input",
                span: 6,
              },
            ],
            actions: [{}],
          },
          //提交
          handleConfirm() {
            let dictList = [];

            if (pageData.dictList.length) {
              dictList = pageData.dictList.map((item: any) => {
                return {
                  ...item,
                  parentId: pageData.editData.id,
                  code: pageData.editData.code,
                };
              });
            }

            Service.configuration.dict
              .saveList([pageData.editData, ...dictList])
              .then((res: any) => {
                submitSuccess(res);
              });
          },
        },
        query: (pages: any) => {
          return Service.configuration.dict
            .page({ ...pages, ...pageData.queryData })
            .then((res: any) => {
              return res;
            });
        },
      },
    ],
  });

const { tableConfig, tableRef } = useTable({
  tools: [
    {
      operation: "add",
      click() {
        unref(tableRef).addLine();
      },
    },
    {
      operation: "remove",
      click() {},
    },
  ],
  tableColumn: [
    { type: "checkbox", width: 50, fixed: "left" },
    {
      title: $t("system.no", "序号"),
      type: "seq",
      width: 100,
      fixed: "left",
    },
    {
      title: $t("dict.label", "名"),
      field: "label",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: $t("dict.value", "值"),
      field: "value",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: $t("dict.color", "自定义颜色"),
      field: "color",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: $t("dict.sort", "排序"),
      field: "sort",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: $t("dict.params", "其他参数"),
      field: "params",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: $t("table.status", "状态"),
      field: "status",
      isFilters: true,
      width: 200,
      form: {
        type: "input",
      },
    },
    {
      title: "操作",
      cType: "action",
      fixed: "right",
      width: 250,
    },
  ],
  actions: [
    {
      operation: "operate",
      remove(id: string) {
        Service.configuration.dict.remove([id]).then(() => {
          Service.configuration.dict
            .detail({ id: pageData.editData.id })
            .then((res: any) => {
              pageData.dictList.splice(0);
              nextTick(() => {
                pageData.dictList.push(...res.data.dictList);
              });
            });
        });
      },
    },
  ],
  data: pageData.dictList,
});
</script>
<style lang="scss" scoped></style>
