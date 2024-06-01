<!--  -->
<template>
  <VxeModal
    v-model="visible"
    :title="title ? title : dialogConfig.title"
    :width="dialogConfig.width ? dialogConfig.width : '500px'"
    show-zoom
    resize
    show-footer
    remember
    :mask="false"
    destroy-on-close
    esc-closable
  >
    <template #corner>
      <VxeIcon name="minus" class="close"></VxeIcon>
    </template>
    <template #default>
      <dom></dom>
    </template>

    <template #footer>
      <div class="flex justify-center">
        <template v-for="item in dialogConfig.actions">
          <template v-if="item.show ? item.show() : true">
            <c-button
              v-if="item.operation === 'confirm'"
              text="确定"
              round
              type="primary"
              size="small"
              :icon="CircleCheck"
              @handleClick="handleConfirm"
            />
            <c-button
              v-else
              :text="item.text"
              round
              size="small"
              v-bind="item"
              @handleClick="item.click()"
            />
          </template>
        </template>
        <c-button
          :icon="CircleClose"
          text="取消"
          round
          size="small"
          @click="handleCancel"
        />
      </div>
    </template>
  </VxeModal>
</template>

<script lang="ts" setup>
import { VxeModal, VxeIcon } from "vxe-table";
import { CircleCheck, CircleClose } from "@element-plus/icons-vue";

const emit = defineEmits(["confirm"]);

const prop: any = defineProps({
  dialogConfig: {
    text: "编辑弹框配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
  title: {
    text: "弹框标题",
    type: [String],
    default: "",
  },
  dom: {
    type: [Object],
  },
});

const visible = ref(true);

function handleConfirm() {
  return emit("confirm", prop.dialogConfig.handleConfirm);
}
function handleOpen() {
  visible.value = true;
}
function handleClose() {
  visible.value = false;
}
function handleCancel() {
  visible.value = false;
}

defineExpose({
  handleConfirm,
  handleCancel,
  handleOpen,
  handleClose,
});
</script>

<style lang="scss">
.close:hover {
  cursor: pointer;
  color: #61aefe;
}

.vxe-modal--footer {
  border-top: 1px solid #e1e3e7;
  padding: 10px 0 !important;
}

.vxe-modal--content {
  padding: 0 !important;
}
</style>
