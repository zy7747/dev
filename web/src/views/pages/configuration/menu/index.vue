<!-- 菜单 menu -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
defineOptions({
  name: "Menu",
});

const pageData: any = reactive({
  queryData: {},
  editData: {},
});

const menuTree: any = ref([]);
const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("menu.menu", "菜单"),
  formConfig: {
    formParams: [
      {
        label: $t("menu.title", "菜单名"),
        prop: "title",
        type: "input",
      },
      {
        label: $t("table.status", "状态"),
        prop: "status",
        type: "select",
        options: Dict("menu_status"),
      },
    ],
  },
  tableConfig: [
    {
      "row-config": {
        keyField: "id",
      },
      "tree-config": {
        transform: true,
        reserve: true,
        rowField: "id",
        parentField: "parentId",
      },
      cellClassName({ row, column }: any) {
        if (column.type !== "checkbox" && column.slots.default != "operate") {
          if (row.type === "system") {
            return "--el-color-danger-light-9";
          }
          if (row.type === "menu") {
            return "--el-color-warning-light-9";
          }
          if (row.type === "button") {
            return "--el-color-primary-light-9";
          }
          if (row.type === "directory") {
            return "--el-color-success-light-9";
          }
        }
      },
      title: $t("menu.menu", "菜单"),
      tools: [
        {
          operation: "add",
          permission: ["menu:add"],
          click() {
            unref(pageRef).handleOpen({
              type: "add",
              data: {
                type: "menu",
                status: "1",
                alwaysShow: 1,
                visible: 1,
                keepAlive: 1,
              },
            });
          },
        },
        {
          operation: "remove",
          permission: ["menu:remove"],
          click() {
            Service.configuration.menu.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "import",
          permission: ["menu:import"],
          api(files: any) {
            return Service.configuration.menu
              .imports(files)
              .then((res: any) => {
                importSuccess(res, pageRef);
              });
          },
        },
        {
          operation: "export",
          permission: ["menu:export"],
          api() {
            return Service.configuration.menu.exports(pageData.queryData);
          },
          fileName: $t("menu.menu", "菜单"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          title: $t("menu.sort", "排序"),
          field: "sort",
          width: 100,
          sortable: true,
          fixed: "left",
        },
        {
          title: $t("menu.icon", "图标"),
          field: "icon",
          fixed: "left",
          cType: "icon",
          width: 80,
        },
        {
          title: $t("menu.type", "类型"),
          field: "type",
          isFilters: true,
          fixed: "left",
          translate: "menu_type",
          width: 100,
        },
        {
          title: $t("menu.title", "菜单名"),
          field: "title",
          isFilters: true,
          treeNode: true,
          fixed: "left",
        },
        {
          title: $t("menu.name", "组件名"),
          field: "name",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("menu.component", "组件文件路径"),
          field: "component",
        },
        {
          title: $t("menu.path", "路由路径"),
          field: "path",
          isFilters: true,
        },
        {
          title: $t("menu.permission", "权限标识"),
          field: "permission",
          isFilters: true,
        },
        {
          title: $t("menu.visible", "是否可见"),
          field: "visible",
          isFilters: true,
          translate: "isNo",
          width: 100,
        },
        {
          title: $t("menu.keepAlive", "是否缓存"),
          field: "keepAlive",
          isFilters: true,
          translate: "isNo",
          width: 100,
        },
        {
          title: $t("menu.alwaysShow", "是否总是显示"),
          field: "alwaysShow",
          isFilters: true,
          translate: "isNo",
          width: 120,
        },
        {
          title: $t("table.status", "状态"),
          field: "status",
          translate: "menu_status",
          isFilters: true,
          width: 100,
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          sortable: true,
          width: 150,
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "add",
          permission: ["menu:add"],
          show: ({ row }: any) => row.type !== "button",
          click({ row }: any) {
            let type = "system";

            if (row.type === "system") {
              type = "directory";
            } else if (row.type === "directory") {
              type = "menu";
            } else if (row.type === "menu") {
              type = "button";
            }

            unref(pageRef).handleOpen({
              type: "add",
              data: {
                parentId: row.id,
                type,
                status: "1",
                alwaysShow: 1,
                visible: 1,
                keepAlive: 1,
              },
            });
          },
        },
        {
          operation: "edit",
          permission: ["menu:edit"],
          click({ row }: any) {
            Service.configuration.menu
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
          operation: "copy",
          permission: ["menu:add"],
          click({ row }: any) {
            Service.configuration.menu
              .detail({ id: row.id })
              .then((res: any) => {
                delete res.data.id;
                unref(pageRef).handleOpen({
                  type: "add",
                  data: res.data,
                });
              });
          },
        },
        {
          operation: "detail",
          click({ row }: any) {
            Service.configuration.menu
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
          permission: ["menu:remove"],
          click({ row }: any) {
            Service.configuration.menu.remove([row.id]).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
      ],
      dialogConfig: {
        width: "1000px",
        formConfig: {
          formParams: [
            {
              label: $t("menu.type", "类型"),
              prop: "type",
              type: "radio",
              options: Dict("menu_type"),
              span: 24,
            },
            {
              label: $t("menu.parentId", "父节点id"),
              prop: "parentId",
              type: "treeSelect",
              params: {
                "check-strictly": true,
              },
              show: () => pageData.editData.type !== "system",
              options: menuTree.value,
              span: 12,
            },
            {
              label: $t("menu.icon", "图标"),
              prop: "icon",
              type: "icon",
              options: ["app", "system"],
              show: () => pageData.editData.type !== "button",
              span: 12,
            },
            {
              label: $t("menu.title", "菜单名"),
              prop: "title",
              type: "input",
              rules: [
                {
                  required: true,
                },
              ],
              span: 12,
            },
            {
              label: $t("menu.name", "组件名"),
              prop: "name",
              type: "input",
              show: () =>
                pageData.editData.type !== "button" &&
                pageData.editData.type !== "directory",
              span: 12,
            },
            {
              label: $t("menu.component", "组件"),
              prop: "component",
              show: () => pageData.editData.type === "menu",
              rules: [
                {
                  required: true,
                },
              ],
              type: "input",
              span: 12,
            },
            {
              label: $t("menu.path", "路由路径"),
              prop: "path",
              type: "input",
              show: () => pageData.editData.type !== "button",
              rules: [
                {
                  required: true,
                },
              ],
              span: 12,
            },
            {
              label: $t("menu.permission", "权限标识"),
              prop: "permission",
              type: "input",
              span: 12,
            },
            {
              label: $t("table.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("menu_status"),
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("menu.sort", "排序"),
              prop: "sort",
              type: "number",
              rules: [
                {
                  required: true,
                },
              ],
            },
            {
              label: $t("menu.visible", "是否可见"),
              prop: "visible",
              type: "radio",
              options: Dict("isNo"),
              show: () => pageData.editData.type !== "button",
            },
            {
              label: $t("menu.keepAlive", "是否缓存"),
              prop: "keepAlive",
              type: "radio",
              options: Dict("isNo"),
              show: () => pageData.editData.type === "menu",
            },
            {
              label: $t("menu.alwaysShow", "是否总是显示"),
              prop: "alwaysShow",
              options: Dict("isNo"),
              type: "radio",
              show: () => pageData.editData.type === "menu",
            },
          ],
        },
        //提交
        handleConfirm() {
          Service.configuration.menu
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      list: () => {
        return Service.configuration.menu
          .list(pageData.queryData)
          .then((res: any) => {
            const list = res.data.map((item: any) => {
              return { ...item, label: item.title, value: item.id };
            });

            menuTree.value.splice(0);

            menuTree.value.push(...FormatData.handleTree(list));

            return res;
          });
      },
    },
  ],
});
</script>
<style lang="scss" scoped></style>
