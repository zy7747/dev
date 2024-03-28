<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-aside
        :style="{
          backgroundColor: theme.asideBackgroundColor,
        }"
      >
        <Logo
          :theme="theme"
          :collapse="theme.collapse"
          :height="theme.headerHeight"
        />
        <Sidebar
          :backgroundColor="theme.asideBackgroundColor"
          :width="theme.asideWidth"
          :collapse="theme.collapse"
        />
      </el-aside>

      <el-container>
        <el-header
          :style="{
            backgroundColor: theme.headerBackgroundColor,
            height: `${theme.headerHeight}px`,
          }"
        >
          <HeaderLine
            :width="container.asideWidth"
            :backgroundColor="theme.headerBackgroundColor"
          />
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
import {
  AppMain,
  Logo,
  HeaderLine,
  Sidebar,
  Breadcrumb,
  Tabs,
} from "../components";

const prop = defineProps({
  theme: {
    text: "主题",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

const container: any = computed(() => {
  const width = prop.theme.asideWidth;

  return {
    headerWidth: `calc(100vw - ${width}px)`,
  };
});
</script>

<style lang="scss" scoped>
.el-aside {
  height: 100vh;
  width: auto;
}

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
