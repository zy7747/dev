<!--  -->
<template>
  <el-dialog
    v-model="visible"
    :title="dialogConfig.title"
    :width="dialogConfig.width ? dialogConfig.width : '500px'"
    draggable
  >
    <div>
      <slot name="body" />
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleConfirm"> 确定 </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
const emit = defineEmits(["confirm"]);

const prop: any = defineProps({
  dialogConfig: {
    text: "编辑弹框配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

const visible = ref(false);

function handleConfirm() {
  emit("confirm", prop.dialogConfig.handleConfirm);
}
function handleCancel() {
  visible.value = false;
}
function handleOpen() {
  visible.value = true;
}
function handleClose() {
  visible.value = false;
}

defineExpose({
  handleConfirm,
  handleCancel,
  handleOpen,
  handleClose,
});
</script>

<style lang="scss" scoped></style>
