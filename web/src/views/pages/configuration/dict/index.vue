<!-- 字典 dict -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
const pageData: any = ref({
  queryData: {},
  editData: {},
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
          label: $t("dict.status", "状态"),
          prop: "status",
          type: "input",
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
            permission: ["dict.add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: ["dict.remove"],
            click() {
              Service.dict.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["dict.import"],
            api(files: any) {
              return Service.dict.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["dict.export"],
            api() {
              return Service.dict.exports(unref(pageData).queryData);
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
            title: $t("dict.label", "名"),
            field: "label",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.value", "值"),
            field: "value",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.color", "自定义颜色"),
            field: "color",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.sort", "排序"),
            field: "sort",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.params", "其他参数"),
            field: "params",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.status", "状态"),
            field: "status",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.remark", "备注"),
            field: "remark",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.creator", "创建人"),
            field: "creator",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.updater", "更新人"),
            field: "updater",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.createTime", "创建时间"),
            field: "createTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("dict.updateTime", "更新时间"),
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
            permission: ["dict.edit"],
            click({ row }: any) {
              Service.dict.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "detail",
            permission: ["dict.detail"],
            click({ row }: any) {
              Service.dict.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "remove",
            permission: ["dict.remove"],
            click({ row }: any) {
              Service.dict.remove([row.id]).then((res: any) => {
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
                label: $t("dict.status", "状态"),
                prop: "status",
                type: "input",
                span: 6,
              },
              {
                label: $t("dict.remark", "备注"),
                prop: "remark",
                type: "input",
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            Service.dict.save(unref(pageData).editData).then((res: any) => {
              submitSuccess(res);
            });
          },
        },
        query: (pages: any) => {
          return Service.dict.page({ ...pages }).then((res: any) => {
            return res;
          });
        },
      },
    ],
  });
</script>
<style lang="scss" scoped></style>
