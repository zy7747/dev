<template>
  <div class="filter-content">
    <template v-if="params.column.filters.length">
      <c-input
        class="filterInput"
        size="small"
        :suffix-icon="Search"
        v-model="_data.filterText"
      />

      <ul class="filterList">
        <li class="checkedAll">
          <vxe-checkbox
            v-model="_data.checkedAll"
            @change="checkAllFn(_data.checkedAll)"
          >
            全选
          </vxe-checkbox>
        </li>
        <li v-for="(item, sIndex) in _data.valList" :key="sIndex">
          <vxe-checkbox v-model="item.checked" @change="checkFn()">
            {{ item.value }}
          </vxe-checkbox>
        </li>
      </ul>

      <div class="actionBtn">
        <c-button
          size="small"
          type="primary"
          @handleClick="confirm"
          :text="$t('system.filter')"
          :icon="SuccessFilled"
        />
        <c-button
          :icon="RefreshLeft"
          size="small"
          :text="$t('system.reset')"
          @handleClick="reset"
        />
      </div>
    </template>

    <template v-else>
      <div>无匹配项</div>
    </template>
  </div>
</template>

<script lang="ts" setup>
import { SuccessFilled, RefreshLeft, Search } from "@element-plus/icons-vue";

const prop = defineProps({
  params: {
    text: "类型",
    type: [Object] as any,
  },
});

const _data: any = reactive({
  checkedAll: false,
  filterText: "",
  valList: [], // 过滤后
});

//过滤文字
watch(
  () => _data.filterText,
  (val) => {
    const list = prop.params.column.filters;
    if (val) {
      _data.valList = list.filter((item: any) => {
        return item.value.indexOf(val) !== -1;
      });
    } else {
      _data.valList = list;
    }
  }
);
//全选
function checkAllFn(val: boolean) {
  const list = prop.params.column.filters;
  if (val) {
    list.forEach((item: any) => {
      item.checked = true;
    });
  } else {
    list.forEach((item: any) => {
      item.checked = false;
    });
  }
}
//单选
function checkFn() {
  const list = prop.params.column.filters;
  _data.checkedAll = list.every((item: any) => {
    return item.checked === true;
  });
}
//筛选
function confirm() {
  const { $panel } = prop.params;
  _data.valList.forEach((item: any) => {
    $panel.changeOption(null, item.checked, item);
  });
  $panel.confirmFilter();
}
//重置
function reset() {
  const list = prop.params.column.filters;

  list.forEach((item: any) => {
    item.checked = false;
  });

  _data.checkedAll = false;

  _data.filterText = "";

  const { $panel } = prop.params;
  $panel.resetFilter();
}

_data.valList = prop.params.column.filters;
checkFn();
</script>

<style lang="scss" scoped>
.filter-content {
  padding: 10px;
}

.actionBtn {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 15px;
}

.filterInput {
  margin-bottom: 5px;
}

.filterList {
  max-height: 300px;
  overflow: auto;
  position: relative;
  .checkedAll {
    position: sticky;
    top: 0;
    background-color: #fff;
  }

  li {
    width: 100%;
  }
}
</style>
