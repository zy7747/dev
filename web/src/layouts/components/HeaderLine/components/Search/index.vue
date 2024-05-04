<!--  -->
<template>
  <div class="SearchInput">
    <div class="searchBox">
      <el-autocomplete
        v-model="keywords"
        :fetch-suggestions="querySearch"
        :placeholder="$t('form.search')"
        show-word-limit
        clearable
        @keyup.enter="searchVideo(keywords)"
      >
        <template #default="{ item }">
          <div class="value" @click="searchVideo(item.value)">
            {{ item.value }}
          </div>
        </template>

        <template #append>
          <div class="SearchIcon" @click="searchVideo(keywords)">
            <SearchIcon style="width: 1rem; height: 1rem" />
          </div>
        </template>
      </el-autocomplete>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Search as SearchIcon } from "@element-plus/icons-vue";
const Router = useRouter();

const props = defineProps({
  value: {
    text: "值",
    type: String,
    default: () => {
      return "";
    },
  },
});

const keywords = ref<string>(props.value);

watch(
  () => props.value,
  (value: any) => {
    keywords.value = value;
  }
);

const querySearch: any = async (queryString: string, cb: any) => {
  let results: any;
  if (queryString) {
    results = [{ label: queryString, value: queryString }];
  } else {
    results = [{ label: "推荐", value: "推荐" }];
  }

  cb(results);
};

function searchVideo(keyword: any) {
  Router.push({ path: "/videoSearch", query: { videoName: keyword } });
}
</script>

<style lang="scss" scoped>
.SearchInput {
  width: 400px;
  height: 100%;
  display: flex;
}
.SearchIcon {
  display: flex;
  justify-content: center;
  align-items: center;
  &:hover {
    color: rgb(127, 240, 255);
    cursor: pointer;
  }
}

.value {
  width: 20rem;
  overflow: hidden;
  text-overflow: ellipsis; //使用省略号代替溢出文本
  white-space: nowrap;
}

:deep(.el-input-group__append) {
  padding: 0;
  padding-right: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.searchBox {
  width: 100%;
  height: 40px;
  background-color: #fff;
  border: 1px solid #e3e5e7;
  border-radius: 8px;
  overflow: hidden;
  :deep(.el-autocomplete) {
    width: 100% !important;
    height: 100% !important;
  }

  :deep(.el-input) {
    width: 100% !important;
    height: 100% !important;
  }
}

:deep(.el-input__wrapper) {
  padding: 0 15px;
  box-shadow: none !important;
  width: 100%;
  height: 100%;
}

:deep(.el-input-group__append) {
  background-color: transparent;
  box-shadow: none !important;
  color: #000000;
}
</style>
