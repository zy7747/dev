<!--  -->
<template>
  <div class="">
    <ul class="videoList">
      <li class="video" v-for="(item, index) in videoList" :key="index">
        <VideoBox :videoInfo="item"></VideoBox>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import VideoBox from "../VideoBox/index.vue";
defineProps({
  videoList: {
    text: "视频列表",
    type: [Array as any],
    default: () => {
      return [];
    },
  },
});
</script>

<style lang="scss" scoped>
.videoList {
  height: 100%;
  display: grid;
  grid-template-columns: 49% 49%;
}
</style>
