<!--  -->
<template>
  <c-button
    @click="more = !more"
    class="btnR"
    type="success"
    link
    text="更多筛选:"
  />
  <div v-show="more">
    <ul class="flex filterList">
      <li v-for="item in videoSortWay">
        <c-button
          class="btnR"
          @click="query(item, 'sortWay')"
          :type="pageData.sortWay === item.value ? 'success' : ''"
          :text="item.label"
          size="small"
        />
      </li>
    </ul>
    <ul class="flex filterList">
      <li v-for="item in videoType">
        <c-button
          @click="query(item, 'type')"
          :type="pageData.type === item.value ? 'success' : ''"
          :text="item.label"
          class="btnR"
          size="small"
        />
      </li>
    </ul>
    <ul class="flex filterList">
      <li v-for="item in videoArea">
        <c-button
          @click="query(item, 'region')"
          :type="pageData.region === item.value ? 'success' : ''"
          :text="item.label"
          class="btnR"
          size="small"
        />
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
const more = ref(false);
const emit = defineEmits(["query"]);
const videoSortWay = Dict("video_sort_way");
const videoType = Dict("video_type");
const videoArea = Dict("video_area");

const pageData = ref({
  sortWay: "",
  region: "",
  type: "",
});

function query(item: any, type: string) {
  if (type === "region") {
    unref(pageData).region = item.value;
  } else if (type === "sortWay") {
    unref(pageData).sortWay = item.value;
  } else if (type === "type") {
    unref(pageData).type = item.value;
  }

  emit("query", pageData.value);
}
</script>

<style lang="scss" scoped>
.filterList {
  margin-bottom: 5px;
}
</style>
