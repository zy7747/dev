<!--  -->
<template>
  <el-upload
    multiple
    v-model:file-list="fileList"
    :show-file-list="true"
    :action="fileUrl"
    :before-upload="handleBeforeUpload"
    :on-error="handleUploadError"
    :on-exceed="handleExceed"
    :on-success="handleUploadSuccess"
    :on-remove="handleDelete"
    :http-request="(params:any):any => httpRequest(params)"
    :limit="limit"
  >
    <c-button
      v-bind="$attrs"
      type="success"
      size="small"
      :icon="Upload"
      :text="$t('system.uploadFile')"
    />
    <template #tip>
      <div class="el-upload__tip">
        请上传
        <template v-if="fileSize">
          大小不超过
          <b style="color: #f56c6c">{{ fileSize }}MB</b>
        </template>
        <template v-if="fileType">
          格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b>
        </template>
        的文件
      </div>
    </template>
  </el-upload>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_URL;
const emit = defineEmits(["remove"]);
const loading = ref(false);
import { Upload } from "@element-plus/icons-vue";

const prop: any = defineProps({
  limit: {
    text: "上限",
    type: [Number] as any,
    default: 99999,
  },
  fileSize: {
    text: "上限",
    type: [Number] as any,
    default: 99999,
  },
  fileType: {
    text: "文件类型, 例如['png', 'jpg', 'jpeg']",
    type: [Array] as any,
    default: () => [
      "png",
      "jpg",
      "jpeg",
      "doc",
      "xls",
      "xlsx",
      "txt",
      "pdf",
      "mp4",
      "mp3",
      "gif",
    ],
  },
  api: {
    text: "接口",
    type: Function,
  },
  formData: {
    text: "表头数据",
    type: Object,
    default: () => {
      return {};
    },
  },
});

const value: any = defineModel();

const fileList = computed(() => {
  return unref(value).map((item: any) => {
    return { ...item, name: item.videoName, url: item.url };
  });
});

function handleBeforeUpload(file: any) {
  if (prop.fileType) {
    let fileExtension = "";

    if (file.name.lastIndexOf(".") > -1) {
      fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
    }

    const isTypeOk = prop.fileType.some((type: any) => {
      if (file.type.indexOf(type) > -1) return true;
      if (fileExtension && fileExtension.indexOf(type) > -1) return true;
      return false;
    });

    if (!isTypeOk) {
      ElMessage.error(
        `文件格式不正确, 请上传${prop.fileType.join("/")}格式文件!`
      );
      return false;
    }
  }

  if (prop.fileSize) {
    const isLt = file.size / 1024 / 1024 < prop.fileSize;
    if (!isLt) {
      ElMessage.error(`上传文件大小不能超过 ${prop.fileSize} MB!`);
      return false;
    }
  }
}
function handleExceed() {
  ElMessage.error(`上传文件数量不能超过 ${prop.limit} 个!`);
}
function handleDelete() {
  emit("remove");
}
function handleUploadSuccess({ filename, url, status }: any) {
  console.log(filename, url, status);
}
function handleUploadError() {
  ElMessage.error("上传失败，请重试");
}

function httpRequest(params: any) {
  loading.value = true;
  const formData = new FormData();

  formData.append("file", params.file);

  prop.api(formData).finally(() => {
    loading.value = false;
  });
}
</script>

<style lang="scss" scoped>
::v-deep(.el-upload-list) {
  max-height: 300px !important;
  overflow: auto !important;
  transition: none !important;
}
</style>
