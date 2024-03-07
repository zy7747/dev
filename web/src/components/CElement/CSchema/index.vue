<!--  -->
<template>
  <div style="width: 100%">
    <c-select
      v-if="type === 'select'"
      :options="options"
      v-model="value"
      v-bind="params"
    />
  </div>
</template>

<script lang="ts" setup>
const value: any = defineModel();

defineProps({
  type: {
    text: "类型",
    type: [String, Number],
    default: "",
  },
  options: {
    text: "选项列表",
    type: [Array, Object, Function],
    default: () => {
      return [];
    },
  },
  params: {
    text: "属性",
    type: [Array, Object, Function],
    default: () => {
      return {};
    },
  },
});
</script>

<style lang="scss" scoped></style>
