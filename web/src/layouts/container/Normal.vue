<template>
  <el-container>
    <el-header
      :style="{
        height: `${theme.headerHeight}px`,
        backgroundColor: theme.headerBackgroundColor,
      }"
    >
      <Logo
        :hasTitle="true"
        :collapse="true"
        :width="theme.asideWidth"
        :height="theme.headerHeight"
        :theme="theme"
      />
      <HeaderLine :width="theme.asideWidth" />
    </el-header>

    <el-container
      class="app-main"
      :style="{
        height: `calc(100vh - ${theme.headerHeight}px) !important`,
      }"
    >
      <el-aside
        :width="theme.asideWidth"
        :style="{ backgroundColor: theme.asideBackgroundColor }"
      >
        <Sidebar :width="theme.asideWidth" :collapse="theme.collapse" />
      </el-aside>

      <el-main>
        <div class="tool">
          <Breadcrumb class="breadcrumb" v-if="theme.breadcrumb" />
          <Tabs class="tabs" v-if="theme.tabs" />
        </div>

        <AppMain class="page-main" />
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {
  AppMain,
  Breadcrumb,
  Tabs,
  Sidebar,
  HeaderLine,
  Logo,
} from "../components";

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
  border-bottom: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
}

.el-main {
  padding: 0;
}

.page-main {
  padding: 0 10px;
}

.el-aside {
  border-right: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
}
</style>
