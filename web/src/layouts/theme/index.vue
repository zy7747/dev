<!--  -->
<template>
  <div class="theme">
    <el-button type="primary" :icon="Tools" @click="drawer = true"> </el-button>

    <el-drawer v-model="drawer" title="主题" direction="rtl">
      <div>日夜模式</div>
      <Dark></Dark>
      <div>菜单位置</div>
      <el-radio-group v-model="theme.container">
        <el-radio-button v-for="item in options" :label="item.value" />
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
        v-model="theme.tagsView"
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
    </el-drawer>
  </div>
</template>

<script lang="ts" setup>
import Dark from "../theme/dark.vue";
import { Tools } from "@element-plus/icons-vue";
const theme: any = defineModel();

const drawer = ref(false);

const options = ref([
  { label: "左菜单", value: "Left" },
  { label: "上菜单", value: "Top" },
  { label: "右菜单", value: "Right" },
  { label: "常规", value: "Normal" },
]);
</script>

<style lang="scss" scoped></style>
