<!--  -->
<template>
  <ul class="list">
    <li class="listBox" v-for="item in videoList">
      <videoBox :videoItem="item" />
    </li>
  </ul>
</template>

<script lang="ts" setup>
import videoBox from "./videoBox.vue";

defineProps({
  videoList: {
    text: "分类",
    type: [Array] as any,
  },
});
</script>

<style lang="scss" scoped>
.listBox {
  height: 200px;
  margin: 5px;
}

.list {
  width: 100%;
  // height: 90vh;
  // overflow: auto;
  display: grid;
  grid-template-columns: repeat(10, 10%);
  align-content: flex-start;
}
</style>
