<!--  -->
<template>
  <div class="layouts">
    <Theme class="theme" v-model.theme="themeConfig" />
    <component :is="container(themeConfig.container)" :theme="themeConfig" />
  </div>
</template>

<script lang="ts" setup>
import Theme from "./theme/index.vue";
import { Normal, Left, Right, Top } from "./container";
import themeConfig from "./config/presets01";

function container(containerName: String) {
  switch (containerName) {
    case "Normal":
      return markRaw(Normal);
    case "Left":
      return markRaw(Left);
    case "Right":
      return markRaw(Right);
    case "Top":
      return markRaw(Top);
  }
}
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
