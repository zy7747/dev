<!-- 操作日志 operationLog -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "OperationLog",
});
const pageData: any = reactive({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef } = usePage({
  createLoad: true,
  title: $t("operationLog.operationLog", "操作日志"),
  formConfig: {
    formParams: [
      {
        label: $t("operationLog.title", "标题"),
        prop: "title",
        type: "input",
      },
      {
        label: $t("operationLog.module", "操作模块"),
        prop: "module",
        type: "input",
      },
      {
        label: $t("operationLog.type", "操作类型"),
        prop: "type",
        type: "input",
      },
      {
        label: $t("operationLog.operateIp", "操作IP"),
        prop: "operateIp",
        type: "input",
      },
    ],
  },
  tableConfig: [
    {
      title: $t("operationLog.operationLog", "操作日志"),
      tools: [
        {
          operation: "export",
          permission: ["operationLog:export"],
          api() {
            return Service.log.operationLog.exports(unref(pageData).queryData);
          },
          fileName: $t("operationLog.operationLog", "操作日志"),
        },
      ],
      tableColumn: [
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          isFilters: true,
        },
        {
          title: $t("table.status", "状态"),
          field: "status",
          isFilters: true,
          width: 80,
        },
        {
          title: $t("operationLog.title", "标题"),
          field: "title",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("operationLog.module", "操作模块"),
          field: "module",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("operationLog.operateIp", "操作IP"),
          field: "operateIp",
          isFilters: true,
        },
        {
          title: $t("operationLog.operatePlace", "操作地"),
          field: "operatePlace",
          isFilters: true,
        },
        {
          title: $t("operationLog.time", "耗时"),
          field: "time",
          isFilters: true,
        },
        {
          title: $t("operationLog.type", "操作类型"),
          field: "type",
          isFilters: true,
          width: 150,
        },
        {
          cType: "action",
          fixed: "right",
          width: 80,
        },
      ],
      actions: [
        {
          operation: "detail",
          click({ row }: any) {
            return Service.log.operationLog
              .detail({ id: row.id })
              .then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
          },
        },
      ],
      dialogConfig: {
        width: "1000px",
        formConfig: {
          formParams: [
            {
              label: $t("operationLog.title", "标题"),
              prop: "title",
              type: "input",
            },
            {
              label: $t("operationLog.module", "操作模块"),
              prop: "module",
              type: "input",
            },
            {
              label: $t("operationLog.type", "操作类型"),
              prop: "type",
              type: "input",
            },
            {
              label: $t("operationLog.operateIp", "操作IP"),
              prop: "operateIp",
              type: "input",
            },
            {
              label: $t("operationLog.operatePlace", "操作地"),
              prop: "operatePlace",
              type: "input",
            },
            {
              label: $t("operationLog.time", "耗时"),
              prop: "time",
              type: "input",
            },
            {
              label: $t("operationLog.errorMsg", "错误信息"),
              prop: "errorMsg",
              type: "input",
            },
          ],
        },
      },
      query: (pages: any) => {
        return Service.log.operationLog
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
