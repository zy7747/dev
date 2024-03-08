<!--  -->
<template>
  <c-page ref="pageRef" :pageOption="option" />
</template>

<script lang="ts" setup>
//页面数据
const pageData: any = reactive({
  queryData: {},
  formData: {},
});

const option = ref({});
const pageRef = ref();
//页面配置
function renderPage(config: any) {
  const { pageOption, ids } = usePage({
    formConfig: {
      formParams: config.form,
      formData: pageData.queryData,
    },
    tableConfig: config.tables.map((item: any) => {
      return {
        createLoad: true,
        title: item.title,
        tools: [
          {
            type: "add",
            click() {
              unref(pageRef).handleOpen({ type: "add" });
              //unref(pageRef).addLine({ user: "1" });
            },
          },
          {
            type: "remove",
            click() {
              Service[item.api].remove(unref(ids)).then((res: any) => {
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
        tableColumn: item.tableColumn,
        dialogConfig: {
          width: "1000px",
          formConfig: {
            formParams: item.dialogForm,
            formData: pageData.formData,
          },
          //提交
          handleConfirm() {
            Service[item.api].save(pageData.formData).then((res: any) => {
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
              Service[item.api].detail({ id: row.id }).then((res: any) => {
                pageData.formData = res.data;
                unref(pageRef).handleOpen({ type: "edit" });
              });
            },
          },
          {
            type: "detail",
            click({ row }: any) {
              Service[item.api].detail({ id: row.id }).then((res: any) => {
                pageData.formData = res.data;
                unref(pageRef).handleOpen({ type: "detail" });
              });
            },
          },
          {
            type: "remove",
            click({ row }: any) {
              Service[item.api].remove([row.id]).then((res: any) => {
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
          return Service[item.api]
            .page({ ...pages, ...pageData.query })
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
  renderPage,
});
</script>

<style lang="scss" scoped></style>
