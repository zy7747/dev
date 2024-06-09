<!--  -->
<template>
  <el-drawer
    size="350px"
    v-model="themeStore.drawer"
    title="主题"
    direction="rtl"
  >
    <div>日夜模式</div>
    <el-switch
      v-model="isDark"
      :title="isDark ? '夜间模式' : '明亮模式'"
      inline-prompt
      :active-icon="Moon"
      :inactive-icon="Sunny"
    />
    <div>菜单位置</div>
    <el-radio-group v-model="theme.container">
      <el-radio-button v-for="item in options" :label="item.value">
        {{ item.label }}
      </el-radio-button>
    </el-radio-group>
    <div>Header背景颜色</div>
    <el-color-picker v-model="theme.headerBackgroundColor" />
    <div>Aside背景颜色</div>
    <el-color-picker v-model="theme.asideBackgroundColor" />
    <div>面包屑</div>
    <el-switch
      v-model="theme.breadcrumb"
      inline-prompt
      active-text="开"
      inactive-text="关"
    />
    <div>切换打开的菜单</div>
    <el-switch
      v-model="theme.tabs"
      inline-prompt
      active-text="开"
      inactive-text="关"
    />
    <div>菜单伸缩开关</div>
    <el-switch
      v-model="theme.collapse"
      inline-prompt
      active-text="开"
      inactive-text="关"
    />
    <div>菜单栏宽度</div>
    <el-slider :min="150" :max="300" v-model="theme.asideWidth" />
    <div>头部导航高度</div>
    <el-slider :min="60" :max="120" v-model="theme.headerHeight" />

    <c-button type="primary" text="保存" @click="save" />
    <c-button type="primary" text="重置" @click="reset" />
  </el-drawer>
</template>

<script lang="ts" setup>
import { useDark } from "@vueuse/core";
import { useThemeStore } from "@/store/theme";
import systemTheme from "../config/systemTheme";
import Moon from "@/icons/system/moon.svg";
import Sunny from "@/icons/system/sunny.svg";

const isDark = useDark();
const theme: any = defineModel();
const themeStore = useThemeStore();

const options = ref([
  { label: "左菜单", value: "Left" },
  { label: "上菜单", value: "Top" },
  { label: "右菜单", value: "Right" },
  { label: "常规", value: "Normal" },
]);

function save() {
  themeStore.save(theme);
}

function reset() {
  localStorage.setItem("theme", JSON.stringify(systemTheme.value));
  theme.value = JSON.parse(JSON.stringify(unref(systemTheme)));
}
</script>
