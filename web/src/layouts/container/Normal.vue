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
        <el-aside>
          <Sidebar
            :backgroundColor="theme.asideBackgroundColor"
            :width="theme.asideWidth"
            :collapse="theme.collapse"
          />
        </el-aside>

        <el-main>
          <Breadcrumb v-if="theme.breadcrumb" />
          <TagsView v-if="theme.tagsView" />
          <AppMain />
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
  padding: 10px;
  overflow: auto;
}

.el-aside {
  width: auto;
}
</style>
