<!-- 角色 role -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse title="树" v-if="pageData.editData.id">
        <template #content>
          <CTree :options="dict.menus" v-model="pageData.editData.menus" />
        </template>
      </Collapse>
    </template>
  </c-page>
</template>

<script lang="ts" setup>
defineOptions({
  name: "Role",
});
const pageData: any = reactive({
  queryData: {},
  editData: {},
});

const { dict } = useDict({
  menus: DictService("menusTree"),
});

const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("role.role", "角色"),
    formConfig: {
      formParams: [
        {
          label: $t("role.roleName", "角色名称"),
          prop: "roleName",
          type: "input",
          span: 6,
        },
        {
          label: $t("role.roleCode", "角色编码"),
          prop: "roleCode",
          type: "input",
          span: 6,
        },
        {
          label: $t("table.status", "状态"),
          prop: "status",
          type: "select",
          options: Dict("role_status"),
          span: 6,
        },
      ],
    },
    tableConfig: [
      {
        title: $t("role.role", "角色"),
        tools: [
          {
            operation: "add",
            permission: ["role:add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: { status: "0" } });
            },
          },
          {
            operation: "remove",
            permission: ["role:remove"],
            click() {
              Service.configuration.role.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["role:import"],
            api(files: any) {
              return Service.configuration.role.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["role:export"],
            api() {
              return Service.configuration.role.exports(pageData.queryData);
            },
            fileName: $t("role.role", "角色"),
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
            title: $t("role.roleName", "角色名称"),
            field: "roleName",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("role.roleCode", "角色编码"),
            field: "roleCode",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("table.status", "状态"),
            field: "status",
            isFilters: true,
            translate: "role_status",
            width: 200,
          },
          {
            title: $t("table.remark", "备注"),
            field: "remark",
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
            permission: ["role:edit"],
            click({ row }: any) {
              Service.configuration.role
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
              Service.configuration.role
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
            permission: ["role:remove"],
            click({ row }: any) {
              Service.configuration.role.remove([row.id]).then((res: any) => {
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
                label: $t("role.roleName", "角色名称"),
                prop: "roleName",
                type: "input",
                rules: [
                  {
                    required: true,
                  },
                ],
                span: 6,
              },
              {
                label: $t("role.roleCode", "角色编码"),
                prop: "roleCode",
                type: "input",
                rules: [
                  {
                    required: true,
                  },
                ],
                span: 6,
              },
              {
                label: $t("table.remark", "备注"),
                prop: "remark",
                type: "input",
                span: 6,
              },
              {
                label: $t("table.status", "状态"),
                prop: "status",
                type: "select",
                rules: [
                  {
                    required: true,
                  },
                ],
                options: Dict("role_status"),
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            Service.configuration.role
              .save(unref(pageData).editData)
              .then((res: any) => {
                submitSuccess(res);
              });
          },
        },
        query: (pages: any) => {
          return Service.configuration.role
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
