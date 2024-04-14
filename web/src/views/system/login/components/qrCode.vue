<!--  -->
<template>
  <div class="flex justify-center items-center qrCode">
    <div class="qrCodeBox" @handleClick="refresh">
      <canvas ref="qrCodeCanvas" style="width: 200px; height: 200px"> </canvas>
    </div>
    <div class="prompt">
      {{ "打开微信扫一扫" }}
    </div>
  </div>
</template>

<script lang="ts" setup>
import { toCanvas } from "qrcode";
import dayjs from "dayjs";
const qrCodeCanvas = ref();

function createQrCode() {
  const nowDate = dayjs().format("YYYY-MM-DD HH:mm:ss");

  toCanvas(qrCodeCanvas.value, nowDate, {
    width: 200, //自定义宽度
    color: {
      dark: "#000", //自定义码的颜色
      light: "#f6f5f5", //自定义背景颜色
    },
  });
}

//刷新二维码
function refresh() {
  createQrCode();
}

onMounted(() => {
  createQrCode();
});
</script>

<style lang="scss" scoped>
.qrCode {
  flex-direction: column;
}

.qrCodeBox {
  cursor: pointer;
  width: 200px;
  height: 200px;
  border-radius: 15px;
  overflow: hidden;
  border: 1px solid rgb(196, 194, 194);
  margin-bottom: 5px;
}
</style>
