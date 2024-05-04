<!--  -->
<template>
  <div class="flex justify-between tabs">
    <div class="flex tags">
      <router-link
        v-for="(tag, index) in visitedViews"
        class="tag flex items-center"
        :key="tag.path"
        :class="isActive(tag) ? 'active' : ''"
        :to="{ path: tag.path, query: tag.query }"
      >
        <svg-icon :name="tag.icon" class="icon" />
        {{ tag.title }}

        <el-icon
          v-show="isActive(tag)"
          class="el-icon-close"
          @click.prevent.native="removeTab(index)"
        >
          <CircleCloseFilled />
        </el-icon>
      </router-link>
    </div>

    <ul class="flex">
      <li class="action" @click="refresh">
        <el-icon><Refresh /></el-icon>
      </li>
      <li class="action" @click="themeOpen">
        <el-icon><Tools /></el-icon>
      </li>
      <li class="action">
        <el-icon><ArrowDownBold /></el-icon>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import {
  ArrowDownBold,
  Tools,
  Refresh,
  CircleCloseFilled,
} from "@element-plus/icons-vue";
import { useTabStore } from "@/store/tabs";
import { useThemeStore } from "@/store/theme";
const themeStore = useThemeStore();

const Route = useRoute();
const Router = useRouter();
const useTab = useTabStore();
const visitedViews = useTab.visitedViews;

// 是否选中标签
function isActive(route: any) {
  return route.path === Route.path;
}
//删除路由
function removeTab(index: number) {
  useTab.removeTab(visitedViews[index]);

  if (visitedViews.length === 0) {
    Router.push({
      path: "/dashboard",
    });
  } else {
    Router.push({
      path: visitedViews[index - 1].fullPath,
    });
  }
}
//新增路由
function addTab(route: any) {
  useTab.addTab(route);
}
function themeOpen() {
  themeStore.open();
}
function refresh() {
  location.reload();
}

watch(
  Route,
  (value) => {
    addTab(value);
  },
  { immediate: true }
);
</script>

<style lang="scss" scoped>
.tabs {
  .tag {
    color: #000000;
    background-color: #fff;
    padding: 0 10px;
    border-radius: 5px;
    margin-right: 8px;
    &:hover {
      color: #fff;
      background: #303b75;
    }
  }

  .active {
    color: #fff;
    background: #303b75;
  }

  .icon {
    width: 16px;
    height: 16px;
    margin-right: 5px;
  }

  .el-icon-close {
    margin-left: 5px;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
    transform-origin: 100% 50%;

    &:hover {
      background-color: #245fd4;
      color: #fff;
    }
  }
  //右侧菜单
  .action {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid var(--el-border-color-darker);
    padding: 0 10px;
    &:hover {
      cursor: pointer;
      color: #245fd4;
    }
  }
}
</style>
