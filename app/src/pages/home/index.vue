<!-- 首页 -->
<template>
  <div class="">
    <!--  -->
    <u-tabs :list="tabs" @click="tabsChange"></u-tabs>

    <div class="videoListBox">
      <!-- 轮播图 -->
      <u-swiper
        showTitle
        :autoplay="true"
        height="25vh"
        :list="videoList"
        keyName="image"
        circular
      />

      <div style="height: calc(75vh - 44px); overflow: auto">
        <VideoList :videoList="videoList"></VideoList>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import VideoList from "@/components/VideoList/index.vue";

const fileUrl = import.meta.env.VITE_APP_FILE_API;
const videoList = ref<any[]>(); //视频列表
const hotVideoList = ref<any>(); //所有热门视频
const tabs = ref<any[]>(); //动态栏位

//处理请求的视频数据
function getHotVideo() {}

function tabsChange(i: any) {
  videoList.value = hotVideoList.value[i.key].videoList.map((item: any) => {
    return { ...item, image: fileUrl + item.picture };
  });
}

onMounted(() => {
  getHotVideo();
});
</script>

<style lang="scss" scoped>
.videoListBox {
  height: calc(100vh - 44px);
  width: 100%;
  overflow: auto;
}
</style>
