<!--  -->
<template>
  <c-page ref="pageRef" :pageOption="option" :pageData="pageData" />
</template>

<script lang="ts" setup>
const option = ref({});
const pageRef = ref();
const pageData: any = ref({
  queryData: {},
  editData: {},
});

//页面渲染
function render(config: any) {
  const { pageOption, ids, apis } = usePage({
    pageRef,
    createLoad: true,
    formConfig: {
      formParams: config.form,
    },
    tableConfig: config.tables.map((table: any) => {
      return {
        title: table.title,
        tools: [
          {
            type: "add",
            permission: [],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            type: "remove",
            permission: [],
            click() {
              apis(table.api)
                .remove(ids())
                .then((res: any) => {
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
            permission: [],
          },
          {
            type: "export",
            permission: [],
          },
        ],
        tableColumn: table.tableColumn,
        dialogConfig: {
          width: "1000px",
          formConfig: {
            formParams: table.dialogForm,
          },
          //提交
          handleConfirm() {
            apis(table.api)
              .save(unref(pageData).editData)
              .then((res: any) => {
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
            permission: [],
            click({ row }: any) {
              apis(table.api)
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
            type: "detail",
            permission: [],
            click({ row }: any) {
              apis(table.api)
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
            type: "remove",
            permission: [],
            click({ row }: any) {
              apis(table.api)
                .remove([row.id])
                .then((res: any) => {
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
          return apis(table.api)
            .page({ ...pages, ...unref(pageData).queryData })
            .then((res: any) => {
              return res;
            });
        },
      };
    }),
  });

  option.value = pageOption;
  nextTick(() => {
    unref(pageRef).query();
  });
}

defineExpose({
  render,
});
</script>

<style lang="scss" scoped></style>
