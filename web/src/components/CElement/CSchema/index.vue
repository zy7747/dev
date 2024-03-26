<!--  -->
<template>
  <div style="width: 100%">
    <c-select
      v-if="type === 'select'"
      :placeholder="
        $t('form.please select') + ' ' + (item.label ? item.label : item.title)
      "
      :options="options"
      v-model="value"
      v-bind="params"
    />
    <c-radio
      v-else-if="type === 'radio'"
      :options="options"
      v-model="value"
      v-bind="params"
    />
    <c-checkbox
      v-else-if="type === 'checkbox'"
      :options="options"
      v-model="value"
      v-bind="params"
    />
    <c-input
      v-else-if="type === 'input'"
      :placeholder="
        $t('form.please input') + ' ' + (item.label ? item.label : item.title)
      "
      v-model="value"
      v-bind="params"
    />
    <c-number
      v-else-if="type === 'number'"
      :placeholder="
        $t('form.please input') + ' ' + (item.label ? item.label : item.title)
      "
      v-model="value"
      v-bind="params"
    />
    <c-treeSelect
      v-else-if="type === 'treeSelect'"
      :placeholder="
        $t('form.please select') + ' ' + (item.label ? item.label : item.title)
      "
      :options="options"
      v-model="value"
      v-bind="params"
    />
    <IconSelect
      v-else-if="type === 'icon'"
      :placeholder="
        $t('form.please select') + ' ' + (item.label ? item.label : item.title)
      "
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
    type: [String],
    default: "",
  },
  options: {
    text: "选项列表",
    type: [Array, Object, Function, String],
    default: () => {
      return [];
    },
  },
  params: {
    text: "属性",
    type: [Array, Object, Function] as any,
    default: () => {
      return {};
    },
  },
  item: {
    text: "表单信息",
    type: [Array, Object, Function] as any,
    default: () => {
      return {};
    },
  },
});
</script>

<style lang="scss" scoped></style>
