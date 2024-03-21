<!--  -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>

<script lang="ts" setup>
const pageData: any = ref({
  queryData: {},
  editData: {},
});

const { pageRef, pageOption } = usePage({
  createLoad: true,
  title: "标题",
  formConfig: {
    formParams: [{}],
  },
  tableConfig: [
    {
      title: "表格3",
      tableColumn: [
        { type: "checkbox", width: 50, fixed: "left" },
        {
          title: "序号", //用户名
          type: "seq",
          width: 100,
          fixed: "left",
        },
        {
          title: "头像", //用户名
          field: "ava",
          cType: "img",
          width: 200,
        },
        {
          title: "用户名", //用户名
          field: "user",
          cType: "tag",
          sortable: true,
          isFilters: true,
          width: 200,
          rules: [
            {
              required: true,
              message: "Please pick a date",
              trigger: "change",
            },
          ],
          form: {
            type: "select",
            params: {},
          },
        },
        {
          title: "基本信息", //基本信息
          children: [
            {
              title: "账号", //账号
              field: "account",
              width: 200,
              sortable: true,
              isFilters: true,
              form: {
                type: "select",
                params: {},
              },
              rules: [
                {
                  type: "date",
                  required: true,
                  message: "Please pick a date",
                  trigger: "change",
                },
              ],
            },
            {
              title: "电话号码", //用户名
              field: "phone",
              width: 200,
              sortable: true,
              isFilters: true,
            },
            {
              title: "电子邮箱", //用户名
              field: "email",
              width: 200,
              sortable: true,
              isFilters: true,
            },
          ],
        },
        {
          title: "操作",
          cType: "action",
          fixed: "right",
          width: 250,
        },
      ],
      query: (pages: any) => {
        return Service.user.page({ ...pages }).then((res: any) => {
          return res;
        });
      },
    },
  ],
});
</script>

<style lang="scss" scoped></style>
