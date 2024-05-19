<!-- 短视频 -->
<template>
  <div class="shortVideo">
    <swiper
      style="height: 100vh"
      :indicator-dots="false"
      :autoplay="false"
      :interval="3000"
      :duration="1000"
      :vertical="true"
      :current="current"
      @change="changeVideo"
    >
      <swiper-item v-for="(item, index) in videoList" :key="index">
        <view class="swiper-item">
          <view
            v-if="index >= current - 1 && index <= current + 1"
            v-html="videoHtm(item.url)"
          />
        </view>
      </swiper-item>
    </swiper>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { videoPage } from "@/apis/video";
const fileUrl = import.meta.env.VITE_APP_FILE_API;

const queryParams = ref<any>({
  page: 1,
  size: 20,
  type: "shorts",
});

const videoList = ref<any[]>();
const current = ref<number>(0);

function getVideoPage() {
  videoPage(queryParams.value).then((res: any) => {
    videoList.value = res.data.list.map((item: any) => {
      return { ...item, url: fileUrl + item.url, title: item.videoName };
    });
  });
}

function videoHtm(src: any) {
  return `<video controls="controls" class="shortVideo" style="object-fit: cover;" width="100%" height="500px"><source src="${src}" type="video/mp4"></video>`;
}

function changeVideo({ detail }: any) {
  current.value = detail.current;
}

getVideoPage();
</script>

<style lang="scss" scoped>
.shortVideo {
  object-fit: cover;
}
</style>
