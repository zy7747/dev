<!-- 配置页面 page -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Page",
});

const Router = useRouter();
const pageData: any = ref({
  queryData: {},
  editData: {},
});

const { dict } = useDict({
  menusTree: DictService("menusTree"),
  menusList: DictService("menusList"),
});

const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("page.page", "配置页面"),
  formConfig: {
    formParams: [
      {
        label: $t("page.menuId", "页面"),
        prop: "menuId",
        type: "treeSelect",
        params: {
          "check-strictly": true,
        },
        options: () => dict.menusTree,
      },
      {
        label: $t("page.pageCode", "页面编码"),
        prop: "pageCode",
        type: "input",
      },
    ],
  },
  tableConfig: [
    {
      title: $t("page.page", "配置页面"),
      tools: [
        {
          operation: "add",
          permission: ["page:add"],
          click() {
            unref(pageRef).handleOpen({ type: "add", data: {} });
          },
        },
        {
          operation: "remove",
          permission: ["page:remove"],
          click() {
            return Service.configuration.page.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["page:import"],
          api(files: any) {
            return Service.configuration.page
              .imports(files)
              .then((res: any) => {
                importSuccess(res, pageRef);
              });
          },
        },
        {
          operation: "export",
          permission: ["page:export"],
          api() {
            return Service.configuration.page.exports(
              unref(pageData).queryData
            );
          },
          fileName: $t("page.page", "配置页面"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("page.menuId", "页面"),
          field: "menuId",
          isFilters: true,
          translate: "menusList",
        },
        {
          title: $t("page.pageCode", "页面编码"),
          field: "pageCode",
          isFilters: true,
        },
        {
          title: $t("table.status", "状态"),
          field: "status",
          isFilters: true,
          translate: "page_status",
        },
        {
          title: $t("table.remark", "备注"),
          field: "remark",
          isFilters: true,
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          isFilters: true,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          isFilters: true,
        },
        {
          cType: "action",
          fixed: "right",
          width: 300,
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["page:edit"],
          click({ row }: any) {
            Service.configuration.page
              .detail({ id: row.id })
              .then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
          },
        },
        {
          text: "配置",
          click({ row }: any) {
            Router.push({
              path: "/configuration/page/add",
              query: {
                id: row.id,
              },
            });
          },
        },
        {
          operation: "detail",
          click({ row }: any) {
            Service.configuration.page
              .detail({ id: row.id })
              .then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
          },
        },
        {
          operation: "remove",
          permission: ["page:remove"],
          click({ row }: any) {
            Service.configuration.page.remove([row.id]).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
      ],
      dialogConfig: {
        width: "1500px",
        formConfig: {
          formParams: [
            {
              label: $t("page.menuId", "页面"),
              prop: "menuId",
              type: "treeSelect",
              params: {
                "check-strictly": true,
              },
              options: () => dict.menusTree,
            },
            {
              label: $t("page.pageCode", "页面编码"),
              prop: "pageCode",
              type: "input",
            },
            {
              label: $t("table.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("page_status"),
            },
            {
              label: $t("table.remark", "备注"),
              prop: "remark",
              type: "input",
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.configuration.page
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) => {
        return Service.configuration.page
          .page({ ...pages })
          .then((res: any) => {
            return res;
          });
      },
    },
  ],
});
</script>
<style lang="scss" scoped></style>
