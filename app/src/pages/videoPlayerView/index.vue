<!--  -->
<template>
  <div class="">
    <!-- APP用上面这个 -->
    <view v-html="videoHtm(src)"></view>

    <Episode @changeVideo="changeVideo" :episodeList="videoList"></Episode>
    <u-divider text="分割线" :dashed="true"></u-divider>

    <uni-card :title="videoInfo.videoName" extra="简介">
      <text>
        {{ videoInfo.profile }}
      </text>
    </uni-card>
  </div>
</template>

<script lang="ts" setup>
import { onLoad } from "@dcloudio/uni-app";
import Episode from "@/components/Episode/index.vue";

const baseUrl = import.meta.env.VITE_APP_BASE_API;

const videoInfo = ref<any>({});
const videoList = ref<any[]>([]);
const id = ref("");
const src = ref<string>("");

onLoad((options: any) => {
  id.value = options.id;

  //初始化渲染
  Service.videoDetail({ id: id.value }).then((res: any) => {
    const src = res.data.videoList[0].url;
    videoList.value = res.data.videoList;
    videoInfo.value = res.data;
    //播放视频
    changeVideo(src);
  });
});

function videoHtm(src: any) {
  return `<video controls="controls" controlslist="nodownload" width="100%" height="200px"><source src="${src}" type="video/mp4"></video>`;
}

//修改或者播放视频
const changeVideo = (url: string) => {
  //修改src
  src.value = baseUrl + url;
  //记录播放量
  Service.playNum({ id: id.value });
};
</script>

<style lang="scss" scoped></style>
