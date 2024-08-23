<!-- 角色 role -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse :title="$t('menu.menu')" v-if="pageData.editData.id">
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

const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("role.role", "角色"),
  formConfig: {
    formParams: [
      {
        label: $t("role.roleName", "角色名称"),
        prop: "roleName",
        type: "input",
      },
      {
        label: $t("role.roleCode", "角色编码"),
        prop: "roleCode",
        type: "input",
      },
      {
        label: $t("table.status", "状态"),
        prop: "status",
        type: "select",
        options: Dict("role_status"),
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
            return Service.config.role.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["role:import"],
          api(files: any) {
            return Service.config.role.imports(files).then((res: any) => {
              importSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["role:export"],
          api() {
            return Service.config.role.exports(pageData.queryData);
          },
          fileName: $t("role.role", "角色"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("role.roleName", "角色名称"),
          field: "roleName",
          isFilters: true,
        },
        {
          title: $t("role.roleCode", "角色编码"),
          field: "roleCode",
          isFilters: true,
        },
        {
          title: $t("table.status", "状态"),
          field: "status",
          isFilters: true,
          translate: "role_status",
        },
        {
          title: $t("table.remark", "备注"),
          field: "remark",
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          translate: "user",
          isFilters: true,
          width: "120px",
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
          width: "120px",
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          sortable: true,
          width: "180px",
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          sortable: true,
          width: "180px",
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["role:edit"],
          click({ row }: any) {
            Service.config.role.detail({ id: row.id }).then((res: any) => {
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
            Service.config.role.detail({ id: row.id }).then((res: any) => {
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
            Service.config.role.remove([row.id]).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
      ],
      editConfig: {
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
            },
            {
              label: $t("table.remark", "备注"),
              prop: "remark",
              type: "input",
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
            },
          ],
        },
        //提交
        handleConfirm() {
          Service.config.role
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.config.role.page({
          ...pages,
          ...pageData.queryData,
        }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
