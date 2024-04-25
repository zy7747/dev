<!-- input-number -->
<template>
  <el-input-number
    v-model="value"
    v-bind="$attrs"
    :size="size"
    :style="`width:${width};`"
    :placeholder="placeholder"
    :value-on-clear="null"
  />
</template>

<script lang="ts" setup>
defineProps({
  size: {
    text: "大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  width: {
    type: [String],
    default: "100%",
  },
  placeholder: {
    text: "提示语句",
    type: [String],
    default: () => {
      return $t("form.please input", "请输入");
    },
  },
});

const value: any = defineModel();
</script>

<style lang="scss" scoped></style>
