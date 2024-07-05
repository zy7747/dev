<!--  -->
<template>
  <div class="termsFilter">
    <u-sticky>
      <div class="sticky">
        <!-- 搜索 -->
        <uni-search-bar
          class="uni-mt-10"
          radius="100"
          placeholder="输入搜索条件"
          clearButton="none"
          cancelButton="none"
          @confirm="search"
        />
        <u-collapse :border="false">
          <u-collapse-item title="分类筛选" name="Docs guide">
            <u-tabs
              keyName="label"
              :list="videoCategoryList"
              @click="(item:any)=>tabsChange('type',item)"
            ></u-tabs>
            <u-tabs
              keyName="label"
              :list="videoTypeList"
              @click="(item:any)=>tabsChange('videoType',item)"
            ></u-tabs>
            <u-tabs
              keyName="label"
              :list="videoAreaList"
              @click="(item:any)=>tabsChange('region',item)"
            ></u-tabs>
            <u-tabs
              keyName="label"
              :list="videoSortWayList"
              @click="(item:any)=>tabsChange('sortWay',item)"
            ></u-tabs>
          </u-collapse-item>
        </u-collapse>
      </div>
    </u-sticky>

    <div style="height: calc(100vh - 128px); overflow: auto">
      <VideoList :videoList="videoList"></VideoList>
    </div>

    <div class="pagination">
      <uni-pagination
        v-model="queryParams.page"
        :total="total"
        :pageSize="queryParams.size"
        prev-text="前一页"
        next-text="后一页"
        @change="getVideoPage"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import VideoList from "@/components/VideoList/index.vue";
import { useDictStore } from "@/store/dict";

const dictStore: any = useDictStore();

//列表
const videoSortWayList = ref<any[]>([]);
const videoAreaList = ref<any[]>([]);
const videoTypeList = ref<any[]>([]);
const videoCategoryList = ref<any[]>([]);
const videoList = ref<any[]>();
const total = ref(0);

const queryParams = ref<any>({
  page: 1,
  size: 20,
  type: "movie",
  videoType: "",
  region: "",
  sortWay: "", //按点赞量排序
});

//获取字典
function setDict() {
  // 排序类型
  const categoryList = JSON.parse(
    JSON.stringify(dictStore.dict["video_category"])
  );

  videoCategoryList.value = categoryList;

  // 排序方式
  const sortWayList = JSON.parse(
    JSON.stringify(dictStore.dict["video_sort_way"])
  );

  videoSortWayList.value = sortWayList;

  //类型
  const typeList = JSON.parse(JSON.stringify(dictStore.dict["video_type"]));

  videoTypeList.value = typeList;

  //地区
  const areaList = JSON.parse(JSON.stringify(dictStore.dict["video_area"]));

  videoAreaList.value = areaList;
}

//关键字搜索
function search(res: any) {
  uni.showToast({
    title: "搜索：" + res.value + "页面跳转",
    icon: "none",
  });
}

//切换筛选条件
function tabsChange(key: string, item: any) {
  if (key === "type") {
    queryParams.value.type = item.value;
  } else if (key === "sortWay") {
    queryParams.value.sortWay = item.value;
  } else if (key === "videoType") {
    queryParams.value.videoType = item.value;
  } else if (key === "region") {
    queryParams.value.region = item.value;
  }
  getVideoPage();
}

function getVideoPage() {
  Service.videoPage(queryParams.value).then((res: any) => {
    videoList.value = res.data.list;
    total.value = res.data.total;
  });
}

setDict();
getVideoPage();
</script>

<style lang="scss" scoped>
.sticky {
  background-color: #fff;
}
.termsFilter {
  position: relative;
  height: 100vh;
  .pagination {
    bottom: 0;
    width: 100%;
    position: fixed;
    background: #bfbfbf;
  }
}
</style>
