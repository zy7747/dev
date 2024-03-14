<!-- AppMain -->
<template>
  <div class="app-main font-mono">
    <!-- <el-backtop style="z-index: 99999" target=".app-main" /> -->
    <el-watermark
      :content="$t('app.watermark', '水印')"
      :font="font"
      :gap="[150, 150]"
    >
      <RouterView />
    </el-watermark>
  </div>
</template>

<script lang="ts" setup>
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
