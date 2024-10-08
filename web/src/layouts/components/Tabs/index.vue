<!--  -->
<template>
  <div class="flex tabs">
    <!-- 左 -->
    <div class="action right-line" @click="leftSlide">
      <el-icon><ArrowLeftBold /></el-icon>
    </div>
    <!-- 菜单 -->
    <div class="tags" ref="tags">
      <router-link
        v-for="(tag, index) in visitedViews"
        :key="tag.path"
        :class="isActive(tag) ? 'active link' : 'link'"
        :to="{ path: tag.path, query: tag.query }"
        @mouseenter="handleMouseEnter(tag)"
        @mouseleave="handleMouseLeave"
      >
        <div class="tag">
          <div class="title">{{ tag.title }}</div>

          <el-icon
            v-show="isHover(tag) || isActive(tag)"
            class="el-icon-close"
            @click.prevent.native="removeTab(index)"
          >
            <CircleCloseFilled />
          </el-icon>
        </div>
      </router-link>
    </div>
    <!-- 右 -->
    <div class="action left-line" @click="rightSlide">
      <el-icon><ArrowRightBold /></el-icon>
    </div>
    <!-- 右侧菜单 -->
    <ul class="flex">
      <li class="action left-line" @click="themeOpen">
        <el-icon class="rotate180"><Tools /></el-icon>
      </li>

      <li class="action left-line">
        <CDropdown :options="options" @command="command">
          <template #title>
            <div class="rotate180">
              <i class="vxe-icon-custom-column"></i>
            </div>
          </template>
        </CDropdown>
      </li>

      <li class="action left-line" @click="refresh">
        <el-icon class="rotate180"><Refresh /></el-icon>
      </li>

      <li class="action left-line" @click="toggle">
        <el-icon v-if="!isFullscreen"><FullScreen /></el-icon>
        <i v-else class="vxe-icon-zoom-in"></i>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import {
  ArrowLeftBold,
  ArrowRightBold,
  Tools,
  FullScreen,
  Refresh,
  CircleCloseFilled,
} from "@element-plus/icons-vue";
import { useTabStore } from "@/store/tabs";
import { useThemeStore } from "@/store/theme";
import { useFullscreen } from "@vueuse/core";

const themeStore = useThemeStore();

const Route = useRoute();
const Router = useRouter();
const useTab = useTabStore();
const visitedViews = useTab.visitedViews;
const tags = ref();
const hoverRoute = ref();

// 是否选中标签
function isActive(route: any) {
  return route.path === Route.path;
}
//是否移入标签
function isHover(route: any) {
  return route.path === hoverRoute.value;
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
//鼠标移入移出
function handleMouseEnter(route: any) {
  hoverRoute.value = route.path;
}
function handleMouseLeave() {
  hoverRoute.value = null;
}

function command(row: any) {
  if (row.value === "closeOther") {
    useTab.removeOther(Route);
  } else if (row.value === "closeLeft") {
    useTab.removeLeft(Route);
  } else if (row.value === "closeRight") {
    useTab.removeRight(Route);
  }
}

const options = ref([
  { icon: "关闭页面", label: "关闭其他页面", value: "closeOther" },
  { icon: "关闭左侧页面", label: "关闭左侧页面", value: "closeLeft" },
  { icon: "关闭右侧页面", label: "关闭右侧页面", value: "closeRight" },
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
const { isFullscreen, toggle } = useFullscreen(document.body);

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
.tabs {
  width: 100%;
  border-bottom: 1px solid var(--el-border-color-darker);
  background-color: var(--el-fill-color-light);
  .tags {
    display: flex;
    align-items: center;
    width: calc(100% - 25px * 5);
    overflow: auto;
    scrollbar-width: none; /* Firefox */
    .link {
      height: 25px;
      padding: 0 5px;
      .tag {
        display: flex;
        justify-content: center;
        align-items: center;
        min-width: 120px;
        .title {
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
      &:hover {
        color: #fff;
        background: #303b75;
      }
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

  .action {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 10px;
    &:hover {
      cursor: pointer;
      color: #245fd4;
    }
  }

  .right-line {
    border-right: 1px solid var(--el-border-color-darker);
  }
  .left-line {
    border-left: 1px solid var(--el-border-color-darker);
  }
}
</style>
