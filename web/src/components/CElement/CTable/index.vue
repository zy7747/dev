<!-- CTable -->
<template>
  <vxe-grid
    ref="xGrid"
    :columns="tableColumn"
    :data="tableData"
    v-bind="gridOptions"
    :loading="loading"
    @menu-click="contextMenuClickEvent"
  >
    <template #empty>
      <el-empty description="没有更多数据了" />
    </template>
    <template #toolbar_buttons>
      <Tools :tools="tableConfig.tools" :checkboxData="checkboxData()">
        <template
          v-for="item in toolsSlot(tableConfig.tools)"
          v-slot:[item.slot]
        >
          <slot :name="item.slot" />
        </template>
      </Tools>
    </template>

    <template #toolbar_tools> </template>

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
import Sortable from "sortablejs";
import Action from "./components/action.vue";
import Columns from "./components/columns.vue";
import lodash from "lodash";

import { VxeUI } from "vxe-table";
import { initFilter, getTableCols, getRules, toolsSlot } from "@/hooks/table";

const xGrid = ref<any>();
const Route = useRoute();

const paginationRef = ref();
const tableData: any = ref([]);
const filters: any = ref({}); //过滤数据
const total: Ref<number> = ref(0);
const loading: any = ref(false);

const prop = defineProps({
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

  getRules(prop.tableConfig.tableColumn, rules);

  return rules;
}

//塞入Filter
function setFilter() {
  const $grid = xGrid.value;

  initFilter(prop.tableConfig.tableColumn, unref(filters));

  if (tableData && unref(tableData).length) {
    //数据装入
    tableData.value.forEach((item: any) => {
      Object.keys(unref(filters)).forEach((key: any) => {
        unref(filters)[key].push({ label: item[key], value: item[key] });
      });
    });

    //数据去重
    Object.keys(unref(filters)).map((key) => {
      unref(filters)[key] = lodash.uniqBy(unref(filters)[key], "value");

      if ($grid) {
        $grid.setFilter(key, lodash.uniqBy(unref(filters)[key], "value"));
      }
    });
  }
}

//插入数据
const addLine = async (row: any) => {
  const $grid = xGrid.value;

  if ($grid) {
    if ($grid.isEditByRow(null)) {
      const { row: newRow } = await $grid.insertAt(
        { ...row, isAdd: true },
        null
      );

      prop.tableConfig.data.unshift(newRow);

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
  if (prop.tableConfig.query) {
    loading.value = true;
    return nextTick(() => {
      const page = unref(paginationRef).page;
      const size = unref(paginationRef).size;

      return prop.tableConfig
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
  } else if (prop.tableConfig.list) {
    loading.value = true;
    return prop.tableConfig
      .list()
      .then((res: any) => {
        tableData.value = JSON.parse(JSON.stringify(res.data));
      })
      .finally(() => {
        loading.value = false;
        setFilter();
      });
  } else {
    tableData.value = prop.tableConfig.data;
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

onMounted(() => {
  if (prop.tableConfig.isDrop) {
    Sortable.create(
      unref(xGrid).$el.querySelector(".body--wrapper>.vxe-table--body tbody"),
      {
        onEnd: ({ newIndex, oldIndex }: any) => {
          const targetRow = prop.tableConfig.data.splice(oldIndex, 1)[0];
          prop.tableConfig.data.splice(newIndex, 0, targetRow);

          for (let index in prop.tableConfig.data) {
            prop.tableConfig.data[index].sort = parseInt(index);
          }
        },
      }
    );
  }
});

//基本配置
const gridOptions = reactive<any>({
  border: false,
  round: true,
  size: "small",
  height: 500,
  align: "center",
  keepSource: true,
  id: `${Route.fullPath}/${prop.tableConfig.title}`, //缓存列状态
  showHeaderOverflow: "title",
  scrollX: { enabled: true, gt: 20 },
  scrollY: { enabled: true, gt: 50 },
  rowConfig: {
    useKey: true,
    keyField: "_row_index",
    isHover: true,
    isCurrent: true,
  },
  columnConfig: { isCurrent: false, isHover: false, maxFixedSize: 8 },
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
  },
  printConfig: {
    columns: prop.tableConfig.columns,
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
//合并参数
Object.assign(gridOptions, prop.tableConfig);

//列
const tableColumn: any = computed(() => {
  return getTableCols(prop.tableConfig.tableColumn);
});

defineExpose({
  query,
  reset,
  addLine,
  checkboxData,
  checkRadioData,
  setFilter,
});
</script>

<style lang="scss" scoped>
.pagination {
  margin-top: 15px;
}
</style>
