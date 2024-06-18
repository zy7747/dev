<template>
  <div class=".my-filter-content">
    <template v-if="params.column.filters.length">
      <ul class="my-fc-search-list my-fc-search-list-body">
        <li
          class="my-fc-search-item"
          v-for="(item, sIndex) in params.column.filters"
          :key="sIndex"
        >
          <vxe-checkbox v-model="item.checked">{{ item.value }}</vxe-checkbox>
        </li>
      </ul>
    </template>
    <template v-else>
      <div class="my-fc-search-empty">无匹配项</div>
    </template>
  </div>
</template>

<script lang="ts" setup>
const prop = defineProps({
  params: {
    text: "类型",
    type: [Object] as any,
  },
});

console.log(prop.params.column);
</script>

<style lang="scss" scoped>
.my-filter-content {
  padding: 10px;
  user-select: none;
}
.my-filter-content .my-fc-search .my-fc-search-top {
  position: relative;
  padding: 5px 0;
}
.my-filter-content .my-fc-search .my-fc-search-top > input {
  border: 1px solid #ababab;
  padding: 0 20px 0 2px;
  width: 200px;
  height: 22px;
  line-height: 22px;
}
.my-filter-content .my-fc-search .my-fc-search-content {
  padding: 2px 10px;
}
.my-filter-content .my-fc-search-empty {
  text-align: center;
  padding: 20px 0;
}
.my-filter-content .my-fc-search-list {
  margin: 0;
  padding: 0;
  list-style: none;
}
.my-filter-content .my-fc-search-list-body {
  overflow: auto;
  height: 120px;
}
.my-filter-content .my-fc-search-list .my-fc-search-item {
  padding: 2px 0;
  display: block;
}
.my-filter-content .my-fc-footer {
  text-align: right;
  padding-top: 10px;
}
.my-filter-content .my-fc-footer button {
  padding: 0 15px;
  margin-left: 15px;
}
</style>
