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
    :default-checked-keys="defaultChecked"
    @check="getCheckedKeys"
    show-checkbox
    :height="208"
  />
</template>

<script lang="ts" setup>
import type { TreeNode } from "element-plus/es/components/tree-v2/src/types";

const treeRef = ref();
//选中与半选的和
const data: any = defineModel();
//选中
const checked: any = ref([]);
//半选
const halfChecked: any = ref([]);
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

//默认选中
const defaultChecked: any = ref([]);

function findDefaultChecked(tree: any, list: any) {
  if (unref(data).length) {
    tree.forEach((p: any) => {
      if (!p.children && unref(data).includes(p.id)) {
        list.push(p.id);
      }

      if (p.children) {
        findDefaultChecked(p.children, list);
      }
    });
  }
}

watch(
  option,
  (tree) => {
    const list: any = [];

    findDefaultChecked(tree, list);

    defaultChecked.value = list;
  },
  { immediate: true }
);

const getCheckedKeys = () => {
  data.value = [
    ...unref(treeRef).getCheckedKeys(),
    ...unref(treeRef).getHalfCheckedKeys(),
  ];

  checked.value = unref(treeRef).getCheckedKeys();

  halfChecked.value = unref(treeRef).getHalfCheckedKeys();
};

const onQueryChanged = () => {
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
