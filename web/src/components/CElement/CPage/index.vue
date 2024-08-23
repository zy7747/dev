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
          color="#0960bd"
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

    <c-tables
      ref="tablesRef"
      v-model="active"
      @tabChange="tabChange"
      :tableConfig="pageOption.tableConfig"
    >
      <template v-slot:[item] v-for="item in tableSlots()">
        <slot :name="item" />
      </template>
    </c-tables>

    <template v-for="(item, index) in pageOption.tableConfig">
      <c-modal
        :ref="(el:any) => setDialogRef(el, index)"
        @confirm="confirm"
        :title="dialogTitle"
        :dialogConfig="dialogConfig(item.editConfig)"
      >
        <template #body>
          <Collapse v-bind="item.editConfig.formConfig">
            <template #content>
              <c-form
                :ref="(el) => setEditRef(el, index)"
                :disabled="isDetail"
                :model="pageData.editData"
                :formConfig="item.editConfig.formConfig"
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
// 获取插槽对象
const slots = useSlots();
const prop = defineProps({
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
const tablesRef: any = ref<any>([]);

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
      if (res) {
        if (
          prop.pageOption.tableConfig[unref(active)] &&
          prop.pageOption.tableConfig[unref(active)].slotQuery
        ) {
          return prop.pageOption.tableConfig[unref(active)].slotQuery();
        } else {
          return unref(tablesRef).query();
        }
      }
    });
}
//重置
function reset() {
  unref(formRef).resetForm();

  if (unref(tablesRef)[unref(active)]) {
    unref(tablesRef)[unref(active)].reset();
    query();
  } else if (prop.pageOption.tableConfig[unref(active)].slotQuery) {
    return prop.pageOption.tableConfig[unref(active)].slotQuery();
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
function tableSlots(): any {
  // 获取所有插槽的名称
  return Object.keys(slots).filter((res: any) => {
    return res.indexOf("table_") !== -1 || res.indexOf("tab_") !== -1;
  });
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
  return unref(tablesRef).checkboxData();
}
//新增
function addLine(row: any) {
  unref(tablesRef).addLine(row);
}
//tab变更
function tabChange() {
  query();
}

function setDialogRef(el: any, index: number) {
  unref(dialogRef)[index] = el;
}
function setEditRef(el: any, index: number) {
  unref(editRef)[index] = el;
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
  getActive,
});
</script>

<style lang="scss" scoped>
::v-deep(.el-main) {
  padding: 10px !important;
}
</style>
