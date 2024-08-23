<!-- 用户 user -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse title="权限" v-if="pageData.editData.id">
        <template #content>
          <CTree
            :options="dict.permissionTree"
            v-model="pageData.editData.permissions"
          />
        </template>
      </Collapse>
    </template>
  </c-page>
</template>
<script lang="ts" setup>
defineOptions({
  name: "User",
});
const pageData: any = reactive({
  queryData: {},
  editData: {
    permissions: [],
  },
});
const { dict } = useDict({
  roles: DictService("roles"),
  permissionTree: DictService("permissionTree"),
});

const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("user.user", "用户"),
  formConfig: {
    formParams: [
      {
        label: $t("user.phone", "电话号码"),
        prop: "phone",
        type: "input",
      },
      {
        label: $t("user.email", "电子邮箱"),
        prop: "email",
        type: "input",
      },
      {
        label: $t("user.account", "账号"),
        prop: "account",
        type: "input",
      },
      {
        label: $t("user.user", "用户"),
        prop: "user",
        type: "select",
        options: Dict("user"),
      },
      {
        label: $t("user.nickname", "昵称"),
        prop: "nickname",
        type: "input",
      },
      {
        label: $t("user.userType", "类型"),
        prop: "userType",
        type: "select",
        options: Dict("user_type"),
      },
      {
        label: $t("table.status", "状态"),
        prop: "status",
        type: "select",
        options: Dict("user_status"),
      },
    ],
  },
  tableConfig: [
    {
      title: $t("user.user", "用户"),
      tools: [
        {
          operation: "add",
          permission: ["user:add"],
          click() {
            unref(pageRef).handleOpen({
              type: "add",
              data: {
                status: "normal",
                roles: [],
                userType: "ordinary",
              },
            });
          },
        },
        {
          operation: "remove",
          permission: ["user:remove"],
          click() {
            return Service.system.user.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["user:import"],
          api(files: any) {
            return Service.system.user.imports(files).then((res: any) => {
              importSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["user:export"],
          api() {
            return Service.system.user.exports(pageData.queryData);
          },
          fileName: $t("user.user", "用户"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("user.avatar", "头像"),
          field: "avatar",
          cType: "img",
        },
        {
          title: $t("user.user", "用户名"),
          field: "user",
          isFilters: true,
        },
        {
          title: $t("user.nickname", "昵称"),
          field: "nickname",
          isFilters: true,
        },
        {
          title: $t("system.base info", "基础信息"),
          children: [
            {
              title: $t("user.account", "账号"),
              field: "account",
              isFilters: true,
            },
            {
              title: $t("user.phone", "电话号码"),
              field: "phone",
              isFilters: true,
              sortable: true,
            },
            {
              title: $t("user.email", "电子邮箱"),
              field: "email",
              isFilters: true,
            },
          ],
        },
        {
          title: $t("user.userType", "类型"),
          isFilters: true,
          field: "userTypeName",
        },
        {
          title: $t("table.status", "状态"),
          isFilters: true,
          field: "status",
          translate: "user_status",
        },
        {
          title: $t("table.remark", "备注"),
          field: "remark",
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          translate: "user",
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          sortable: true,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          sortable: true,
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["user:edit"],
          click({ row }: any) {
            return Service.system.user
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
          operation: "copy",
          permission: ["user:add"],
          click({ row }: any) {
            return Service.system.user
              .detail({ id: row.id })
              .then((res: any) => {
                delete res.data.id;
                delete res.data.permissions;
                unref(pageRef).handleOpen({
                  type: "add",
                  data: res.data,
                });
              });
          },
        },
        {
          operation: "detail",
          click({ row }: any) {
            return Service.system.user
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
          permission: ["user:remove"],
          click({ row }: any) {
            return Service.system.user.remove([row.id]).then((res: any) => {
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
              label: $t("user.phone", "电话号码"),
              prop: "phone",
              type: "input",
            },
            {
              label: $t("user.email", "电子邮箱"),
              prop: "email",
              type: "input",
            },
            {
              label: $t("user.account", "账号"),
              prop: "account",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("user.user", "用户名"),
              prop: "user",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("user.password", "密码"),
              prop: "password",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
              params: {
                type: "password",
                "show-password": true,
              },
            },
            {
              label: $t("user.nickname", "昵称"),
              prop: "nickname",
              type: "input",
            },
            {
              label: $t("user.avatar", "头像"),
              prop: "avatar",
              type: "input",
            },
            {
              label: $t("user.userType", "类型"),
              prop: "userType",
              type: "select",
              options: Dict("user_type"),
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: "角色",
              prop: "roles",
              type: "select",
              options: () => dict.roles,
              params: {
                multiple: true,
                "collapse-tags": true,
              },
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("table.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("user_status"),
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
          ],
        },
        //提交
        handleConfirm() {
          return Service.system.user
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.system.user.page({ ...pages, ...pageData.queryData }),
    },
  ],
});
</script>

<style lang="scss" scoped></style>
