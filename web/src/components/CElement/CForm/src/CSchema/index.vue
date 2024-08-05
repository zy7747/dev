<!--  -->
<template>
  <div style="width: 100%">
    <c-select
      v-if="type === 'select'"
      :placeholder="getPlaceholder('select', item)"
      :options="option"
      v-model="value"
      v-bind="params"
    />
    <c-radio
      v-else-if="type === 'radio'"
      :options="option"
      v-model="value"
      v-bind="params"
    />
    <c-date
      style="width: 100%"
      v-else-if="type === 'date'"
      v-model="value"
      v-bind="params"
    />
    <c-checkbox
      v-else-if="type === 'checkbox'"
      :options="option"
      v-model="value"
      v-bind="params"
    />
    <c-input
      v-else-if="type === 'input'"
      :placeholder="getPlaceholder('input', item)"
      v-model="value"
      v-bind="params"
    />
    <c-number
      v-else-if="type === 'number'"
      :placeholder="getPlaceholder('input', item)"
      v-model="value"
      v-bind="params"
    />
    <c-treeSelect
      v-else-if="type === 'treeSelect'"
      :placeholder="getPlaceholder('select', item)"
      :options="option"
      v-model="value"
      v-bind="params"
    />
    <Editor
      v-else-if="type === 'editor'"
      :placeholder="getPlaceholder('input', item)"
      v-model="value"
      v-bind="params"
    />
    <c-treeSelect
      v-else-if="type === 'allSelect'"
      :placeholder="getPlaceholder('select', item)"
      :options="[
        {
          label: '全部',
          value: 'all',
          children: options,
        },
      ]"
      multiple
      collapse-tags
      check-on-click-node
      show-checkbox
      v-model="value"
      v-bind="params"
    />
    <IconSelect
      v-else-if="type === 'icon'"
      :placeholder="getPlaceholder('select', item)"
      :options="option"
      v-model="value"
      v-bind="params"
    />
  </div>
</template>

<script lang="ts" setup>
const value: any = defineModel();

const prop = defineProps({
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

const option = computed(() => {
  if (typeof prop.options === "function") {
    return prop.options();
  } else if (typeof prop.options === "object") {
    return prop.options;
  }
});

function getPlaceholder(type: string, item: any) {
  if (type === "select") {
    if (item.label) {
      return $t("form.please select", "请选择") + " " + item.label;
    } else if (item.title) {
      return $t("form.please select", "请选择") + " " + item.title;
    } else {
      return $t("form.please select", "请选择");
    }
  } else if (type === "input") {
    if (item.label) {
      return $t("form.please input", "请输入") + " " + item.label;
    } else if (item.title) {
      return $t("form.please input", "请输入") + " " + item.title;
    } else {
      return $t("form.please input", "请输入");
    }
  }
}
</script>

<style lang="scss" scoped></style>
