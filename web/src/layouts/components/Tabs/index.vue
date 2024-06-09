<!--  -->
<template>
  <div class="flex tabs">
    <div class="action-right" @click="leftSlide">
      <el-icon><ArrowLeftBold /></el-icon>
    </div>
    <div class="flex tags" ref="tags">
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
    <div class="action" @click="rightSlide">
      <el-icon><ArrowRightBold /></el-icon>
    </div>
    <ul class="flex">
      <li class="action" @click="refresh">
        <el-icon><Refresh /></el-icon>
      </li>
      <li class="action" @click="themeOpen">
        <el-icon><Tools /></el-icon>
      </li>
      <li class="action" @click="otherClick">
        <CDropdown :options="options" @command="command">
          <template #title>
            <el-icon><ArrowDownBold /></el-icon>
          </template>
        </CDropdown>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import {
  ArrowDownBold,
  ArrowLeftBold,
  ArrowRightBold,
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
const tags = ref();

// 是否选中标签
function isActive(route: any) {
  return route.path === Route.path;
}
//左滑块
function leftSlide() {
  if (unref(tags).scrollLeft - 500 > 0) {
    unref(tags).scrollLeft -= 500;
  } else {
    unref(tags).scrollLeft = 0;
  }
}
//右滑块
function rightSlide() {
  unref(tags).scrollLeft += 500;
}
//
function otherClick() {}

function command(row: any) {
  console.log(row);
}

const options = ref([
  { icon: "访客", label: "关闭其他", value: "closeOther" },
  { icon: "切换账号", label: "关闭左侧", value: "closeLeft" },
  { icon: "关闭", label: "关闭右侧", value: "closeRight" },
]);

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

useTab.setTab();

watch(
  Route,
  (value) => {
    addTab(value);
  },
  { immediate: true }
);
</script>

<style lang="scss" scoped>
.tags {
  width: calc(100% - 185px);
  overflow: auto;
  scrollbar-width: none; /* Firefox */
}

.tabs {
  border: 1px solid var(--el-border-color-darker);
  background-color: var(--el-fill-color-light);
  .tag {
    display: flex;
    justify-content: center;
    padding: 0 10px;
    max-width: 500px;
    min-width: 130px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
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
    border-left: 1px solid var(--el-border-color-darker);
    padding: 0 10px;
    &:hover {
      cursor: pointer;
      color: #245fd4;
    }
  }
  .action-right {
    display: flex;
    justify-content: center;
    align-items: center;
    border-right: 1px solid var(--el-border-color-darker);
    padding: 0 10px;
    &:hover {
      cursor: pointer;
      color: #245fd4;
    }
  }
}
</style>
