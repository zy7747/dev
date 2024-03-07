<!--  -->
<template>
  <div class="layouts">
    <Theme class="theme" v-model.theme="themeConfig" />
    <component :is="themeConfig.container" :theme="themeConfig" />
  </div>
</template>

<script lang="ts" setup>
import Theme from "./theme/index.vue";
import themeConfig from "./config/presets01";
</script>

<style lang="scss" scoped>
.layouts {
  position: relative;
  .theme {
    top: 60px;
    right: 0;
    position: absolute;
    z-index: 1000;
  }
}
</style>
