<!--CPage-->
<template>
  <div class="CPage">
    <Collapse :title="pageOption.title">
      <template #tools>
        <c-button
          size="small"
          type="primary"
          @click="query"
          :text="$t('system.query')"
          :icon="Search"
        />
        <c-button
          plain
          size="small"
          @click="reset"
          :text="$t('system.reset')"
          :icon="Refresh"
        />
      </template>

      <template #content>
        <c-form
          @keyup.enter.native="query"
          ref="formRef"
          :model="pageData.queryData"
          :formConfig="pageOption.formConfig"
        />
      </template>
    </Collapse>

    <c-tabs
      v-if="pageOption.tableConfig && pageOption.tableConfig.length"
      @tabChange="tabChange"
      v-model="active"
      type="border-card"
      :options="pageOption.tableConfig"
    >
      <template #content="{ item, index }">
        <c-table :ref="(el) => setTableRef(el, index)" :tableConfig="item" />
      </template>
    </c-tabs>

    <template v-for="(item, index) in pageOption.tableConfig" :key="index">
      <c-dialog
        :ref="(el) => setDialogRef(el, index)"
        @confirm="confirm"
        :title="dialogTitle"
        :dialogConfig="item.dialogConfig"
      >
        <template #body>
          <Collapse title="表单">
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
      </c-dialog>
    </template>
  </div>
</template>

<script lang="ts" setup>
import { Refresh, Search } from "@element-plus/icons-vue";

import { template } from "lodash";

const pageData: any = defineModel("pageData");

const formRef: any = ref<any>();
const tableRef: any = ref<any>([]);
const dialogRef: any = ref<any>([]);
const editRef: any = ref<any>([]);

const active: any = ref<any>(0);
const isDetail: any = ref<any>();
const dialogTitle: Ref<string> = ref<string>("");
// const slots = useSlots();

const { pageOption } = defineProps({
  pageOption: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

function dialogSlot(index: number) {
  return "dialog" + index;
}

//查询
function query() {
  unref(formRef)
    .submitForm()
    .then((res: any) => {
      if (res) {
        if (unref(tableRef)[unref(active)]) {
          unref(tableRef)[unref(active)].query();
        }
      }
    });
}
//重置
function reset() {
  unref(formRef).resetForm();
  unref(tableRef)[unref(active)].query();
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
  unref(pageData).editData = data;

  unref(dialogRef)[unref(active)].handleOpen();
}
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
});
</script>

<style lang="scss" scoped></style>
