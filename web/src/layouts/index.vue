<!-- layouts -->
<template>
  <Theme v-model="config" />
  <component :is="container(config.container)" :theme="config" />
</template>

<script lang="ts" setup>
import { Normal, Left, Right, Top } from "./container";
import applyTheme from "./config/applyTheme";
import systemTheme from "./config/systemTheme";
import videoTheme from "./config/videoTheme";
import Theme from "./theme/index.vue";
const config = ref();

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

function getConfig() {
  const layout = localStorage.getItem("theme");
  if (layout) {
    config.value = JSON.parse(layout);
  } else {
    config.value = JSON.parse(JSON.stringify(unref(videoTheme)));
  }
}

getConfig();
</script>
