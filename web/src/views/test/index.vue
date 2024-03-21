<!-- 用户 user -->
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
    title: $t("user.user", "用户"),
    formConfig: {
      formParams: [
        {
          label: $t("user.id", "id"),
          prop: "id",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.uid", "用户id"),
          prop: "uid",
          type: "input",
          span: 6,
        },
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
          label: $t("user.password", "密码"),
          prop: "password",
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
          label: $t("user.avatar", "头像"),
          prop: "avatar",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.userType", "类型"),
          prop: "userType",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.status", "状态"),
          prop: "status",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.remark", "备注"),
          prop: "remark",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.creator", "创建人"),
          prop: "creator",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.updater", "更新人"),
          prop: "updater",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.createTime", "创建时间"),
          prop: "createTime",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.updateTime", "更新时间"),
          prop: "updateTime",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.isDeleted", "是否删除"),
          prop: "isDeleted",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.tenantId", "租户id"),
          prop: "tenantId",
          type: "input",
          span: 6,
        },
        {
          label: $t("user.version", "乐观锁"),
          prop: "version",
          type: "input",
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
            permission: ["user.add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: ["user.remove"],
            click() {
              Service.user.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["user.import"],
            api(files: any) {
              return Service.user.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["user.export"],
            api() {
              return Service.user.exports(unref(pageData).queryData);
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
            title: $t("user.id", "id"),
            field: "id",
            width: 200,
          },
          {
            title: $t("user.uid", "用户id"),
            field: "uid",
            width: 200,
          },
          {
            title: $t("user.phone", "电话号码"),
            field: "phone",
            width: 200,
          },
          {
            title: $t("user.email", "电子邮箱"),
            field: "email",
            width: 200,
          },
          {
            title: $t("user.account", "账号"),
            field: "account",
            width: 200,
          },
          {
            title: $t("user.user", "用户名"),
            field: "user",
            width: 200,
          },
          {
            title: $t("user.password", "密码"),
            field: "password",
            width: 200,
          },
          {
            title: $t("user.nickname", "昵称"),
            field: "nickname",
            width: 200,
          },
          {
            title: $t("user.avatar", "头像"),
            field: "avatar",
            width: 200,
          },
          {
            title: $t("user.userType", "类型"),
            field: "userType",
            width: 200,
          },
          {
            title: $t("user.status", "状态"),
            field: "status",
            width: 200,
          },
          {
            title: $t("user.remark", "备注"),
            field: "remark",
            width: 200,
          },
          {
            title: $t("user.creator", "创建人"),
            field: "creator",
            width: 200,
          },
          {
            title: $t("user.updater", "更新人"),
            field: "updater",
            width: 200,
          },
          {
            title: $t("user.createTime", "创建时间"),
            field: "createTime",
            width: 200,
          },
          {
            title: $t("user.updateTime", "更新时间"),
            field: "updateTime",
            width: 200,
          },
          {
            title: $t("user.isDeleted", "是否删除"),
            field: "isDeleted",
            width: 200,
          },
          {
            title: $t("user.tenantId", "租户id"),
            field: "tenantId",
            width: 200,
          },
          {
            title: $t("user.version", "乐观锁"),
            field: "version",
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
            permission: ["user.edit"],
            click({ row }: any) {
              Service.user.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "detail",
            permission: ["user.detail"],
            click({ row }: any) {
              Service.user.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "remove",
            permission: ["user.remove"],
            click({ row }: any) {
              Service.user.remove([row.id]).then((res: any) => {
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
                label: $t("user.id", "id"),
                prop: "id",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.uid", "用户id"),
                prop: "uid",
                type: "input",
                span: 6,
              },
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
                label: $t("user.password", "密码"),
                prop: "password",
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
                label: $t("user.avatar", "头像"),
                prop: "avatar",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.userType", "类型"),
                prop: "userType",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.status", "状态"),
                prop: "status",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.remark", "备注"),
                prop: "remark",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.creator", "创建人"),
                prop: "creator",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.updater", "更新人"),
                prop: "updater",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.createTime", "创建时间"),
                prop: "createTime",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.updateTime", "更新时间"),
                prop: "updateTime",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.isDeleted", "是否删除"),
                prop: "isDeleted",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.tenantId", "租户id"),
                prop: "tenantId",
                type: "input",
                span: 6,
              },
              {
                label: $t("user.version", "乐观锁"),
                prop: "version",
                type: "input",
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            Service.user.save(unref(pageData).editData).then((res: any) => {
              submitSuccess(res);
            });
          },
        },
        query: (pages: any) => {
          return Service.user.page({ ...pages }).then((res: any) => {
            return res;
          });
        },
      },
    ],
  });
</script>
<style lang="scss" scoped></style>
