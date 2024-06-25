<!--    -->
<template>
  <el-button
    :size="size"
    v-show="show"
    :loading="attrs.loading || loading"
    @click="click"
    v-hasPermission="attrs.permission"
  >
    <vxe-icon :name="vxeIcon" v-if="vxeIcon"></vxe-icon>
    {{ text }}
  </el-button>
</template>

<script lang="ts" setup>
import { useAttrs } from "vue";
const attrs: any = useAttrs();

defineProps({
  vxeIcon: {
    text: "vxe图标",
    type: [String],
  },
  text: {
    text: "按钮名称",
    type: [String],
    default: () => {
      return "";
    },
  },
  hasPermission: {
    text: "权限列表",
    type: [Array],
    default: () => {
      return [];
    },
  },
  size: {
    text: "按钮大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  show: {
    text: "是否显示",
    type: [Boolean, Function],
    default: () => {
      return true;
    },
  },
});

const loading = ref(false);

function click() {
  if (!attrs.onHandleClick) return;

  loading.value = true;

  const result = attrs.onHandleClick();

  if (Object.prototype.toString.call(result) === "[object Promise]") {
    result.finally(() => {
      loading.value = false;
    });
  } else {
    loading.value = false;
  }
}
</script>

<style lang="scss" scoped></style>
