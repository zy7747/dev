<!-- 配置页面 page -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Page",
});
const pageData: any = ref({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("page.page", "配置页面"),
    formConfig: {
      formParams: [
        {
          label: $t("page.menuId", "页面id"),
          prop: "menuId",
          type: "input",
          span: 6,
        },
      ],
    },
    tableConfig: [
      {
        title: $t("page.page", "配置页面"),
        tools: [
          {
            operation: "add",
            permission: ["configuration:page:add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: ["configuration:page:remove"],
            click() {
              Service.configuration.page.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["configuration:page:import"],
            api(files: any) {
              return Service.configuration.page.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["configuration:page:export"],
            api() {
              return Service.configuration.page.exports(
                unref(pageData).queryData
              );
            },
            fileName: $t("page.page", "配置页面"),
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
            title: $t("page.menuId", "页面id"),
            field: "menuId",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.status", "状态"),
            field: "status",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.remark", "备注"),
            field: "remark",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.creator", "创建人"),
            field: "creator",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.updater", "更新人"),
            field: "updater",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.createTime", "创建时间"),
            field: "createTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("page.updateTime", "更新时间"),
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
            permission: ["configuration:page:edit"],
            click({ row }: any) {
              Service.configuration.page
                .detail({ id: row.id })
                .then((res: any) => {
                  unref(pageRef).handleOpen({
                    type: "edit",
                    data: res.data,
                  });
                });
            },
          },
          {
            operation: "detail",
            permission: ["configuration:page:detail"],
            click({ row }: any) {
              Service.configuration.page
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
            permission: ["configuration:page:remove"],
            click({ row }: any) {
              Service.configuration.page.remove([row.id]).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
        ],
        dialogConfig: {
          width: "1500px",
          formConfig: {
            formParams: [
              {
                label: $t("page.menuId", "页面id"),
                prop: "menuId",
                type: "input",
                span: 6,
              },
              {
                label: $t("page.options", "页面配置"),
                prop: "options",
                type: "input",
                span: 6,
              },
              {
                label: $t("page.status", "状态"),
                prop: "status",
                type: "input",
                span: 6,
              },
              {
                label: $t("page.remark", "备注"),
                prop: "remark",
                type: "input",
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            Service.configuration.page
              .save(unref(pageData).editData)
              .then((res: any) => {
                submitSuccess(res);
              });
          },
        },
        query: (pages: any) => {
          return Service.configuration.page
            .page({ ...pages })
            .then((res: any) => {
              return res;
            });
        },
      },
    ],
  });
</script>
<style lang="scss" scoped></style>
