<!-- 权限 permission -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
import AddPermission from "./components/AddPermission.vue";

defineOptions({
  name: "Permission",
});
import { Plus } from "@element-plus/icons-vue";
const pageData: any = reactive({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids } = usePage({
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
          text: "一键新增权限",
          type: "primary",
          icon: Plus,
          permission: ["permission:add"],
          click() {
            showModal(AddPermission, {
              title: "批量新增权限",
              width: 800,
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
                removeSuccess(res, pageRef);
              });
          },
        },
        {
          operation: "import",
          permission: ["permission:import"],
          api(files: any) {
            return Service.configuration.permission
              .imports(files)
              .then((res: any) => {
                importSuccess(res, pageRef);
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
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          sortable: true,
          width: 150,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          sortable: true,
          width: 150,
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
                removeSuccess(res, pageRef);
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
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("permission.permission", "权限标识"),
              prop: "permission",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("permission.type", "类型"),
              prop: "type",
              type: "select",
              options: Dict("permission_type"),
              rules: [
                {
                  required: true,
                },
              ],
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
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.configuration.permission.page({
          ...pages,
          ...pageData.queryData,
        }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
