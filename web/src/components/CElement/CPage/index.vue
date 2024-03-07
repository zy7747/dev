<!--CPage-->
<template>
  <div class="CPage">
    <Collapse title="页面名称">
      <template #tools>
        <el-button type="primary" @click="query">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </template>

      <template #content>
        <c-form ref="formRef" :formConfig="pageOption.formConfig" />
      </template>
    </Collapse>

    <Collapse title="页面名称">
      <template #content>
        <el-tabs v-model="active" type="border-card">
          <el-tab-pane
            v-for="(item, index) in pageOption.tableConfig"
            :name="index"
          >
            <template #label>
              <span>{{ item.title }}</span>
            </template>
            <c-table ref="tableRef" :tableConfig="item" />
            <c-dialog
              @confirm="confirm"
              ref="dialogRef"
              :dialogConfig="item.dialogConfig"
            >
              <template #body>
                <c-form
                  :disabled="disabled"
                  ref="editRef"
                  :formConfig="item.dialogConfig.formConfig"
                />
              </template>
            </c-dialog>
          </el-tab-pane>
        </el-tabs>
      </template>
    </Collapse>
  </div>
</template>

<script lang="ts" setup>
const active: any = ref<any>(0);
const disabled: any = ref<any>();

const formRef: any = ref<any>();
const tableRef: any = ref<any>();
const dialogRef: any = ref<any>();
const editRef: any = ref<any>();

defineProps({
  pageOption: {
    text: "页面配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

//提交表单
function submitForm() {
  unref(formRef).submitForm();
}
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
function resetForm() {
  unref(formRef).resetForm();
  unref(tableRef)[unref(active)].query();
}
//编辑弹窗
function handleOpen({ type }: any) {
  if (type === "add") {
    disabled.value = false;
  } else if (type === "edit") {
    disabled.value = false;
  } else if (type === "detail") {
    disabled.value = true;
  }
  unref(dialogRef)[unref(active)].handleOpen();
}
//确认
function confirm(handleConfirm: any) {
  unref(editRef)
    [unref(active)].submitForm()
    .then((valid: any) => {
      if (valid) {
        handleConfirm().then((res: any) => {
          if (res) {
            unref(dialogRef)[unref(active)].handleClose();
          }
        });
      }
    });
}
//多选
const checkboxData = computed(() => {
  return unref(tableRef)[unref(active)].checkboxData;
});

defineExpose({
  query,
  resetForm,
  submitForm,
  checkboxData,
  handleOpen,
});
</script>

<style lang="scss" scoped>
.el-main {
  padding: 0;
}
</style>
