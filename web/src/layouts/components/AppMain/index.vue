<!-- AppMain -->
<template>
  <div>
    <!-- <el-backtop style="z-index: 10" target=".app-main" /> -->
    <el-watermark content="" :font="font" :gap="[150, 150]">
      <RouterView v-slot="{ Component, route }">
        <keep-alive :include="include" :max="10">
          <component :is="Component" :key="route.fullPath" />
        </keep-alive>
      </RouterView>
    </el-watermark>
  </div>
</template>

<script lang="ts" setup>
import { useTabStore } from "@/store/tabs";
const useTab = useTabStore();

const include = computed(() => {
  return useTab.keepViews;
});

const font = ref({
  fontWeight: 10,
  fontSize: 16,
});
</script>

<style lang="scss" scoped></style>
