<!-- 菜单 menu -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData" />
</template>
<script lang="ts" setup>
const pageData: any = ref({
  queryData: {},
  editData: {},
});
const { pageOption, pageRef, ids, query, removeSuccess, submitSuccess } =
  usePage({
    createLoad: true,
    title: $t("menu.menu", "菜单"),
    formConfig: {
      formParams: [
        {
          label: $t("menu.title", "菜单名"),
          prop: "title",
          type: "input",
          span: 6,
        },
        {
          label: $t("menu.status", "状态"),
          prop: "status",
          type: "input",
          span: 6,
        },
      ],
    },
    tableConfig: [
      {
        "tree-config": {
          transform: true,
          rowField: "id",
          parentField: "parentId",
        },
        title: $t("menu.menu", "菜单"),
        tools: [
          {
            operation: "add",
            permission: ["menu.add"],
            click() {
              unref(pageRef).handleOpen({ type: "add", data: {} });
            },
          },
          {
            operation: "remove",
            permission: ["menu.remove"],
            click() {
              Service.menu.remove(ids()).then((res: any) => {
                removeSuccess(res);
              });
            },
          },
          {
            operation: "import",
            permission: ["menu.import"],
            api(files: any) {
              return Service.menu.imports(files).then(() => {
                query();
              });
            },
          },
          {
            operation: "export",
            permission: ["menu.export"],
            api() {
              return Service.menu.exports(unref(pageData).queryData);
            },
            fileName: $t("menu.menu", "菜单"),
          },
        ],
        tableColumn: [
          { type: "checkbox", width: 50, fixed: "left" },
          {
            title: $t("system.no", "序号"),
            type: "seq",
            width: 200,
            fixed: "left",
            treeNode: true,
          },
          {
            title: $t("menu.title", "菜单名"),
            field: "title",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.name", "组件名"),
            field: "name",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.path", "路由路径"),
            field: "path",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.type", "类型"),
            field: "type",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.component", "组件"),
            field: "component",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.permission", "权限标识"),
            field: "permission",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.level", "层级"),
            field: "level",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.sort", "排序"),
            field: "sort",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.visible", "是否可见"),
            field: "visible",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.keepAlive", "是否缓存"),
            field: "keepAlive",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.alwaysShow", "是否总是显示"),
            field: "alwaysShow",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.icon", "图标"),
            field: "icon",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.description", "描述"),
            field: "description",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.meta", "其他信息"),
            field: "meta",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.status", "状态"),
            field: "status",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.remark", "备注"),
            field: "remark",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.creator", "创建人"),
            field: "creator",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.updater", "更新人"),
            field: "updater",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.createTime", "创建时间"),
            field: "createTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("menu.updateTime", "更新时间"),
            field: "updateTime",
            isFilters: true,
            width: 200,
          },
          {
            title: $t("system.action", "操作"),
            cType: "action",
            fixed: "right",
            width: 250,
          },
        ],
        actions: [
          {
            operation: "edit",
            permission: ["menu.edit"],
            click({ row }: any) {
              Service.menu.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "detail",
            permission: ["menu.detail"],
            click({ row }: any) {
              Service.menu.detail({ id: row.id }).then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
            },
          },
          {
            operation: "remove",
            permission: ["menu.remove"],
            click({ row }: any) {
              Service.menu.remove([row.id]).then((res: any) => {
                removeSuccess(res);
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
                options: Dict("menu_type"),
                span: 12,
              },
              {
                label: $t("menu.icon", "图标"),
                prop: "icon",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.title", "菜单名"),
                prop: "title",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.name", "组件名"),
                prop: "name",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.component", "组件"),
                prop: "component",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.path", "路由路径"),
                prop: "path",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.permission", "权限标识"),
                prop: "permission",
                type: "input",
                span: 12,
              },
              {
                label: $t("menu.status", "状态"),
                prop: "status",
                type: "select",
                options: Dict("dict_status"),
                span: 6,
              },
              {
                label: $t("menu.sort", "排序"),
                prop: "sort",
                type: "number",
                span: 6,
              },
              {
                label: $t("menu.visible", "是否可见"),
                prop: "visible",
                type: "radio",
                options: Dict("isNo"),
                span: 6,
              },
              {
                label: $t("menu.keepAlive", "是否缓存"),
                prop: "keepAlive",
                type: "radio",
                options: Dict("isNo"),
                span: 6,
              },
              {
                label: $t("menu.alwaysShow", "是否总是显示"),
                prop: "alwaysShow",
                options: Dict("isNo"),
                type: "radio",
                span: 6,
              },
            ],
          },
          //提交
          handleConfirm() {
            Service.menu.save(unref(pageData).editData).then((res: any) => {
              submitSuccess(res);
            });
          },
        },
        list: () => {
          return Service.menu.list().then((res: any) => {
            return res;
          });
        },
      },
    ],
  });
</script>
<style lang="scss" scoped></style>
