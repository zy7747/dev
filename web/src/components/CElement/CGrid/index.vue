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
const idList: any = ref([]);

//页面渲染
function render(config: any) {
  const { pageOption, ids } = usePage({
    pageRef,
    formConfig: {
      formParams: config.form,
      formData: pageData.queryData,
    },
    tableConfig: config.tables.map((table: any) => {
      return {
        createLoad: true,
        title: table.title,
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
              Service[table.api].remove(unref(ids)).then((res: any) => {
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
            formData: pageData.formData,
          },
          //提交
          handleConfirm() {
            Service[table.api].save(pageData.formData).then((res: any) => {
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
                pageData.formData = res.data;
                unref(pageRef).handleOpen({ type: "edit" });
              });
            },
          },
          {
            type: "detail",
            click({ row }: any) {
              Service[table.api].detail({ id: row.id }).then((res: any) => {
                pageData.formData = res.data;
                unref(pageRef).handleOpen({ type: "detail" });
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
            .page({ ...pages, ...pageData.query })
            .then((res: any) => {
              return res;
            });
        },
      };
    }),
  });

  console.log(pageOption, ids);

  option.value = pageOption;
  idList.value = ids;
}

defineExpose({
  render,
});
</script>

<style lang="scss" scoped></style>
