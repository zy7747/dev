<template>
  <div>
    <!-- {{ $t("app.video") }} -->
    <up-avatar></up-avatar>
    <u-swiper
      :list="list4"
      keyName="image"
      showTitle
      :autoplay="true"
      circular
    />
    <up-button type="primary" text="月落"></up-button>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const list4 = ref([
  {
    image: "https://cdn.uviewui.com/uview/swiper/swiper2.png",
    title: "昨夜星辰昨夜风，画楼西畔桂堂东",
  },
  {
    image: "https://cdn.uviewui.com/uview/swiper/swiper1.png",
    title: "身无彩凤双飞翼，心有灵犀一点通",
  },
  {
    image: "https://cdn.uviewui.com/uview/swiper/swiper3.png",
    title: "谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳",
  },
]);
</script>

<style lang="scss" scoped>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.text-area {
  display: flex;
  justify-content: center;
}

.title {
  font-size: 30rpx;
  color: #8f8f94;
}
</style>
