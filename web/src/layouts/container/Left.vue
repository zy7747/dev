<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-aside
        :style="{
          backgroundColor: theme.asideBackgroundColor,
        }"
      >
        <Logo :theme="theme" :hasTitle="false" :height="theme.headerHeight" />
        <Sidebar
          :backgroundColor="theme.asideBackgroundColor"
          :width="theme.asideWidth"
          :collapse="false"
        />
      </el-aside>
      <el-container>
        <el-header
          :style="{
            height: `${theme.headerHeight}px`,
          }"
        >
          <HeaderLine :backgroundColor="theme.headerBackgroundColor" />
        </el-header>
        <el-main>
          <Breadcrumb v-if="theme.breadcrumb" />
          <Tabs v-if="theme.tabs" />
          <AppMain />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { AppMain, Logo, Sidebar, Breadcrumb, Tabs } from "../components";

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
.el-aside {
  height: 100vh;
  width: auto;
}
.el-main {
  padding: 0;
  overflow: auto;
}
</style>
