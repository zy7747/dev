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

function apis(params: String) {
  let api = Service;

  params.split(".").forEach((item) => {
    api = api[item];
  });

  return api;
}

//页面渲染
function render(config: any) {
  const { pageOption, ids } = usePage({
    pageRef,
    createLoad: true,
    formConfig: {
      formParams: config.form,
    },
    tableConfig: config.tables.map((table: any) => {
      const api = apis(table.api);

      return {
        title: table.title,
        tools: [
          {
            operation: "add",
            permission: [],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: [],
            click() {
              api.remove(ids()).then((res: any) => {
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
            operation: "import",
            permission: [],
            api(files: any) {
              return api.imports(files).then(() => {
                unref(pageRef).query();
              });
            },
          },
          {
            operation: "export",
            permission: [],
            api: api.exports,
            fileName: table.title,
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
            api.save(unref(pageData).editData).then((res: any) => {
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
            operation: "edit",
            permission: [],
            click({ row }: any) {
              api.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "detail",
            permission: [],
            click({ row }: any) {
              api.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "remove",
            permission: [],
            click({ row }: any) {
              api.remove([row.id]).then((res: any) => {
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
          return api
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
