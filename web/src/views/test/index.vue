<!--  -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>

<script lang="ts" setup>
const pageData: any = ref({
  queryData: {},
  editData: {},
});

const { pageRef, pageOption, ids } = usePage({
  createLoad: true,
  formConfig: {
    formParams: [],
  },
  tableConfig: [
    {
      title: "标题",
      tools: [
        {
          operation: "add",
          click() {
            unref(pageRef).handleOpen({ type: "add", data: {} });
          },
        },
        {
          operation: "remove",
          click() {
            Service.page.remove(ids()).then((res: any) => {
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
        },
        {
          operation: "export",
        },
      ],
      tableColumn: [],
      dialogConfig: {
        width: "1000px",
        formConfig: {
          formParams: [],
        },
        //提交
        handleConfirm() {
          Service.page.save(unref(pageData).editData).then((res: any) => {
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
          click({ row }: any) {
            Service.page.detail({ id: row.id }).then((res: any) => {
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
            Service.page.detail({ id: row.id }).then((res: any) => {
              unref(pageRef).handleOpen({
                type: "detail",
                data: res.data,
              });
            });
          },
        },
        {
          operation: "remove",
          click({ row }: any) {
            Service.page.remove([row.id]).then((res: any) => {
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
        return Service.page
          .page({ ...pages, ...unref(pageData).queryData })
          .then((res: any) => {
            return res;
          });
      },
    },
  ],
});
</script>

<style lang="scss" scoped></style>
