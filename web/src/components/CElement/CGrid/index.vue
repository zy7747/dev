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
  let form = [];

  if (config.form.length) {
    form = config.form.map((item: any) => {
      if (item.type === "select") {
        return {
          ...item,
          options: Dict(item.options),
        };
      } else {
        return item;
      }
    });
  }

  const { pageOption, ids } = usePage({
    pageRef,
    createLoad: true,
    title: config.title,
    formConfig: {
      formParams: form,
    },
    tableConfig: config.tables.map((table: any) => {
      const api = apis(table.api);

      let dialogForm = [];

      if (table.dialogForm.length) {
        dialogForm = table.dialogForm.map((item: any) => {
          if (item.type === "select") {
            return {
              ...item,
              options: Dict(item.options),
            };
          } else {
            return item;
          }
        });
      }

      return {
        title: table.title,
        tools: [
          {
            operation: "add",
            permission: [table.permissions + ":add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: [table.permissions + ":remove"],
            click() {
              return api.remove(ids()).then((res: any) => {
                removeSuccess(res, pageRef);
              });
            },
          },
          {
            operation: "import",
            permission: [table.permissions + ":import"],
            api(files: any) {
              return api.imports(files).then((res: any) => {
                importSuccess(res, pageRef);
              });
            },
          },
          {
            operation: "export",
            permission: [table.permissions + ":export"],
            api() {
              return api.exports(unref(pageData).queryData);
            },
            fileName: table.title,
          },
        ],
        tableColumn: table.tableColumn,
        editConfig: {
          width: "1000px",
          formConfig: {
            formParams: dialogForm,
          },
          //提交
          handleConfirm() {
            api.save(unref(pageData).editData).then((res: any) => {
              submitSuccess(res, pageRef);
            });
          },
        },
        actions: [
          {
            operation: "edit",
            permission: [table.permissions + ":edit"],
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
            permission: [table.permissions + ":remove"],
            click({ row }: any) {
              api.remove([row.id]).then((res: any) => {
                removeSuccess(res, pageRef);
              });
            },
          },
        ],
        query: (pages: any) => {
          return api.page({ ...pages, ...unref(pageData).queryData });
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
