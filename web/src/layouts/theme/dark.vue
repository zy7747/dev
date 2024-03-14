<template>
  <el-switch
    v-model="isDark"
    :title="isDark ? '夜间模式' : '明亮模式'"
    inline-prompt
    :active-icon="Moon"
    :inactive-icon="Sunny"
    style="--el-switch-off-color: #666"
  />
</template>

<script lang="ts" setup>
import { useDark } from "@vueuse/core";
import Moon from "@/icons/system/moon.svg";
import Sunny from "@/icons/system/sunny.svg";

const isDark = useDark();
</script>

<style lang="scss" scoped></style>
