<!--  -->
<template>
  <div class="theme">
    <el-button type="primary" :icon="Tools" @click="drawer = true"> </el-button>

    <el-drawer v-model="drawer" title="主题" direction="rtl">
      <div>日夜模式</div>
      <Dark></Dark>
      <div>菜单位置</div>
      <Container v-model.container="theme.container"></Container>
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
import Container from "../theme/container.vue";
import { Tools } from "@element-plus/icons-vue";

const theme: any = defineModel();

const drawer = ref(false);
</script>

<style lang="scss" scoped></style>
