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
    </div>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;
const videoList = ref<any[]>(); //视频列表
const tabs = ref<any[]>(); //动态栏位
import { onShow } from "@dcloudio/uni-app";

function getTabs() {
  const category = [
    { label: "首页", value: "", params: "/videoIndex/home" },
  ].concat(Dict("video_category"));

  tabs.value = category.map((item: any) => {
    return { ...item, name: item.label };
  });

  tabsChange(tabs.value[0]);
}

function tabsChange(i: any) {
  Service.video.list
    .page({
      page: 1,
      size: 10,
      category: i.value,
    })
    .then((res: any) => {
      videoList.value = res.data.list.map((item: any) => {
        return {
          ...item,
          title: item.title,
          image: item.picture
            ? fileUrl + item.picture
            : fileUrl + "/资源管理器/视频封面/枕刀歌第一季.jpg",
          value: item.value,
        };
      });
    });
}

onShow(() => {
  setTimeout(() => {
    getTabs();
  }, 1000);
});
</script>

<style lang="scss" scoped>
.videoListBox {
  height: calc(100vh - 44px);
  width: 100%;
  overflow: auto;
}
</style>
