<!--  -->
<template>
  <c-page ref="pageRef" :pageOption="option" />
</template>

<script lang="ts" setup>
const option = ref({});
const pageRef = ref();

//页面渲染
function render(config: any) {
  const { pageOption, ids } = usePage({
    pageRef,
    formConfig: {
      formParams: config.form,
      formData: config.queryData,
    },
    tableConfig: config.tables.map((table: any) => {
      return {
        createLoad: true,
        title: table.title,
        tools: [
          {
            type: "add",
            click() {
              table.dialogData = {};
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            type: "remove",
            click() {
              Service[table.api].remove(ids()).then((res: any) => {
                if (res.code === 200) {
                  ElMessage({
                    message: "删除成功",
                    type: "success",
                  });
                  unref(pageRef).query();
                }
              });
            },
          },
          {
            type: "import",
          },
          {
            type: "export",
          },
        ],
        tableColumn: table.tableColumn,
        dialogConfig: {
          width: "1000px",
          formConfig: {
            formParams: table.dialogForm,
            formData: table.dialogData,
          },
          //提交
          handleConfirm() {
            Service[table.api].save(table.dialogData).then((res: any) => {
              if (res.code === 200) {
                ElMessage({
                  message: "新增成功",
                  type: "success",
                });
                unref(pageRef).query();
                unref(pageRef).handleClose();
              }
            });
          },
        },
        actions: [
          {
            type: "edit",
            click({ row }: any) {
              Service[table.api].detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({ type: "edit", data: res.data });
              });
            },
          },
          {
            type: "detail",
            click({ row }: any) {
              Service[table.api].detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({ type: "detail", data: res.data });
              });
            },
          },
          {
            type: "remove",
            click({ row }: any) {
              Service[table.api].remove([row.id]).then((res: any) => {
                if (res.code === 200) {
                  ElMessage({
                    message: "删除成功",
                    type: "success",
                  });
                  unref(pageRef).query();
                }
              });
            },
          },
        ],
        query: (pages: any) => {
          return Service[table.api]
            .page({ ...pages, ...config.queryData })
            .then((res: any) => {
              return res;
            });
        },
      };
    }),
  });

  option.value = pageOption;
}

defineExpose({
  render,
});
</script>

<style lang="scss" scoped></style>
