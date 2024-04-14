<template>
  <div class="common-layout">
    <el-container>
      <el-header
        :style="{
          backgroundColor: theme.headerBackgroundColor,
          color: theme.headerColor,
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
        <HeaderLine
          :width="theme.asideWidth"
          :backgroundColor="theme.headerBackgroundColor"
        />
      </el-header>

      <el-container
        :style="{ height: `calc(100vh - ${theme.headerHeight}px) !important` }"
      >
        <el-aside :style="{ backgroundColor: theme.asideBackgroundColor }">
          <Sidebar
            :backgroundColor="theme.asideBackgroundColor"
            :width="theme.asideWidth"
            :collapse="theme.collapse"
          />
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
.el-main {
  position: relative;
  padding: 0;
  .tabs {
    width: 100%;
    z-index: 1;
    position: absolute;
    background-color: #f5f7fa;
  }

  .app-main {
    padding-top: 26px;
  }
}

.el-aside {
  width: auto;
}
</style>
