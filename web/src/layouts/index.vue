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
import systemTheme from "./config/systemTheme";
import videoTheme from "./config/videoTheme";

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
  setTimeout(() => {
    config.value = unref(systemTheme);
  }, 1500);
}

const Route = useRoute();

watch(
  Route,
  (value) => {
    if (value.fullPath === "/video/home") {
      config.value = unref(videoTheme);
    } else if (value.fullPath === "/system/dashboard") {
      config.value = unref(systemTheme);
    }
  },
  { immediate: true }
);

getConfig();
</script>

<style lang="scss" scoped>
.layouts {
  position: relative;
  .theme {
    top: 58px;
    right: 0;
    position: absolute;
    z-index: 1000;
  }
}
</style>
./config/videoTheme./config/videoTheme
