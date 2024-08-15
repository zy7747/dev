<!--  -->
<template>
  <div>
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
      :height="600"
    >
      <template #default="{ node }">
        <span class="node-item" @contextmenu.prevent.stop="nodeClick(node)">
          <img
            style="width: 20px; margin-right: 5px"
            src="https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/file/folder.png"
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

function nodeClick(node: any) {
  console.log(node);
}

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
