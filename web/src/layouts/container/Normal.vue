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
          :theme="theme"
          :hasTitle="true"
          :collapse="true"
          :width="theme.asideWidth"
          :height="theme.headerHeight"
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
          <TagsView class="tagsView" v-if="theme.tagsView" />
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
  TagsView,
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
  .tagsView {
    width: 100%;
    z-index: 1;
    position: absolute;
    background-color: #f5f7fa;
  }

  .app-main {
    padding-top: 30px;
  }
}

.el-aside {
  width: auto;
}
</style>
