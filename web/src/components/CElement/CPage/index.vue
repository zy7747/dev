<!--CPage-->
<template>
  <div class="CPage">
    <Collapse title="页面名称">
      <template #tools>
        <el-button type="primary" @click="query">查询</el-button>
        <el-button @click="reset">重置</el-button>
      </template>

      <template #content>
        <c-form
          ref="formRef"
          :model="pageData.queryData"
          :formConfig="pageOption.formConfig"
        />
      </template>
    </Collapse>

    <el-tabs @tab-change="tabChange" v-model="active" type="border-card">
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
          :dialogConfig="item.dialogConfig"
        >
          <template #body>
            <c-form
              ref="editRef"
              :disabled="disabled"
              :model="pageData.editData"
              :formConfig="item.dialogConfig.formConfig"
            />
          </template>
        </c-dialog>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
const formRef: any = ref<any>();
const tableRef: any = ref<any>();
const dialogRef: any = ref<any>();
const editRef: any = ref<any>();

const active: any = ref<any>(0);
const disabled: any = ref<any>();

const prop = defineProps({
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
        unref(tableRef)[unref(active)].query();
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
    disabled.value = true;
  } else {
    disabled.value = false;
  }
  prop.pageData.editData = data;
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
  if (prop.pageOption.createLoad) {
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
