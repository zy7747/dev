<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header
        :style="{
          backgroundColor: theme.headerBackgroundColor,
          height: `${theme.headerHeight}px`,
        }"
      >
        <Logo
          :hasTitle="true"
          :collapse="true"
          :theme="theme"
          :width="theme.asideWidth"
          :height="theme.headerHeight"
        />
        <HeaderLine
          :width="theme.asideWidth"
          :backgroundColor="theme.headerBackgroundColor"
        />
      </el-header>

      <el-main>
        <Breadcrumb v-if="theme.breadcrumb" />
        <Tabs v-if="theme.tabs" />
        <AppMain />
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { AppMain, Logo, HeaderLine, Breadcrumb, Tabs } from "../components";

defineProps({
  theme: {
    text: "主题",
    type: [Object],
    default: () => {
      return {};
    },
  },
});
</script>

<style lang="scss" scoped>
.el-header {
  padding: 0;
  display: flex;
  align-items: center;
}

.el-main {
  padding: 0;
  overflow: auto;
}
</style>
