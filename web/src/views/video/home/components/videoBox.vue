<!--  -->
<template>
  <div class="videoBox" @click="videoView()">
    <img class="image" v-if="!loading" :src="fileUrl + videoItem.picture" />

    <div class="title boxLayout" :title="videoItem.title" v-if="!loading">
      {{ videoItem.title }}
    </div>

    <div class="author boxLayout" :title="videoItem.author" v-if="!loading">
      {{ "作者：" + (videoItem.author ? videoItem.author : "无") }}
    </div>

    <el-skeleton v-if="loading" style="width: 100%" animated>
      <template #template>
        <el-skeleton-item variant="image" class="image" />
        <div class="flex items-center flex-col">
          <el-skeleton-item variant="text" style="width: 95%; margin: 5px 0" />
          <el-skeleton-item variant="text" style="width: 95%" />
        </div>
      </template>
    </el-skeleton>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_URL;
const Router = useRouter();

const prop = defineProps({
  videoItem: {
    text: "视频数据",
    type: [Object] as any,
  },
});

const loading = ref<Boolean>(true);

function loadingChange() {
  setInterval(() => {
    loading.value = false;
  }, 1500);
}

function videoView() {
  Router.push({
    path: "/video/player/" + prop.videoItem.id,
    query: {
      id: prop.videoItem.id,
    },
  });
}

loadingChange();
</script>

<style lang="scss" scoped>
.videoBox {
  width: 100%;
  height: 100%;
  cursor: pointer;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
  &:hover {
    box-sizing: border-box;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border: 2px solid #3b8bca;
  }
}

.image {
  width: 100%;
  height: 150px;
}

.title {
  width: calc(100% - 4px);
  margin: 2px;
  font-size: 14px;
  height: 20px;
}
.author {
  width: calc(100% - 4px);
  margin: 2px;
  font-size: 14px;
  height: 20px;
}

.boxLayout {
  width: 100%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
