<!--CPage-->
<template>
  <div class="CPage">
    <Collapse title="页面名称">
      <template #tools>
        <c-button
          size="small"
          type="primary"
          @click="query"
          text="查询"
          :icon="Search"
        />
        <c-button
          plain
          size="small"
          @click="reset"
          text="重置"
          :icon="Refresh"
        />
      </template>

      <template #content>
        <c-form
          ref="formRef"
          :model="pageData.queryData"
          :formConfig="pageOption.formConfig"
        />
      </template>
    </Collapse>

    <el-tabs
      @tab-change="tabChange"
      v-model="active"
      type="border-card"
      v-if="pageOption.tableConfig && pageOption.tableConfig.length"
    >
      <el-tab-pane
        v-for="(item, index) in pageOption.tableConfig"
        :name="index"
      >
        <template #label>
          <span>{{ item.title }}</span>
        </template>

        <c-table ref="tableRef" :tableConfig="item" />

        <c-dialog
          ref="dialogRef"
          @confirm="confirm"
          :title="dialogTitle"
          :dialogConfig="item.dialogConfig"
        >
          <template #body>
            <Collapse title="表单">
              <template #content>
                <c-form
                  ref="editRef"
                  :disabled="disabled"
                  :model="pageData.editData"
                  :formConfig="item.dialogConfig.formConfig"
                />
              </template>
            </Collapse>
          </template>
        </c-dialog>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { Refresh, Search } from "@element-plus/icons-vue";

const formRef: any = ref<any>();
const tableRef: any = ref<any>();
const dialogRef: any = ref<any>();
const editRef: any = ref<any>();

const active: any = ref<any>(0);
const disabled: any = ref<any>();
const dialogTitle = ref("");

const { pageData, pageOption } = defineProps({
  pageOption: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
  pageData: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

//查询
function query() {
  unref(formRef)
    .submitForm()
    .then((res: any) => {
      if (res) {
        if (tableRef.value) {
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
    dialogTitle.value = "详情";
    disabled.value = true;
  } else {
    if (type === "add") {
      dialogTitle.value = "新增";
    } else {
      dialogTitle.value = "修改";
    }
    disabled.value = false;
  }

  pageData.editData = data;
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
