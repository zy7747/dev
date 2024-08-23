<!-- 消息通知 message -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Message",
});
const pageData: any = reactive({
  queryData: {},
  editData: {
    receiverList: [],
  },
});
const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("message.message", "消息通知"),
  formConfig: {
    formParams: [
      {
        label: $t("message.messageName", "消息名称"),
        prop: "messageName",
        type: "input",
      },
      {
        label: $t("message.module", "推送模块"),
        prop: "module",
        type: "input",
      },
      {
        label: $t("message.type", "类型"),
        prop: "type",
        type: "select",
        options: Dict("notice_type"),
      },
      {
        label: $t("message.isRead", "是否已读"),
        prop: "isRead",
        type: "select",
        options: Dict("isRead"),
      },
      {
        label: $t("message.receiver", "收件人"),
        prop: "receiver",
        type: "select",
        options: Dict("user"),
      },
      {
        label: $t("message.creator", "发送人"),
        prop: "creator",
        type: "select",
        options: Dict("user"),
      },
      {
        label: $t("message.status", "状态"),
        prop: "status",
        type: "select",
        options: Dict("notice_status"),
      },
    ],
  },
  tableConfig: [
    {
      title: $t("message.message", "消息通知"),
      tools: [
        {
          operation: "add",
          permission: ["message:add"],
          click() {
            unref(pageRef).handleOpen({
              type: "add",
              data: {
                isRead: "0",
                status: "1",
              },
            });
          },
        },
        {
          operation: "remove",
          permission: ["message:remove"],
          click() {
            return Service.system.message.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["message:import"],
          api(files: any) {
            return Service.system.message.imports(files).then((res: any) => {
              importSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["message:export"],
          api() {
            return Service.system.message
              .exports(pageData.queryData)
              .then((res: any) => {
                exportSuccess(res);
              });
          },
          fileName: $t("message.message", "消息通知"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("message.messageName", "消息名称"),
          field: "messageName",
          isFilters: true,
        },
        {
          title: $t("message.module", "推送模块"),
          field: "module",
          isFilters: true,
        },
        {
          title: $t("message.type", "类型"),
          field: "type",
          translate: "notice_type",
          isFilters: true,
        },
        {
          title: $t("message.isRead", "是否已读"),
          field: "isRead",
          translate: "isRead",
          isFilters: true,
        },
        {
          title: $t("message.creator", "发送人"),
          field: "creator",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("message.receiver", "收件人"),
          field: "receiver",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("message.remark", "备注"),
          field: "remark",
        },
        {
          title: $t("message.createTime", "创建时间"),
          field: "createTime",
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
          permission: ["message:edit"],
          click({ row }: any) {
            return Service.system.message
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
            return Service.system.message
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
          permission: ["message:remove"],
          click({ row }: any) {
            return Service.system.message.remove([row.id]).then((res: any) => {
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
              label: $t("message.messageName", "消息名称"),
              prop: "messageName",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("message.module", "推送模块"),
              prop: "module",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("message.type", "类型"),
              prop: "type",
              type: "select",
              options: Dict("notice_type"),
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("message.receiver", "收件人"),
              prop: "receiverList",
              type: "allSelect",
              options: Dict("user"),
            },
            {
              label: $t("message.messageContent", "消息内容"),
              prop: "messageContent",
              type: "editor",
              span: 24,
              rules: [
                {
                  required: true,
                },
              ],
            },

            {
              label: $t("message.isRead", "是否已读"),
              prop: "isRead",
              type: "select",
              options: Dict("isRead"),
              params: {
                disabled: true,
              },
              rules: [
                {
                  required: true,
                },
              ],
            },

            {
              label: $t("message.receiver", "收件人"),
              prop: "receiver",
              type: "select",
              options: Dict("user"),
              params: {
                disabled: true,
              },
            },
            {
              label: $t("message.remark", "备注"),
              prop: "remark",
              type: "input",
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.system.message
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.system.message.page({ ...pages, ...pageData.queryData }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
