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
  const { pageOption, ids, query, removeSuccess, submitSuccess } = usePage({
    pageRef,
    createLoad: true,
    title: config.title,
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
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: [],
            api(files: any) {
              return api.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: [],
            api() {
              return api.exports(unref(pageData).queryData);
            },
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
              submitSuccess(res);
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
                removeSuccess(res);
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
