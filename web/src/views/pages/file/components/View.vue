<!--  -->
<template>
  <div class="">
    <vxe-list height="550" v-bind="$attrs" :scroll-y="{ enabled: true }">
      <template #default="{ items }">
        <div
          @click="openFile(item)"
          @contextmenu.prevent.stop="openFile(item)"
          v-for="(item, index) in items"
          :key="index"
          class="box"
        >
          <el-image
            style="width: 100px; height: 100px; margin-right: 30px"
            :src="fileIcon(item)"
          />
          <span>{{ item.fileName }}</span>
        </div>
      </template>
    </vxe-list>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_URL;

function fileIcon(item: any) {
  const url =
    "https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/file/";

  const obj: any = {
    excel: url + "excel.png",
    pdf: url + "pdf.png",
    folder: url + "folder.png",
    word: url + "word.png",
    txt: url + "txt.png",
    mp3: url + "mp3.png",
    mp4: url + "mp4.png",
    file: url + "file.png",
  };

  if (item.fileType === "folder") {
    return obj["folder"];
  } else if (
    item.fileType === "jpg" ||
    item.fileType === "jpeg" ||
    item.fileType === "png" ||
    item.fileType === "gif"
  ) {
    return fileUrl + item.url;
  } else if (item.fileType === "pdf") {
    return obj["pdf"];
  } else if (item.fileType === "xls" || item.fileType === "xlsx") {
    return obj["excel"];
  } else if (item.fileType === "docx") {
    return obj["word"];
  } else if (item.fileType === "txt") {
    return obj["txt"];
  } else if (item.fileType === "mp3") {
    return obj["mp3"];
  } else if (item.fileType === "mp4") {
    console.log(obj["mp4"]);

    return obj["mp4"];
  } else {
    return obj["file"];
  }
}

function openFile(item: any) {
  if (item.fileType === "folder") {
  } else if (
    item.fileType === "jpg" ||
    item.fileType === "jpeg" ||
    item.fileType === "png" ||
    item.fileType === "gif"
  ) {
    window.open(fileUrl + item.url);
  } else if (item.fileType === "pdf") {
  } else if (item.fileType === "xls" || item.fileType === "xlsx") {
  } else if (item.fileType === "docx") {
  } else if (item.fileType === "txt") {
  } else if (item.fileType === "mp3") {
  } else if (item.fileType === "mp4") {
    window.open(fileUrl + item.url);
  } else {
  }
}
</script>

<style lang="scss" scoped>
.box {
  width: 100%;
  display: flex;
  align-items: center;
  margin: 5px 0;
  padding: 5px 10px;
  &:hover {
    opacity: 0.8;
    cursor: pointer;
    background-color: #e6f1ff;
    border: 1px solid #cddef5;
  }
}
</style>
