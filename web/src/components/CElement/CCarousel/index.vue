<!--  -->
<template>
  <ul class="basis-6 flex">
    <li
      class="carouselBtn hvr-underline-from-center cursor-pointer"
      v-for="(item, index) in options"
      :key="item"
      @click="change(item, index)"
    >
      {{ item.title }}
    </li>
  </ul>
  <el-carousel
    :height="height"
    ref="carousel"
    motion-blur
    :autoplay="false"
    arrow="never"
  >
    <el-carousel-item v-for="(item, index) in options" :key="index">
      <slot name="content" :item="item" :index="index" />
    </el-carousel-item>
  </el-carousel>
</template>

<script lang="ts" setup>
const active: any = defineModel();
const carousel = ref();
const emit = defineEmits(["tabChange"]);

defineProps({
  options: {
    text: "list",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
  height: {
    text: "height",
    type: [String] as any,
    default: "600px",
  },
});

function change(item: any, index: number) {
  active.value = index;
  carousel.value.setActiveItem(index);
  emit("tabChange", index, item);
}
</script>

<style lang="scss" scoped>
.carouselBtn {
  padding: 5px 15px;
  margin: 5px 5px;
  background-color: aquamarine;
}
</style>
