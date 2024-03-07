<template>
  <el-switch
    v-model="isDark"
    :title="isDark ? '夜间模式' : '明亮模式'"
    inline-prompt
    :active-icon="Moon"
    :inactive-icon="Sunny"
    style="--el-switch-off-color: #666"
  />
</template>

<script lang="ts" setup>
import { useDark } from "@vueuse/core";
import Moon from "@/icons/svg/moon.svg";
import Sunny from "@/icons/svg/sunny.svg";

const isDark = useDark();

// 监视主题改变，并变更vxe-table的css属性值
watch(
  isDark,
  (newVal) => {
    if (newVal === true) {
      /*暗色主题*/
      // 文字颜色 body/工具栏
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-font-color", "#ffffffd1");
      // 表格body背景颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-body-background-color", "#18181c");
      // 表格body背景颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-background-color", "#18181c");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-pager-background-color", "#18181c");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty(
          "--vxe-table-row-striped-background-color",
          "#18181c"
        );
      // 标题头文字颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-header-font-color", "#ffffffd1");
      // 标题头背景颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-toolbar-background-color", "#080808");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-header-background-color", "#080808");
      // 标题头右侧图标背景颜色 按钮默认颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-button-default-background-color", "#080808");
    } else {
      /*亮色主题*/
      // 文字颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-font-color", "#333639");
      // 表格body背景颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-body-background-color", "#FFF");
      // 表格body背景颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-background-color", "#FFF");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-pager-background-color", "#FFF");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-toolbar-background-color", "#FFF");
      // 标题头右侧图标背景颜色 按钮默认颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-button-default-background-color", "#FFF");
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-row-striped-background-color", "#FFF");
      // 标题头文字颜色
      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-header-font-color", "#606266");

      document
        .getElementsByTagName("body")[0]
        .style.setProperty("--vxe-table-header-background-color", "#f8f8f9");
    }
  },
  {
    immediate: true,
    deep: true,
  }
);
</script>

<style lang="scss" scoped></style>
