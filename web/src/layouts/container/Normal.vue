<template>
  <div class="common-layout">
    <el-container>
      <el-header
        :style="{
          height: `${theme.headerHeight}px`,
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
        :style="{ height: `calc(100vh - ${theme.headerHeight}px) !important` }"
      >
        <el-aside>
          <Sidebar :width="theme.asideWidth" :collapse="theme.collapse" />
        </el-aside>

        <el-main>
          <Breadcrumb class="breadcrumb" v-if="theme.breadcrumb" />
          <Tabs class="tabs" v-if="theme.tabs" />
          <AppMain class="app-main" />
        </el-main>
      </el-container>
    </el-container>
  </div>
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
}

.el-aside {
  width: auto;
}
</style>
