<!--  -->
<template>
  <div class="">
    <el-carousel
      indicator-position="none"
      ref="carousel"
      :interval="3000"
      type="card"
    >
      <el-carousel-item v-for="(item, index) in imageList" :key="index">
        <div class="background" text="2xl" justify="center">
          <img :src="fileUrl + item.picture" alt="" style="width: 100%" />
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script lang="ts" setup>
const fileUrl = import.meta.env.VITE_APP_FILE_API;

defineProps({
  imageList: {
    text: "图片列表",
    type: [Array as any],
    default: () => {
      return [];
    },
  },
});
</script>

<style lang="scss" scoped></style>
