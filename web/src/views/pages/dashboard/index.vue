<!--  -->
<template>
  <c-grid ref="gridRef" />
</template>

<script lang="ts" setup>
const gridRef = ref();

function getGrid() {
  setTimeout(() => {
    nextTick(() => {
      if (unref(gridRef)) {
        unref(gridRef).render({
          form: [
            {
              label: "用户名称",
              prop: "user",
              type: "input",
              span: 6,
            },
            {
              label: "账号",
              prop: "account",
              type: "select",
              options: [{ label: "1", value: "1" }],
              span: 6,
            },
          ],
          tables: [
            {
              title: "表格1",
              api: "user",
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
                  title: "操作",
                  cType: "action",
                  fixed: "right",
                  width: 250,
                },
              ],
              dialogForm: [
                {
                  label: "电话",
                  prop: "phone",
                  type: "input",
                  span: 6,
                },
                {
                  label: "电子邮箱",
                  prop: "email",
                  type: "input",
                  span: 6,
                },
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
            },
          ],
        });
      }
    });
  }, 2000);
}

getGrid();
</script>

<style lang="scss" scoped></style>
