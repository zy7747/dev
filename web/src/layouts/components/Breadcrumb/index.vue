<!--  -->
<template>
  <div class="flex items-center">
    <el-page-header @back="onBack">
      <template #content>
        <div class="flex items-center">
          <el-breadcrumb :separator-icon="ArrowRight">
            <el-breadcrumb-item :to="{ path: '/home' }">
              首页
            </el-breadcrumb-item>
            <el-breadcrumb-item>菜单1</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </template>
    </el-page-header>
  </div>
</template>

<script lang="ts" setup>
import { ArrowRight } from "@element-plus/icons-vue";
const onBack = () => {};
</script>
