<!--  -->
<template>
  <div class="flex">
    <template v-for="item in tools">
      <template v-if="item.show !== false">
        <slot v-if="item.slot" :name="item.slot" :item="item" />

        <c-button
          v-else-if="item.operation === 'add'"
          v-bind="item"
          type="primary"
          size="small"
          :text="$t('system.add')"
          :icon="Plus"
          @click="item.click"
        />

        <c-button
          v-else-if="item.operation === 'remove'"
          v-bind="item"
          type="danger"
          size="small"
          :text="$t('system.delete in bulk')"
          :icon="Delete"
          :disabled="!checkboxData.length"
          @handleClick="item.click"
        />

        <template v-else-if="item.operation === 'import'">
          <el-upload
            ref="uploadRef"
            action=""
            :limit="1"
            :show-file-list="false"
            :before-upload="handleImport"
            :http-request="(params:any):any => httpRequest(params, item)"
          >
            <c-button
              :loading="loading"
              v-bind="item"
              class="btn"
              type="success"
              size="small"
              :text="$t('system.import')"
              :icon="Upload"
            />
          </el-upload>
        </template>

        <template v-else-if="item.operation === 'uploadFile'">
          <el-upload
            ref="uploadRef"
            action=""
            :show-file-list="false"
            :http-request="(params:any):any => httpRequest(params, item)"
          >
            <c-button
              :loading="loading"
              v-bind="item"
              class="btn"
              type="success"
              size="small"
              :text="$t('system.uploadFile')"
              :icon="Upload"
            />
          </el-upload>
        </template>

        <c-button
          v-else-if="item.operation === 'export'"
          v-bind="item"
          class="btn"
          color="#626aef"
          size="small"
          :text="$t('system.export')"
          :icon="DownloadFile"
          @handleClick="handleExport(item)"
        />

        <c-button
          v-else
          v-bind="item"
          size="small"
          :icon="item.icon ? item.icon : SwitchFilled"
          @handleClick="item.click"
          :text="item.text"
        />
      </template>
    </template>
  </div>
</template>

<script lang="ts" setup>
import {
  Plus,
  Delete,
  Download as DownloadFile,
  Upload,
  SwitchFilled,
} from "@element-plus/icons-vue";
import type { UploadInstance } from "element-plus";

const uploadRef: any = ref<UploadInstance>();
const loading = ref(false);

defineProps({
  tools: {
    text: "工具栏",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
  checkboxData: {
    text: "多选",
    type: [Array],
    default: () => {
      return [];
    },
  },
});

//导入
function handleImport(rawFile: any) {
  if (
    rawFile.type !== "application/vnd.ms-excel" &&
    rawFile.type !==
      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
  ) {
    ElMessage.error("必须上传excel表格");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("表格大小不能大于 2MB!");
    return false;
  }

  return true;
}

function httpRequest(params: any, item: any) {
  loading.value = true;
  const formData = new FormData();

  formData.append("file", params.file);

  item.api(formData).finally(() => {
    unref(uploadRef)[0].clearFiles();
    loading.value = false;
  });
}

//导出
function handleExport(item: any) {
  return item
    .api()
    .then((res: any) => {
      const fileName = item.fileName || "导出文件";
      Download.excel(res, fileName);

      exportSuccess(res);
    })
    .catch(() => {
      ElMessage({
        message: "导出失败",
        type: "error",
      });
    });
}
</script>

<style lang="scss" scoped></style>
