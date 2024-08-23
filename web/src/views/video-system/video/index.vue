<!-- 视频 video -->
<template>
  <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
    <template #dialog0>
      <Collapse :isShow="false" title="视频列表" v-if="pageData.editData.id">
        <template #content>
          <VideoUpload :api="api" v-model="pageData.editData.videoList" />
        </template>
      </Collapse>
    </template>
  </c-page>
</template>
<script lang="ts" setup>
import VideoUpload from "./components/VideoUploads.vue";
import VideoImport from "./components/VideoImport.vue";
const fileUrl = import.meta.env.VITE_APP_FILE_URL;

defineOptions({
  name: "Video",
});
const { dict } = useDict({
  filesTree: DictService("filesTree"),
});

const pageData: any = reactive({
  queryData: {},
  editData: {},
});

function api(files: any) {
  const editData = unref(pageData).editData;

  if (pageData.editData.isCollection) {
    files.append("parentId", pageData.editData.savePath);

    return Service.system.file.uploadFile(files).then((res: any) => {
      const data = {
        parentId: editData.id,
        videoName: res.data.fileName,
        url: res.data.url,
        episode: editData.length + 1,
        isCollection: 0,
        status: "success",
      };
      pageData.editData.videoList.push(data);

      return Service.video.list.save(data).then((res: any) => {
        uploadSuccess(res);
      });
    });
  } else {
    return Service.system.file
      .save({
        parentId: pageData.editData.savePath,
        fileName: pageData.editData.videoName,
        fileType: "folder",
      })
      .then((fileRes: any) => {
        files.append("parentId", fileRes.data.id);

        Service.system.file.uploadFile(files).then((res: any) => {
          return Service.video.list
            .save({
              ...pageData.editData,
              url: res.data.url,
            })
            .then((res: any) => {
              uploadSuccess(res);
            });
        });
      });
  }
}

