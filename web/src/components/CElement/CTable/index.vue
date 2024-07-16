<!-- CTable -->
<template>
  <vxe-grid
    ref="xGrid"
    keep-source
    :columns="tableColumn"
    :data="tableData"
    v-bind="gridOptions"
    :loading="loading"
    @menu-click="contextMenuClickEvent"
  >
    <template #toolbar_buttons>
      <Tools :tools="tableConfig.tools" :checkboxData="checkboxData()" />
    </template>

    <template #toolbar_tools>
      <div class="flex">
        <c-schema
          class="btnR"
          :params="{ size: 'small' }"
          type="input"
          v-model="search"
        />
        <c-button
          size="small"
          type="primary"
          class="btnR"
          @handleClick="query"
          :text="$t('system.table search')"
          :icon="Search"
        />
      </div>
    </template>

    <template #tableSlot="{ row, column }: any">
      <Columns
        @click="column.params.on.click({ row, column })"
        :type="column.params.cType"
        :params="column.params"
        v-model="row[column.field]"
      />
    </template>

    <template #tableEdit="{ row, column }: any">
      <c-schema
        :item="column"
        :type="column.params.form.type"
        :params="column.params.form.params"
        v-model="row[column.field]"
      />
    </template>

    <template #operate="{ row }">
      <Action
        v-model="tableConfig.data"
        :actions="tableConfig.actions"
        :row="row"
        :xGrid="xGrid"
      />
    </template>

    <template #pager>
      <div
        v-show="total !== 0"
        class="flex w-full justify-end items-center pagination"
      >
        <Pagination @query="query" ref="paginationRef" :total="total" />
      </div>
    </template>
  </vxe-grid>
</template>

<script lang="ts" setup>
import "./tsx/filter.tsx";

import Action from "./components/action.vue";
import Columns from "./components/columns.vue";
import lodash from "lodash";

import { VxeUI } from "vxe-table";
import { Search } from "@element-plus/icons-vue";
import { getFilter, getTableCols, getRules } from "@/hooks/table";

const xGrid = ref<any>();
const Route = useRoute();

const paginationRef = ref();
const tableData = ref();
const filters: any = ref({});
const search = ref();

const total: Ref<number> = ref(0);
const loading: any = ref(false);

const { tableConfig } = defineProps({
  tableConfig: {
    text: "表格配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

function Rules() {
  let rules = {};

  getRules(tableConfig.tableColumn, rules);

  return rules;
}

function getFilters() {
  getFilter(tableConfig.tableColumn, unref(filters));
}

function setFilter() {
  //数据装入
  tableData.value.forEach((item: any) => {
    Object.keys(unref(filters)).forEach((key: any) => {
      unref(filters)[key].push({ label: item[key], value: item[key] });
    });
  });

  //数据去重
  Object.keys(unref(filters)).map((key) => {
    unref(filters)[key] = lodash.uniqBy(unref(filters)[key], "value");
  });
}
//列
const tableColumn: any = computed(() => {
  return getTableCols(tableConfig.tableColumn, filters);
});

//插入数据
const addLine = async (row: any) => {
  const $grid = xGrid.value;

  if ($grid) {
    if ($grid.isEditByRow(null)) {
      const { row: newRow } = await $grid.insertAt(
        { ...row, isAdd: true },
        null
      );

      tableConfig.data.unshift(newRow);

      await $grid.setEditRow(newRow);
    } else {
      ElMessage({
        message: "请先保存数据再新增",
        type: "warning",
        showClose: true,
        grouping: true,
      });
    }
  }
};
//多选
function checkboxData() {
  if (xGrid.value) {
    return xGrid.value.getCheckboxRecords();
  } else {
    return [];
  }
}
//单选
function checkRadioData() {
  if (xGrid.value) {
    return xGrid.value.getCheckboxRecords();
  } else {
    return [];
  }
}
//查询
function query() {
  if (tableConfig.query) {
    loading.value = true;
    return nextTick(() => {
      const page = unref(paginationRef).page;
      const size = unref(paginationRef).size;

      return tableConfig
        .query({ page, size })
        .then((res: any) => {
          tableData.value = JSON.parse(JSON.stringify(res.data.list));
          total.value = res.data.total;
        })
        .finally(() => {
          loading.value = false;
          setFilter();
        });
    });
  } else if (tableConfig.list) {
    loading.value = true;
    return tableConfig
      .list()
      .then((res: any) => {
        tableData.value = JSON.parse(JSON.stringify(res.data));
      })
      .finally(() => {
        loading.value = false;
        setFilter();
      });
  }
}

function reset() {
  unref(paginationRef).reset();
}

function contextMenuClickEvent({ menu, row, column }: any) {
  switch (menu.code) {
    case "copy":
      // 复制
      if (row && column) {
        if (VxeUI.clipboard.copy(row[column.field])) {
          VxeUI.modal.message({
            content: "已复制到剪贴板！",
            status: "success",
          });
        }
      }
      break;
    case "query":
      query();
      break;
    default:
      VxeUI.modal.alert(`点击了 ${menu.name} 选项`);
  }
}

//基本配置
const gridOptions = reactive<any>({
  border: true,
  round: true,
  size: "small",
  height: 500,
  align: "center",
  keepSource: true,
  id: `${Route.fullPath}/${tableConfig.title}`, //缓存列状态
  showHeaderOverflow: "title",
  scrollX: { enabled: true, gt: 20 },
  scrollY: { enabled: true, gt: 50 },
  rowConfig: {
    keyField: "_row_index",
    isHover: false,
    isCurrent: false,
  },
  resizableConfig: {
    minWidth: 80,
  },
  columnConfig: { isCurrent: false, isHover: false, maxFixedSize: 12 },
  editConfig: {
    trigger: "manual",
    mode: "row",
    autoClear: false,
    showStatus: false,
    showIcon: false,
  },
  menuConfig: {
    body: {
      options: [
        [
          {
            code: "copy",
            name: "复制",
            prefixIcon: "vxe-icon-copy",
            className: "my-copy-item",
          },
        ],
        [
          {
            code: "query",
            name: "刷新",
            prefixIcon: "vxe-icon-refresh",
          },
        ],
      ],
    },
  },
  customConfig: {
    storage: true,
    mode: "popup",
    immediate: true,
  },
  printConfig: {
    columns: tableConfig.columns,
  },
  loadingConfig: { text: "正在拼命加载中..." },
  checkboxConfig: {
    reserve: true,
    highlight: true,
  },
  toolbarConfig: {
    slots: {
      buttons: "toolbar_buttons",
      tools: "toolbar_tools",
    },
    refresh: {
      queryMethod() {
        query();
      },
    },
    print: true, // 显示打印按钮
    zoom: true, // 显示全屏按钮
    custom: true, // 显示自定义列按钮
    export: true, // 显示导出按钮
  },
  editRules: Rules(),
});

getFilters();
Object.assign(gridOptions, tableConfig);

defineExpose({
  query,
  reset,
  addLine,
  checkboxData,
  checkRadioData,
});
</script>

<style lang="scss" scoped>
.pagination {
  margin-top: 15px;
}
</style>
