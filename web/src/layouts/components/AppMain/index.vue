<!-- AppMain -->
<template>
  <div class="app-main font-mono">
    <el-backtop style="z-index: 3" target=".app-main" />
    <el-watermark
      :content="$t('system.watermark')"
      :font="font"
      :gap="[150, 150]"
    >
      <RouterView v-slot="{ Component, route }">
        <transition name="el-fade-in-linear">
          <keep-alive :include="include" :max="10">
            <component :is="Component" :key="route.fullPath" />
          </keep-alive>
        </transition>
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
  color: "rgba(0, 0, 0, 0.15)",
});
</script>

<style lang="scss" scoped>
.app-main {
  height: 100%;
  overflow: auto;
}
</style>
