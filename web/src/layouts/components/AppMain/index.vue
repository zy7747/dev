<!-- AppMain -->
<template>
  <div class="app-main">
    <!-- <el-backtop target=".app-main"></el-backtop> -->
    <c-page ref="pageRef" :pageOption="pageOption" />
  </div>
</template>

<script lang="ts" setup>
const pageData: any = reactive({
  queryData: {},
  formData: {},
  dialog: "add",
});

const { pageOption, pageRef, checkboxData } = useGrid({
  formConfig: {
    formParams: [
      {
        label: "下拉框",
        prop: "aaa",
        type: "select",
        show: true,
        span: 6,
        options: [
          {
            label: "123",
            value: "123",
          },
        ],
        rules: [
          {
            type: "date",
            required: true,
            message: "Please pick a date",
            trigger: "change",
          },
        ],
      },
    ],
    formData: pageData.queryData,
  },
  tableConfig: [
    {
      createLoad: true,
      title: "表格1",
      tools: [
        {
          type: "add",
          click() {
            unref(pageRef).handleOpen({ type: "add" });
          },
        },
        {
          type: "remove",
          click() {
            console.log(unref(checkboxData));
          },
        },
        {
          type: "import",
        },
        {
          type: "export",
        },
        {
          text: "其他按钮",
          click() {},
        },
      ],
      tableColumn: [
        { type: "checkbox", width: 50 },
        {
          title: "序号", //用户名
          type: "seq",
          width: 100,
        },
        {
          title: "用户名", //用户名
          field: "user",
          sortable: true,
          isFilters: true,
          rules: [
            {
              type: "date",
              required: true,
              message: "Please pick a date",
              trigger: "change",
            },
          ],
          form: {
            type: "select",
            params: {
              a: 123,
            },
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
          fixed: "right",
          slots: { default: "operate" },
        },
      ],
      dialogConfig: {
        formConfig: {
          formParams: [
            {
              label: "下拉框",
              prop: "aaa",
              type: "select",
              span: 6,
              options: [
                {
                  label: "123",
                  value: "123",
                },
              ],
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
              label: "用户",
              prop: "user",
              type: "input",
            },
          ],
          formData: pageData.formData,
        },
        //提交
        handleConfirm() {
          return true;
        },
      },
      actions: [
        {
          type: "edit",
          click({ row }: any) {
            pageData.formData = row;
            unref(pageRef).handleOpen({ type: "edit", row });
          },
        },
        {
          type: "detail",
          click({ row }: any) {
            pageData.formData = row;
            unref(pageRef).handleOpen({ type: "detail", row });
          },
        },
        {
          type: "remove",
          click({ row }: any) {
            console.log(row);
          },
        },
        {
          text: "其他按钮",
          click() {},
        },
      ],
      query: (pages: any) => {
        return Service.user.page({ ...pages, ...pageData.formData });
      },
    },
  ],
});
</script>

<style lang="scss" scoped>
.app-main {
  height: 760px;
  border-radius: 15px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  overflow: auto;
}
</style>
