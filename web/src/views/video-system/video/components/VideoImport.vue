<!--  -->
<template>
  <Collapse title="视频列表">
    <template #content>
      <c-form ref="formRef" :model="pageData" :formConfig="formConfig" />
    </template>
  </Collapse>

  <Collapse title="视频列表">
    <template #content>
      <VideoUpload :api="api" v-model="videoList" />
    </template>
  </Collapse>
</template>

<script lang="ts" setup>
import VideoUpload from "../components/VideoUploads.vue";

const { dict } = useDict({
  filesTree: DictService("filesTree"),
});

const pageData: any = reactive({});
const videoList: any = ref([]);

const formConfig = computed(() => {
  return {
    formParams: [
      {
        label: $t("video.savePath", "视频存储节点"),
        prop: "savePath",
        type: "treeSelect",
        span: 24,
        params: {
          "check-strictly": true,
        },
        options: () => dict.filesTree,
      },
      {
        label: $t("video.category", "视频分类"),
        prop: "category",
        type: "select",
        span: 8,
        options: Dict("video_category"),
      },
      {
        label: $t("video.type", "视频类型"),
        prop: "type",
        type: "select",
        span: 8,
        options: Dict("video_type"),
      },
      {
        label: $t("video.region", "视频地区"),
        prop: "region",
        type: "select",
        span: 8,
        options: Dict("video_area"),
      },
    ],
  };
});

function api(files: any, fileName: any) {
  return Service.file
    .save({
      parentId: pageData.savePath,
      fileName: fileName,
      fileType: "folder",
    })
    .then((fileRes: any) => {
      files.append("parentId", fileRes.data.id);
      Service.file.uploadFile(files).then((res: any) => {
        return Service.video.list
          .save({
            ...pageData,
            videoName: fileName,
            url: res.data.url,
            isCollection: 0,
            status: "success",
            episode: 1,
          })
          .then(() => {
            ElMessage({
              message: "上传成功",
              type: "success",
            });
          });
      });
    });
}
</script>

<style lang="scss" scoped></style>
