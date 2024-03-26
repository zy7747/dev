<!--  -->
<template>
  <c-input
    v-model="query"
    :style="`width:${width};margin-bottom:5px`"
    placeholder="Please enter keyword"
    @input="onQueryChanged"
  />
  <el-tree-v2
    ref="treeRef"
    :props="props"
    :style="`width:${width};`"
    v-bind="$attrs"
    :data="option"
    :filter-method="filterMethod"
    :default-checked-keys="checked"
    @check="getCheckedKeys"
    show-checkbox
    :height="208"
  />
</template>

<script lang="ts" setup>
import type { TreeNode } from "element-plus/es/components/tree-v2/src/types";

const treeRef = ref();
// const checked = ref();
const checked: any = defineModel();
const query = ref("");

const { options } = defineProps({
  props: {
    type: [Object],
    default: () => {
      return {
        value: "value",
        label: "label",
        children: "children",
      };
    },
  },
  width: {
    type: [String],
    default: "100%",
  },
  options: {
    text: "选项列表",
    type: [Array, Object, Function, String] as any,
    default: () => {
      return [];
    },
  },
});

const option = computed(() => {
  if (typeof options === "function") {
    return options();
  } else if (typeof options === "object") {
    return options;
  }
});

const getCheckedKeys = () => {
  checked.value = JSON.parse(JSON.stringify(unref(treeRef).getCheckedKeys()));
};

const onQueryChanged = (query: string) => {
  treeRef.value!.filter(query);
};

const filterMethod: any = (query: string, node: TreeNode) => {
  return node.label!.includes(query);
};

defineExpose({
  getCheckedKeys,
});
</script>

<style lang="scss" scoped></style>
