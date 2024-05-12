<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header
        :style="{
          height: `${theme.headerHeight}px`,
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
  background: url("https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/banner/banner.png")
    0 no-repeat;
  background-size: 100%;
}

.el-main {
  padding: 0 100px;
}
</style>
