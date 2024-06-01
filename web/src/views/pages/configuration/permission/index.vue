<!-- 权限 permission -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Permission",
});
const pageData: any = reactive({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("permission.permission", "权限"),
    formConfig: {
      formParams: [
        {
          label: $t("permission.name", "权限名称"),
          prop: "name",
          type: "input",
        },
        {
          label: $t("permission.permission", "权限标识"),
          prop: "permission",
          type: "input",
        },
        {
          label: $t("permission.type", "类型"),
          prop: "type",
          type: "select",
          options: Dict("permission_type"),
        },
      ],
    },
    tableConfig: [
      {
        title: $t("permission.permission", "权限"),
        tools: [
          {
            operation: "add",
            permission: ["permission:add"],
            click() {
              unref(pageRef).handleOpen({
                type: "add",
                data: {
                  type: "fun",
                },
              });
            },
          },
          {
            operation: "remove",
            permission: ["permission:remove"],
            click() {
              return Service.configuration.permission
                .remove(ids())
                .then((res: any) => {
                  removeSuccess(res);
                });
            },
          },
          {
            operation: "import",
            permission: ["permission:import"],
            api(files: any) {
              return Service.configuration.permission
                .imports(files)
                .then(() => {
                  query();
                });
            },
          },
          {
            operation: "export",
            permission: ["permission:export"],
            api() {
              return Service.configuration.permission.exports(
                unref(pageData).queryData
              );
            },
            fileName: $t("permission.permission", "权限"),
          },
        ],
        tableColumn: [
          { type: "checkbox", fixed: "left" },
          {
            type: "seq",
            fixed: "left",
          },
          {
            title: $t("permission.name", "权限名称"),
            field: "name",
            isFilters: true,
          },
          {
            title: $t("permission.permission", "权限标识"),
            field: "permission",
            isFilters: true,
          },
          {
            title: $t("permission.type", "类型"),
            field: "type",
            isFilters: true,
            translate: "permission_type",
          },
          {
            title: $t("permission.description", "描述"),
            field: "description",
            isFilters: true,
          },
          {
            title: $t("table.creator", "创建人"),
            field: "creator",
            isFilters: true,
          },
          {
            title: $t("table.updater", "更新人"),
            field: "updater",
            isFilters: true,
          },
          {
            title: $t("table.createTime", "创建时间"),
            field: "createTime",
            isFilters: true,
          },
          {
            title: $t("table.updateTime", "更新时间"),
            field: "updateTime",
            isFilters: true,
          },
          {
            cType: "action",
            fixed: "right",
          },
        ],
        actions: [
          {
            operation: "edit",
            permission: ["permission:edit"],
            click({ row }: any) {
              return Service.configuration.permission
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
            click({ row }: any) {
              return Service.configuration.permission
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
            permission: ["permission:remove"],
            click({ row }: any) {
              return Service.configuration.permission
                .remove([row.id])
                .then((res: any) => {
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
                label: $t("permission.name", "权限名称"),
                prop: "name",
                type: "input",
              },
              {
                label: $t("permission.permission", "权限标识"),
                prop: "permission",
                type: "input",
              },
              {
                label: $t("permission.type", "类型"),
                prop: "type",
                type: "select",
                options: Dict("permission_type"),
              },
              {
                label: $t("permission.description", "描述"),
                prop: "description",
                type: "input",
              },
            ],
          },
          //提交
          handleConfirm() {
            return Service.configuration.permission
              .save(unref(pageData).editData)
              .then((res: any) => {
                submitSuccess(res);
              });
          },
        },
        query: (pages: any) => {
          return Service.configuration.permission
            .page({ ...pages, ...pageData.queryData })
            .then((res: any) => {
              return res;
            });
        },
      },
    ],
  });
</script>
<style lang="scss" scoped></style>
