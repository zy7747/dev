<!-- 用户 user -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse title="树" v-if="pageData.editData.id">
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

const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("user.user", "用户"),
    formConfig: {
      formParams: [
        {
          label: $t("user.phone", "电话号码"),
          prop: "phone",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.email", "电子邮箱"),
          prop: "email",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.account", "账号"),
          prop: "account",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.user", "用户名"),
          prop: "user",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.nickname", "昵称"),
          prop: "nickname",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.userType", "类型"),
          prop: "userType",
          type: "select",
          options: Dict("user_type"),
          span: 6,
        },
        {
          label: $t("table.status", "状态"),
          prop: "status",
          type: "select",
          options: Dict("user_status"),
          span: 6,
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
              return Service.user.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["user:import"],
            api(files: any) {
              return Service.user.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["user:export"],
            api() {
              return Service.user.exports(pageData.queryData);
            },
            fileName: $t("user.user", "用户"),
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
            title: $t("user.avatar", "头像"),
            field: "avatar",
            width: 200,
          },
          {
            title: $t("user.user", "用户名"),
            field: "user",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("user.nickname", "昵称"),
            field: "nickname",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("system.base info", "基础信息"),
            children: [
              {
                title: $t("user.account", "账号"),
                field: "account",
                isFilters: true,
                width: 200,
              },
              {
                title: $t("user.phone", "电话号码"),
                field: "phone",
                isFilters: true,
                width: 200,
              },
              {
                title: $t("user.email", "电子邮箱"),
                field: "email",
                isFilters: true,
                width: 200,
              },
            ],
          },
          {
            title: $t("user.userType", "类型"),
            isFilters: true,
            field: "userType",
            translate: "user_type",
            width: 200,
          },
          {
            title: $t("table.status", "状态"),
            isFilters: true,
            field: "status",
            translate: "user_status",
            width: 200,
          },
          {
            title: $t("table.remark", "备注"),
            field: "remark",
            width: 200,
          },
          {
            title: $t("table.creator", "创建人"),
            field: "creator",
            width: 200,
          },
          {
            title: $t("table.updater", "更新人"),
            field: "updater",
            width: 200,
          },
          {
            title: $t("table.createTime", "创建时间"),
            field: "createTime",
            width: 200,
          },
          {
            title: $t("table.updateTime", "更新时间"),
            field: "updateTime",
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
            permission: ["user:edit"],
            click({ row }: any) {
              return Service.user.detail({ id: row.id }).then((res: any) => {
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
              return Service.user.detail({ id: row.id }).then((res: any) => {
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
              return Service.user.remove([row.id]).then((res: any) => {
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
                label: $t("user.phone", "电话号码"),
                prop: "phone",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.email", "电子邮箱"),
                prop: "email",
                type: "input",
                span: 6,
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
                span: 6,
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
                span: 6,
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
                span: 6,
              },
              {
                label: $t("user.nickname", "昵称"),
                prop: "nickname",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.avatar", "头像"),
                prop: "avatar",
                type: "input",
                span: 6,
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
                span: 6,
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
                span: 6,
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
                span: 6,
              },
              {
                label: $t("table.remark", "备注"),
                prop: "remark",
                type: "input",
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            return Service.user
              .save(unref(pageData).editData)
              .then((res: any) => {
                submitSuccess(res);
              });
          },
        },
        query: (pages: any) => {
          return Service.user
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
