<!--  -->
<template>
  <videoFilter @query="query" />
  <VideoList :videoList="videoList" />
  <div class="PaginationBox">
    <Pagination
      v-show="total > 0"
      :sizes="30"
      @query="query"
      ref="paginationRef"
      :total="total"
    />
  </div>
</template>

<script lang="ts" setup>
import videoFilter from "./videoFilter.vue";
import VideoList from "./videoList.vue";
const total: Ref<number> = ref(0);
const paginationRef = ref();
const videoList = ref([]);

const prop = defineProps({
  category: {
    text: "分类",
    type: [String],
  },
});

function query(data: any) {
  nextTick(() => {
    const page = unref(paginationRef).page;
    const size = unref(paginationRef).size;

    Service.video.list
      .page({ page, size, category: prop.category, ...data })
      .then((res: any) => {
        videoList.value = res.data.list;
        total.value = res.data.total;
      });
  });
}

query({});
</script>

<style lang="scss" scoped>
.PaginationBox {
  display: flex;
  justify-content: center;
  margin-top: 15px;
  width: 100%;
}
</style>
