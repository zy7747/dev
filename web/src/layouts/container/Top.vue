<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header
        :style="{
          height: `${theme.headerHeight}px`,
          backgroundColor: theme.headerBackgroundColor,
        }"
      >
        <Logo :hasTitle="true" :collapse="true" :theme="theme" />
        <HeaderLine :width="theme.asideWidth" />
      </el-header>

      <el-main
        class="app-main"
        :style="{
          height: `calc(100vh - ${theme.headerHeight}px) !important`,
        }"
      >
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
  background-size: 100%;
  border-bottom: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
}

.el-main {
  padding: 0 100px;
  background: url("https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/main/page-main.jpg")
    0 0 no-repeat;
  background-position: center center;
  background-size: 130% 100%;
}
</style>