const { pageOption, pageRef, ids } = usePage({
  createLoad: true,
  title: $t("video.video", "视频"),
  formConfig: {
    formParams: [
      {
        label: $t("video.videoName", "视频名称"),
        prop: "videoName",
        type: "input",
      },
      {
        label: $t("video.category", "视频分类"),
        prop: "category",
        type: "select",
        options: Dict("video_category"),
      },
      {
        label: $t("video.type", "视频类型"),
        prop: "type",
        type: "select",
        options: Dict("video_type"),
      },
      {
        label: $t("video.region", "视频地区"),
        prop: "region",
        type: "select",
        options: Dict("video_area"),
      },
      {
        label: $t("video.label", "标签"),
        prop: "label",
        type: "input",
      },
      {
        label: $t("video.author", "作者"),
        prop: "author",
        type: "input",
      },
      {
        label: $t("video.createYear", "创作年份"),
        prop: "createYear",
        type: "date",
      },
    ],
  },
  tableConfig: [
    {
      title: $t("video.video", "视频"),
      tools: [
        {
          operation: "add",
          permission: ["video:add"],
          click() {
            unref(pageRef).handleOpen({ type: "add", data: {} });
          },
        },

        {
          operation: "remove",
          permission: ["video:remove"],
          click() {
            return Service.video.list.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
        {
          text: "批量导入",
          permission: ["video:import"],
          click() {
            showModal(VideoImport, {
              title: "批量新增视频",
              width: 1000,
              showFooter: false,
            });
          },
        },
        {
          operation: "import",
          permission: ["video:import"],
          api(files: any) {
            return Service.video.list.imports(files).then((res: any) => {
              importSuccess(res, pageRef);
            });
          },
        },
        {
          operation: "export",
          permission: ["video:export"],
          api() {
            return Service.video.list
              .exports(pageData.queryData)
              .then((res: any) => {
                exportSuccess(res);
              });
          },
          fileName: $t("video.video", "视频"),
        },
      ],
      tableColumn: [
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("video.picture", "视频图片"),
          field: "picture",
          cType: "img",
          width: 100,
        },
        {
          title: $t("video.videoName", "视频名称"),
          field: "videoName",
          isFilters: true,
          cType: "link",
          on: {
            click({ row }: any) {
              window.open(fileUrl + row.url);
              // showModal(VideoPlay, {
              //   config: {
              //     width: 1500,
              //   },
              //   data: row,
              // });
            },
          },
        },
        {
          title: $t("video.category", "视频分类"),
          field: "category",
          isFilters: true,
          width: 120,
          translate: "video_category",
        },
        {
          title: $t("video.type", "视频类型"),
          field: "type",
          isFilters: true,
          width: 120,
          translate: "video_type",
        },
        {
          title: $t("video.region", "视频地区"),
          field: "region",
          isFilters: true,
          translate: "video_area",
        },
        {
          title: $t("video.playNum", "播放量"),
          field: "playNum",
          sortable: true,
          width: 100,
        },
        {
          title: $t("video.createYear", "创作年份"),
          field: "createYear",
          sortable: true,
        },
        {
          title: $t("video.duration", "时长"),
          field: "duration",
          sortable: true,
        },
        {
          title: $t("video.author", "作者"),
          field: "author",
          isFilters: true,
        },
        {
          title: $t("video.label", "标签"),
          field: "label",
        },
        {
          title: $t("video.season", "季"),
          field: "season",
        },
        {
          title: $t("video.episode", "集"),
          field: "episode",
        },
        {
          title: $t("video.isCollection", "是否是集合"),
          field: "isCollection",
          translate: "isNo",
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["video:edit"],
          click({ row }: any) {
            return Service.video.list
              .detail({ id: row.id })
              .then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "edit",
                  data: res.data,
                });
              });
          },
        },
        {
          operation: "detail",
          click({ row }: any) {
            return Service.video.list
              .detail({ id: row.id })
              .then((res: any) => {
                unref(pageRef).handleOpen({
                  type: "detail",
                  data: res.data,
                });
              });
          },
        },
        {
          operation: "remove",
          permission: ["video:remove"],
          click({ row }: any) {
            return Service.video.list.remove([row.id]).then((res: any) => {
              removeSuccess(res, pageRef);
            });
          },
        },
      ],
      editConfig: {
        width: "1000px",
        formConfig: {
          isShow: true,
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
              label: $t("video.picture", "视频图片"),
              prop: "picture",
              type: "input",
            },
            {
              label: $t("video.videoName", "视频名称"),
              prop: "videoName",
              type: "input",
            },
            {
              label: $t("video.title", "视频标题"),
              prop: "title",
              type: "input",
            },
            {
              label: $t("video.category", "视频分类"),
              prop: "category",
              type: "select",
              options: Dict("video_category"),
            },
            {
              label: $t("video.type", "视频类型"),
              prop: "type",
              type: "select",
              options: Dict("video_type"),
            },
            {
              label: $t("video.region", "视频地区"),
              prop: "region",
              type: "select",
              options: Dict("video_area"),
            },
            {
              label: $t("video.author", "作者"),
              prop: "author",
              type: "input",
            },
            {
              label: $t("video.profile", "视频简介"),
              prop: "profile",
              type: "input",
              params: {
                type: "textarea",
                "show-word-limit": true,
                maxlength: 500,
                autosize: { minRows: 8, maxRows: 16 },
              },
              span: 24,
            },

            {
              label: $t("video.season", "季"),
              prop: "season",
              type: "input",
            },
            {
              label: $t("video.episode", "集"),
              prop: "episode",
              type: "input",
            },
            {
              label: $t("video.isCollection", "是否是集合"),
              prop: "isCollection",
              type: "select",
              options: Dict("isNo"),
            },
            {
              label: $t("video.createYear", "创作年份"),
              prop: "createYear",
              type: "date",
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.video.list
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      query: (pages: any) =>
        Service.video.list.page({ ...pages, ...pageData.queryData }),
    },
  ],
});
</script>
<style lang="scss" scoped></style>
