<!--  -->
<template>
  <div class="flex justify-between tabs">
    <div class="flex tags">
      <router-link
        v-for="tag in visitedViews"
        class="tag flex items-center"
        :key="tag.path"
        :class="isActive(tag) ? 'active' : ''"
        :to="{ path: tag.path, query: tag.query }"
      >
        <svg-icon :name="tag.icon" class="icon" />
        {{ tag.title }}

        <el-icon class="el-icon-close" @click="(e) => removeTab(e)">
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
function removeTab(e: any) {
  e.stopPropagation();
  const vRoute = unref(visitedViews)[unref(visitedViews).length - 2];

  Router.push(vRoute.path);

  // useTab.removeTab(route);
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
  border: 1px solid #ecebeb;
  .tag {
    color: #333;
    padding: 0 10px;
    border-right: 1px solid #ecebeb;
    &:hover {
      background: #eee;
    }

    &:hover > .el-icon-close {
      display: block;
    }
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
    display: none;
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
    border-left: 1px solid #ecebeb;
    padding: 0 10px;
    &:hover {
      cursor: pointer;
      color: #245fd4;
    }
  }
}

.active {
  background-color: #e7e8eb;
}
</style>
