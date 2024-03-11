<!--  -->
<template>
  <div class="layouts">
    <Theme class="theme" v-model.theme="config" />
    <component :is="container(config.container)" :theme="config" />
  </div>
</template>

<script lang="ts" setup>
import Theme from "./theme/index.vue";
import { Normal, Left, Right, Top } from "./container";
import presets02 from "./config/presets02";

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

const config = ref();

function getConfig() {
  setTimeout(() => {
    config.value = presets02.value;
  }, 1500);
}

getConfig();
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
