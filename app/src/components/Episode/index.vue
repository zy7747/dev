<!--  -->
<template>
  <div class="episodeBox">
    <ul class="episode">
      <li
        class="episodeItem"
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

  .episode {
    width: 100%;
    border-radius: 5px;
    margin-top: 3%;
    display: grid;
    grid-template-columns: repeat(5, 20%);
    align-content: flex-start;
    background-color: #f1f2f3;
    .episodeItem {
      height: 25px;
      padding: 5px;
      margin: 5px;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 5px;
      background-color: #ffffff;
      overflow: hidden;
      a {
        font-size: 16px;
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
