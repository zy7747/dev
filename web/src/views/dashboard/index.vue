<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();

function getGrid() {
  setTimeout(() => {
    unref(gridRef).render({
      queryData: {},
      form: [
        {
          label: "下拉框",
          prop: "aaa",
          type: "select",
          options: [
            {
              label: "123",
              value: "123",
            },
          ],
          span: 6,
        },
      ],
      tables: [
        {
          title: "表格",
          api: "user",
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
              width: 200,
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
              title: "头像", //用户名
              field: "avatar",
              cType: "img",
              width: 200,
              sortable: true,
              isFilters: true,
            },
            {
              title: "操作",
              cType: "action",
              fixed: "right",
              width: 250,
            },
          ],
          dialogForm: [
            {
              label: "用户",
              prop: "user",
              type: "input",
              span: 6,
            },
            {
              label: "密码",
              prop: "password",
              type: "input",
              span: 6,
              params: {
                type: "password",
                showPassword: true,
              },
            },
          ],
          dialogData: {},
        },
      ],
    });
  }, 2000);
}

getGrid();
</script>

<style lang="scss" scoped></style>
