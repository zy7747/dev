<!--CPage-->
<template>
  <div class="CPage">
    <Collapse :title="pageOption.title">
      <template #title>
        <slot name="title" />
      </template>

      <template #tools>
        <c-button
          v-if="pageOption.query === false ? pageOption.query : true"
          size="small"
          type="primary"
          @handleClick="query"
          :text="$t('system.query')"
          :icon="Search"
        />
        <Tools
          v-if="pageOption.actions && pageOption.actions.length"
          :tools="pageOption.actions"
          class="btnR"
        />
        <c-button
          plain
          v-if="pageOption.reset === false ? pageOption.reset : true"
          size="small"
          @handleClick="reset"
          :text="$t('system.reset')"
          :icon="Refresh"
        />
      </template>

      <template #content>
        <c-form
          ref="formRef"
          @keyup.enter.native="query"
          :model="pageData.queryData"
          :formConfig="pageOption.formConfig"
        />
      </template>
    </Collapse>

    <c-tabs
      type="border-card"
      v-if="pageOption.tableConfig && pageOption.tableConfig.length"
      @tabChange="tabChange"
      v-model="active"
      :options="pageOption.tableConfig"
    >
      <template #content="{ item, index }">
        <c-table
          v-if="item.tableColumn"
          :ref="(el) => setTableRef(el, index)"
          :tableConfig="item"
        />

        <slot :name="item.slot" :item="item" :index="index" />
      </template>
    </c-tabs>

    <template v-for="(item, index) in pageOption.tableConfig">
      <c-modal
        :ref="(el:any) => setDialogRef(el, index)"
        @confirm="confirm"
        :title="dialogTitle"
        :dialogConfig="dialogConfig(item.dialogConfig)"
      >
        <template #body>
          <Collapse
            title="表单"
            v-if="item.dialogConfig.formConfig"
            v-bind="item.dialogConfig.formConfig"
          >
            <template #content>
              <c-form
                :ref="(el) => setEditRef(el, index)"
                :disabled="isDetail"
                :model="pageData.editData"
                :formConfig="item.dialogConfig.formConfig"
              />
            </template>
          </Collapse>

          <slot :name="dialogSlot(index)" />
        </template>
      </c-modal>
    </template>
  </div>
</template>

<script lang="ts" setup>
import { Refresh, Search } from "@element-plus/icons-vue";

const { pageOption } = defineProps({
  pageOption: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

const pageData: any = defineModel("pageData");
const dialogType = ref();

const formRef: any = ref<any>();
const tableRef: any = ref<any>([]);
const dialogRef: any = ref<any>([]);
const editRef: any = ref<any>([]);

const active: any = ref(0);
const isDetail: any = ref<any>();
const dialogTitle: Ref<string> = ref<string>("");

//查询
function query() {
  return unref(formRef)
    .submitForm()
    .then((res: any) => {
      if (res && unref(tableRef)[unref(active)]) {
        return unref(tableRef)[unref(active)].query();
      } else if (pageOption.tableConfig[unref(active)].slotQuery) {
        return pageOption.tableConfig[unref(active)].slotQuery();
      }
    });
}
//重置
function reset() {
  unref(formRef).resetForm();

  if (unref(tableRef)[unref(active)]) {
    unref(tableRef)[unref(active)].reset();
    query();
  } else if (pageOption.tableConfig[unref(active)].slotQuery) {
    return pageOption.tableConfig[unref(active)].slotQuery();
  }
}
//编辑弹窗
function handleOpen({ type, data }: any) {
  if (type === "detail") {
    dialogTitle.value = $t("system.detail", "详情");
    isDetail.value = true;
  } else {
    if (type === "add") {
      dialogTitle.value = $t("system.add", "新增");
    } else {
      dialogTitle.value = $t("system.edit", "修改");
    }
    isDetail.value = false;
  }
  //弹框类型
  dialogType.value = type;
  //数据反显
  unref(pageData).editData = data;
  //打开弹窗
  unref(dialogRef)[unref(active)].handleOpen();
}
//关闭弹窗
function handleClose() {
  unref(dialogRef)[unref(active)].handleClose();
}
//确认
function confirm(handleConfirm: any) {
  unref(editRef)[0]
    .submitForm()
    .then((valid: any) => {
      if (valid) {
        handleConfirm();
      }
    });
}
//提交查询表单
function submitForm() {
  unref(formRef).submitForm();
}
//多选
function checkboxData() {
  return unref(tableRef)[unref(active)].checkboxData();
}
//新增
function addLine(row: any) {
  unref(tableRef)[unref(active)].addLine(row);
}
function tabChange() {
  query();
}
//循环ref获取
function setTableRef(el: any, index: number) {
  unref(tableRef)[unref(index)] = el;
}
function setDialogRef(el: any, index: number) {
  unref(dialogRef)[unref(index)] = el;
}
function setEditRef(el: any, index: number) {
  unref(editRef)[unref(index)] = el;
}
function dialogSlot(index: number) {
  return "dialog" + index;
}
function dialogConfig(config: any) {
  return {
    ...config,
    actions: [
      {
        operation: "confirm",
        show: () => unref(dialogType) !== "detail",
      },
    ],
  };
}
function getActive() {
  return unref(active);
}

onMounted(() => {
  if (pageOption.createLoad) {
    query();
  }
});

defineExpose({
  query,
  reset,
  addLine,
  submitForm,
  checkboxData,
  handleOpen,
  handleClose,
  getActive,
});
</script>

<style lang="scss" scoped>
::v-deep(.el-main) {
  padding: 10px !important;
}
</style>
