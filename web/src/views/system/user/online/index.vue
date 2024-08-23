<!-- 在线用户 userOnline -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "UserOnline",
});
const pageData: any = reactive({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("userOnline.userOnline", "在线用户"),
  formConfig: {
    formParams: [
      {
        label: $t("userOnline.uid", "用户"),
        prop: "uid",
        type: "select",
        options: Dict("user"),
      },
      {
        label: $t("userOnline.account", "账号"),
        prop: "account",
        type: "input",
      },
    ],
  },
  tableConfig: [
    {
      title: $t("userOnline.userOnline", "在线用户"),
      tools: [
        {
          operation: "remove",
          permission: ["userOnline:remove"],
          click() {
            return Service.log.userOnline.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["userOnline:export"],
          api() {
            return Service.log.userOnline
              .exports(pageData.queryData)
              .then((res: any) => {
                exportSuccess(res);
              });
          },
          fileName: $t("userOnline.userOnline", "在线用户"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("userOnline.status", "状态"),
          field: "status",
          isFilters: true,
          translate: "online_status",
          fixed: "left",
          width: 100,
        },
        {
          title: $t("userOnline.uid", "用户"),
          field: "uid",
          translate: "user",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("userOnline.account", "账号"),
          field: "account",
          isFilters: true,
        },
        {
          title: $t("userOnline.createTime", "登录时间"),
          field: "createTime",
          sortable: true,
          width: 150,
        },
        {
          title: $t("userOnline.loginIp", "登录IP"),
          field: "loginIp",
          isFilters: true,
        },
        {
          title: $t("userOnline.loginPlace", "登录地"),
          field: "loginPlace",
          isFilters: true,
        },
        {
          title: $t("userOnline.loginDevice", "登录设备"),
          field: "loginDevice",
          isFilters: true,
        },
        {
          title: $t("userOnline.operateResults", "操作结果"),
          field: "operateResults",
          isFilters: true,
          translate: "user_online_operateResults",
        },

        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "detail",
          click({ row }: any) {
            return Service.log.userOnline
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
          permission: ["userOnline:remove"],
          click({ row }: any) {
            return Service.log.userOnline.remove([row.id]).then((res: any) => {
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
              label: $t("userOnline.uid", "用户"),
              prop: "uid",
              type: "select",
              options: Dict("user"),
            },
            {
              label: $t("userOnline.account", "账号"),
              prop: "account",
              type: "input",
            },
            {
              label: $t("userOnline.loginIp", "登录IP"),
              prop: "loginIp",
              type: "input",
            },
            {
              label: $t("userOnline.loginPlace", "登录地"),
              prop: "loginPlace",
              type: "input",
            },
            {
              label: $t("userOnline.loginDevice", "登录设备"),
              prop: "loginDevice",
              type: "input",
            },
            {
              label: $t("userOnline.operateResults", "操作结果"),
              prop: "operateResults",
              type: "select",
              options: Dict("user_online_operateResults"),
            },
            {
              label: $t("userOnline.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("online_status"),
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.log.userOnline
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.log.userOnline.page({ ...pages, ...pageData.queryData }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
