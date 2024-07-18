<!--  -->
<template>
  <Collapse title="表单">
    <template #content>
      <c-form
        ref="formRef"
        @keyup.enter.native="confirm"
        :model="pageData"
        :formConfig="formConfig"
      />
    </template>
  </Collapse>
</template>

<script lang="ts" setup>
const pageData: any = reactive({});
const { dict } = useDict({
  menusTree: DictService("menusTree"),
});

const formConfig = ref({
  formParams: [
    {
      label: $t("menu.parentId", "父节点id"),
      prop: "parentId",
      type: "treeSelect",
      params: {
        "check-strictly": true,
      },
      rules: [
        {
          required: true,
        },
      ],
      options: () => dict.menusTree,
      span: 24,
    },
    {
      label: $t("menu.menuName", "页面名称"),
      prop: "menuName",
      type: "input",
      span: 12,
      rules: [
        {
          required: true,
        },
      ],
    },
    {
      label: $t("permission.permission tag", "权限标识"),
      prop: "permission",
      type: "input",
      span: 12,
      rules: [
        {
          required: true,
        },
      ],
    },
  ],
});

function confirm() {
  const menu = [
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "查看",
      sort: "0",
    },
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "新增",
      permission: pageData.permission + ":add",
      sort: "1",
    },
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "修改",
      permission: pageData.permission + ":edit",
      sort: "2",
    },
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "删除",
      permission: pageData.permission + ":remove",
      sort: "3",
    },
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "导入",
      permission: pageData.permission + ":import",
      sort: "4",
    },
    {
      visible: 1,
      keepAlive: 1,
      alwaysShow: 1,
      status: "1",
      type: "button",
      parentId: pageData.parentId,
      title: pageData.menuName + "导出",
      permission: pageData.permission + ":export",
      sort: "5",
    },
  ];

  const permission = [
    {
      type: "fun",
      name: pageData.menuName + "删除",
      permission: pageData.permission + ":remove",
    },
    {
      type: "fun",
      name: pageData.menuName + "变更",
      permission: pageData.permission + ":change",
    },
  ];

  Service.configuration.menu.saveList(menu).then(() => {
    Service.configuration.permission.saveList(permission).then(() => {
      ElMessage({
        message: "提交成功",
        type: "success",
      });
    });
  });
}

defineExpose({
  confirm,
});
</script>

<style lang="scss" scoped></style>
