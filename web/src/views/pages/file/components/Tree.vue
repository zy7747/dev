<!--  -->
<template>
  <div style="padding-top: 20px">
    <c-input
      v-model="query"
      :style="`width:${width};margin-bottom:5px`"
      placeholder="Please enter keyword"
      @input="onQueryChanged"
    />

    <el-tree-v2
      ref="treeRef"
      :props="{
        value: 'id',
        label: 'fileName',
        children: 'children',
      }"
      :style="`width:${width};`"
      v-bind="$attrs"
      :filter-method="filterMethod"
      :height="700"
    >
      <template #default="{ node }">
        <span class="node-item">
          <img
            style="width: 20px; margin-right: 5px"
            src="@/assets/images/file/folder.png"
            alt=""
          />
          <span :title="node.label">{{ node.label }}</span>
        </span>
      </template>
    </el-tree-v2>
  </div>
</template>

<script lang="ts" setup>
const treeRef = ref();
const query = ref("");

defineProps({
  width: {
    type: [String],
    default: "100%",
  },
});

const onQueryChanged = () => {
  treeRef.value!.filter(query);
};

const filterMethod: any = (query: string, node: any) => {
  return node.fileName!.includes(unref(query));
};
</script>

<style lang="scss" scoped>
.node-item {
  font-size: 14px;
  display: flex;
  align-items: center;
}
</style>
