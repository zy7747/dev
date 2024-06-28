<!--  -->
<template>
  <div class="flex">
    <el-image
      style="width: 200px; height: 100px"
      :src="fileUrl + videoInfo.picture"
      :zoom-rate="1.2"
      :max-scale="7"
      :min-scale="0.2"
      :initial-index="4"
      :preview-src-list="[fileUrl + videoInfo.picture]"
      fit="cover"
    />

    <div class="videoInfo">
      <div class="title">{{ videoInfo.title }}</div>

      <ul class="flex info">
        <li>
          浏览次数:
          {{ videoInfo.playNum ? videoInfo.playNum : "0" }}
        </li>
        <li>
          获赞次数:
          {{ videoInfo.collectionNum ? videoInfo.collectionNum : "0" }}
        </li>
        <li>
          点赞量:
          {{ videoInfo.starsNum ? videoInfo.starsNum : "0" }}
        </li>
        <li>
          更新时间:
          {{
            videoInfo.updateTime ? videoInfo.updateTime : videoInfo.createTime
          }}
        </li>
      </ul>
    </div>
  </div>

  <div class="flex">
    <div class="videoPlayer">
      <VideoPlayer ref="videoPlayer" />
    </div>
    <div class="episode">
      <Episode @changeVideo="changeVideo" :episodeList="videoInfo.videoList" />
    </div>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;
const Route = useRoute();
const videoInfo: any = ref({});
const videoList = ref<any[]>([]);
const videoPlayer: any = ref();

function load() {
  Service.video.list.detail({ id: Route.query.id }).then((res: any) => {
    videoInfo.value = res.data;
    videoList.value = res.data.videoList;

    nextTick(() => {
      videoPlayer.value.createVideo(videoList.value);
    });
  });
}

//修改或者播放视频
const changeVideo = (src: string) => {
  //修改src
  videoPlayer.value.setVideo(src);
};

load();
</script>

<style lang="scss" scoped>
.title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 10px;
}

.videoInfo {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 15px;
}
.info {
  color: #8d9094;
  li {
    margin-right: 15px;
  }
}

.videoPlayer {
  width: 80%;
  height: 40rem;
}

.episode {
  width: 20%;
  height: 40rem;
}
</style>
