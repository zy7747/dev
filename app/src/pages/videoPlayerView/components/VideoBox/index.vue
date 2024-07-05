<!-- 视频盒子 -->
<template>
  <div @click="videoView">
    <div class="videoBox" v-if="show">
      <image
        style="width: 100%; height: 70%"
        mode="scaleToFill"
        referrerPolicy="no-referrer"
        :src="fileUrl + videoInfo.picture"
      >
      </image>
      <div style="width: 100%; height: 30%" class="videoInfo">
        <div class="title">
          {{ videoInfo.title }}
        </div>
        <div class="author">
          {{ "作者：" + videoInfo.author }}
        </div>
      </div>
    </div>

    <div class="videoBox" v-else>
      <u-skeleton rows="1" loading></u-skeleton>
    </div>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_BASE_API;

const show = computed(() => {
  return JSON.stringify(props.videoInfo) !== "{}";
});

const props = defineProps({
  videoInfo: {
    text: "视频信息",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

function videoView() {
  uni.navigateTo({
    url: "/pages/videoPlayerView/index?id=" + props.videoInfo.id,
  });
}
</script>

<style lang="scss" scoped>
.videoBox {
  margin: 1vw;
  height: 30vh;
  border-radius: 3px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  .videoInfo {
    padding: 5px;
  }
  .title {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    font-size: 14px;
    margin-bottom: 5px;
  }

  .author {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #61666d;
    font-size: 12px;
  }
}
</style>
