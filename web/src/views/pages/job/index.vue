<!-- 定时任务 job -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Job",
});
const pageData: any = reactive({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("job.job", "定时任务"),
  formConfig: {
    formParams: [
      {
        label: $t("job.jobName", "任务名称"),
        prop: "jobName",
        type: "input",
      },
      {
        label: $t("job.jobCode", "任务编码"),
        prop: "jobCode",
        type: "input",
      },
    ],
  },
  tableConfig: [
    {
      title: $t("job.job", "定时任务"),
      tools: [
        {
          operation: "add",
          permission: ["job:add"],
          click() {
            unref(pageRef).handleOpen({ type: "add", data: {} });
          },
        },
        {
          operation: "remove",
          permission: ["job:remove"],
          click() {
            return Service.job.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["job:import"],
          api(files: any) {
            return Service.job.imports(files).then((res: any) => {
              importSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["job:export"],
          api() {
            return Service.job.exports(pageData.queryData).then((res: any) => {
              exportSuccess(res);
            });
          },
          fileName: $t("job.job", "定时任务"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("job.jobName", "任务名称"),
          field: "jobName",
          isFilters: true,
        },
        {
          title: $t("job.jobCode", "任务编码"),
          field: "jobCode",
          isFilters: true,
        },
        {
          title: $t("job.status", "状态"),
          field: "status",
          isFilters: true,
          translate: "job_status",
        },
        {
          title: $t("job.startTime", "开始时间"),
          field: "startTime",
          isFilters: true,
        },
        {
          title: $t("job.executeCount", "执行次数"),
          field: "executeCount",
          isFilters: true,
        },
        {
          title: $t("job.executeInterval", "执行间隔"),
          field: "executeInterval",
          isFilters: true,
        },
        {
          title: $t("job.cron", "cron表达式"),
          field: "cron",
          isFilters: true,
        },
        {
          title: $t("job.remark", "备注"),
          field: "remark",
          isFilters: true,
        },
        {
          title: $t("job.creator", "创建人"),
          field: "creator",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("job.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("job.createTime", "创建时间"),
          field: "createTime",
          isFilters: true,
        },
        {
          title: $t("job.updateTime", "触发时间"),
          field: "updateTime",
          isFilters: true,
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["job:edit"],
          click({ row }: any) {
            return Service.job.detail({ id: row.id }).then((res: any) => {
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
            return Service.job.detail({ id: row.id }).then((res: any) => {
              unref(pageRef).handleOpen({
                type: "detail",
                data: res.data,
              });
            });
          },
        },
        {
          operation: "remove",
          permission: ["job:remove"],
          click({ row }: any) {
            return Service.job.remove([row.id]).then((res: any) => {
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
              label: $t("job.jobName", "任务名称"),
              prop: "jobName",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("job.jobCode", "任务编码"),
              prop: "jobCode",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("job.startTime", "计划开始时间"),
              prop: "startTime",
              type: "date",
              params: {
                type: "datetime",
              },
            },
            {
              label: $t("job.executeCount", "执行次数"),
              prop: "executeCount",
              type: "number",
            },
            {
              label: $t("job.executeInterval", "执行间隔"),
              prop: "executeInterval",
              type: "number",
            },
            {
              label: $t("job.cron", "cron表达式"),
              prop: "cron",
              type: "input",
            },
            {
              label: $t("job.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("job_status"),
            },
            {
              label: $t("job.remark", "备注"),
              prop: "remark",
              type: "input",
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.job.save(unref(pageData).editData).then((res: any) => {
            submitSuccess(res, pageRef);
          });
        },
      },
      query: (pages: any) =>
        Service.job.page({ ...pages, ...pageData.queryData }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
