<!--  -->
<template>
  <div style="display: flex; align-items: center">
    <el-page-header @back="goBack"> </el-page-header>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>
        <a href="JavaScript:void(0)" @click="first">{{ "主页" }}</a>
      </el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item, index) in list" :key="index">
        <a href="JavaScript:void(0)" @click="pathChange(item)">
          {{ item.name }}
        </a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  list: {
    text: "图片列表",
    type: [Array as any],
    default: () => {
      return [];
    },
  },
});
const emit = defineEmits(["goBack", "pathChange", "first"]);
//返回
function goBack() {
  emit("goBack");
}
//路径变更
function pathChange(item: any) {
  emit("pathChange", item);
}
//根目录
function first() {
  emit("first");
}
</script>

<style lang="scss">
.el-page-header__left {
  margin-right: 0 !important;
}
</style>
