<!--  -->
<template>
  <div class="common-layout">
    <el-container>
      <el-aside
        :style="{
          backgroundColor: theme.asideBackgroundColor,
        }"
      >
        <Logo :hasTitle="false" :height="theme.headerHeight" />
        <Sidebar
          :backgroundColor="theme.asideBackgroundColor"
          :width="theme.asideWidth"
          :collapse="false"
        />
      </el-aside>
      <el-main>
        <Breadcrumb v-if="theme.breadcrumb" />
        <TagsView v-if="theme.tagsView" />
        <AppMain />
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { AppMain, Logo, Sidebar, Breadcrumb, TagsView } from "../components";

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
  padding: 10px;
  overflow: auto;
}
</style>
