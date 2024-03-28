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
        <svg-icon :name="`app-${tag.icon}`" class="icon" />
        {{ tag.title }}
        <span class="el-icon-close" @click="removeTab(tag)" />
      </router-link>
    </div>

    <ul class="flex">
      <li class="action">
        <el-icon><Refresh /></el-icon>
      </li>
      <li class="action">
        <el-icon><Tools /></el-icon>
      </li>
      <li class="action">
        <el-icon><ArrowDownBold /></el-icon>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { ArrowDownBold, Tools, Refresh } from "@element-plus/icons-vue";
import { useTabStore } from "@/store/tabs";

const Route = useRoute();
const useTab = useTabStore();

const visitedViews: any = ref([]);

function isActive(route: any) {
  return route.path === Route.path;
}

function removeTab(route: any) {
  useTab.removeTab(route);
}

watch(
  Route,
  (route) => {
    useTab.addTab(route);
    visitedViews.value = useTab.visitedViews;
  },
  { immediate: true }
);
</script>

<style lang="scss" scoped>
.tabs {
  border: 1px solid #ecebeb;
  .tag {
    overflow: hidden;
    color: #333;
    padding: 2px;
    border-right: 1px solid #ecebeb;
    &:hover {
      background: #eee;
    }
  }
  .el-icon-close {
    width: 16px;
    height: 16px;
    border-radius: 50%;
    text-align: end;
    transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
    transform-origin: 100% 50%;
    &:before {
      transform: scale(0.6);
      display: inline-block;
      vertical-align: -3px;
    }
    &:hover {
      background-color: #245fd4;
      color: #fff;
    }
  }
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
  background-color: pink;
}
</style>
