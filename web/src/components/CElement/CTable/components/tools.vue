<!--  -->
<template>
  <div class="flex">
    <template v-for="item in tools">
      <template v-if="item.show !== false">
        <c-button
          v-if="item.type === 'add'"
          v-bind="item"
          type="primary"
          size="small"
          text="新增"
          :icon="Plus"
          @click="item.click"
        />

        <c-button
          v-else-if="item.type === 'remove'"
          v-bind="item"
          type="danger"
          size="small"
          text="批量删除"
          :icon="Delete"
          @click="item.click"
        />

        <template v-else-if="item.type === 'import'">
          <el-upload
            ref="uploadRef"
            action=""
            :limit="1"
            :show-file-list="false"
            :before-upload="handleImport"
            :http-request="(params):any => httpRequest(params, item)"
          >
            <c-button
              v-bind="item"
              class="btn"
              type="success"
              size="small"
              text="导入"
              :icon="Upload"
            />
          </el-upload>
        </template>

        <c-button
          v-else-if="item.type === 'export'"
          v-bind="item"
          class="btn"
          color="#626aef"
          size="small"
          text="导出"
          :icon="DownloadFile"
          @click="handleExport(item)"
        />

        <el-button
          v-else
          v-bind="item"
          type="info"
          size="small"
          :icon="SwitchFilled"
          @click="item.click"
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

defineProps({
  tools: {
    text: "工具栏",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});

//导入
function handleImport(rawFile: any) {
  if (rawFile.type !== "application/vnd.ms-excel") {
    ElMessage.error("必须上传excel表格");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("表格大小不能大于 2MB!");
    return false;
  }

  return true;
}

function httpRequest(params: any, item: any) {
  const formData = new FormData();

  formData.append("file", params.file);

  item.api(formData).then((res: any) => {
    if (res.code === 200) {
      ElMessage({
        message: "导入成功",
        type: "success",
      });
    }
  });

  unref(uploadRef)[0].clearFiles();
}

//导出
function handleExport(item: any) {
  item
    .api()
    .then((res: any) => {
      const fileName = item.fileName || "导出文件";
      Download.excel(res, fileName);

      ElMessage({
        message: "导出成功",
        type: "success",
      });
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
