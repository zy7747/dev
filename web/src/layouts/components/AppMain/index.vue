<!-- AppMain -->
<template>
  <div class="app-main">
    <!-- <el-backtop style="z-index: 99999" target=".app-main"></el-backtop> -->
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
  height: 750px;
  border-radius: 15px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  overflow: auto;
}
</style>
