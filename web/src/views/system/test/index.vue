<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();

function getGrid() {
  setTimeout(() => {
    nextTick(() => {
      unref(gridRef).render({
        title: $t("user.user", "用户"),
        form: [
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
            type: "input",
            span: 6,
          },
          {
            label: $t("table.status", "状态"),
            prop: "status",
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
        ],
        tables: [
          {
            title: $t("user.user", "用户"),
            api: "user",
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
                width: 200,
              },
              {
                title: $t("table.status", "状态"),
                isFilters: true,
                field: "status",
                width: 200,
              },
              {
                title: $t("table.remark", "备注"),
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
                title: $t("system.action", "操作"),
                cType: "action",
                fixed: "right",
                width: 250,
              },
            ],
            dialogForm: [
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
                label: $t("table.status", "状态"),
                prop: "status",
                type: "input",
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
        ],
      });
    });
  }, 2000);
}

getGrid();
</script>

<style lang="scss" scoped></style>
