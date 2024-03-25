<!--  -->
<template>
  <div class="layouts">
    <Theme ref="theme" :style="style" class="theme" v-model.theme="config" />
    <component :is="container(config.container)" :theme="config" />
  </div>
</template>

<script lang="ts" setup>
import Theme from "./theme/index.vue";
import { Normal, Left, Right, Top } from "./container";
import systemTheme from "./config/systemTheme";

import { useDraggable } from "@vueuse/core";

const theme = ref<HTMLElement | null>(null);

const { style } = useDraggable(theme, {
  initialValue: { x: 0, y: 0 },
});

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

const config = ref({ container: "" });

function getConfig() {
  config.value = unref(systemTheme);
}

getConfig();
</script>

<style lang="scss">
.layouts {
  width: 100vw;
  height: 100vh;
  .theme {
    position: absolute;
    top: 50% !important;
    right: 0 !important;
    z-index: 99;
  }
}
</style>
