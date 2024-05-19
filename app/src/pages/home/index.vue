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
import { ref, onMounted } from "vue";
import VideoList from "@/components/VideoList/index.vue";
import { hotVideo } from "@/apis/video";
import { nextTick } from "vue";

const fileUrl = import.meta.env.VITE_APP_FILE_API;
const videoList = ref<any[]>(); //视频列表
const hotVideoList = ref<any>(); //所有热门视频
const tabs = ref<any[]>(); //动态栏位

//处理请求的视频数据
function getHotVideo() {
  hotVideo().then((res: any) => {
    hotVideoList.value = res.data;

    //tabs栏位
    let tab = [];

    for (const key in res.data) {
      tab.push({ ...res.data[key], name: res.data[key].title, key: key });
    }

    tabs.value = tab;

    nextTick(() => {
      tabsChange({ key: "all" });
    });
  });
}

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
