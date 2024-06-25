<!--  -->
<template>
  <div class="episodeBox">
    <div class="title">
      <span> {{ "选集列表" }}</span>
      <svg-icon name="svg-更多_竖向" style="width: 1rem; height: 1rem" />
    </div>
    <ul class="episode">
      <li
        v-for="(item, index) in episodeList"
        :key="index"
        :class="{ episodeActive: episodeActive == index }"
        @click="changeVideo(item, index)"
      >
        <a
          :class="{ aActive: episodeActive == index }"
          href="javascript:void(0);"
        >
          {{ item.episode }}
        </a>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { defineEmits } from "vue";

const emit = defineEmits(["changeVideo"]);

import { ref } from "vue";

defineProps({
  episodeList: {
    text: "episodeList",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});

const episodeActive = ref<number>(0);

const changeVideo = (item: any, index: number) => {
  episodeActive.value = index;

  emit("changeVideo", item.url);
};
</script>

<style lang="scss" scoped>
.episodeBox {
  width: 100%;
  height: 100%;
  .title {
    padding: 0 1rem;
    background-color: #f1f2f3;
    height: 10%;
    font-size: 0.8rem;
    font-weight: 700;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .episode {
    width: 100%;
    height: 87%;
    margin-top: 3%;
    border-radius: 5px;
    display: grid;
    align-content: start;
    grid-template-columns: repeat(4, 25%);
    overflow: auto;
    background-color: #f1f2f3;
    li {
      margin: 3px;
      height: 1.5625rem;
      padding: 0.3125rem;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 0.3125rem;
      background-color: #ffffff;
      a {
        font-size: 1rem;
        transform: scale(0.8);
        white-space: nowrap;
        color: rgb(112, 109, 109);
      }
    }
  }

  li:hover {
    cursor: pointer;
  }
}

.aActive {
  color: #ffffff !important;
}

.episodeActive {
  background-color: #00a1d6 !important;
}
</style>
