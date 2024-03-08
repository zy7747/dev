<!-- CTable -->
<template>
  <vxe-grid
    ref="xGrid"
    :columns="tableColumn"
    :data="tableData"
    v-bind="{ ...gridOptions, ...tableConfig }"
  >
    <template #toolbar_buttons>
      <Tools :tools="tableConfig.tools" />
    </template>

    <template #tableSlot="{ row, column }: any">
      <tableSlot :type="column.params.cType" v-model="row[column.field]" />
    </template>

    <template #tableEdit="{ row, column }: any">
      <c-schema
        :type="column.params.form.type"
        :params="column.params.form.params"
        v-model="row[column.field]"
      />
    </template>

    <template #operate="{ row }">
      <Action :actions="tableConfig.actions" :row="row" :xGrid="xGrid" />
    </template>

    <template #pager>
      <Pagination class="pagination" ref="paginationRef" :total="total" />
    </template>
  </vxe-grid>
</template>

<script lang="ts" setup>
import { VxeGridProps, VxeGridInstance } from "vxe-table";
import Action from "./components/action.vue";
import Tools from "./components/tools.vue";
import tableSlot from "./components/tableSlot.vue";
import lodash from "lodash";

const paginationRef = ref();
const xGrid = ref<VxeGridInstance<any>>();

const total = ref(0);
const loading: any = ref(false);
const tableData = ref([]);
const prop: any = defineProps({
  tableConfig: {
    text: "表格配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

//
function getTableCols(columns: any) {
  return unref(columns).map((item: any) => {
    const col: any = {
      resizable: true,
      slots: {},
      params: {},
      "show-header-overflow": "ellipsis",
    };

    //编辑插槽
    if (!item.editRender && item.form) {
      col.editRender = {};
      col.slots.edit = "tableEdit";

      Object.assign(col.params, { form: item.form });
    }

    //自定义渲染插槽
    if (item.cType && item.cType !== "action") {
      col.slots.default = "tableSlot";
      Object.assign(col.params, { cType: item.cType });
    }

    //过滤
    if (item.isFilters) {
      col.filters = filters.value[item.field];
    }

    //自定义插槽
    if (item.slots) {
      col.slots = item.slots;
    }

    if (item.children && item.children.length) {
      col.children = getTableCols(item.children);
    }

    if (item.cType === "action") {
      col.slots = { default: "operate" };
    }

    return { ...item, ...col };
  });
}
//校验
const rules: any = computed(() => {
  const rules: any = {};

  prop.tableConfig.tableColumn.forEach((item: any) => {
    if (item.rules) {
      rules[item.field] = item.rules;
    }
  });

  return rules;
});
//过滤
function getFilter(columns: any, filterMap: any) {
  columns.forEach((item: any) => {
    if (item.isFilters) {
      filterMap[item.field] = [];
    }

    if (item.children && item.children.length) {
      getFilter(item.children, filterMap);
    }
  });
}
const filters: any = computed(() => {
  const filterMap: any = {};

  //数据平铺
  getFilter(prop.tableConfig.tableColumn, filterMap);

  //数据装入
  unref(tableData).forEach((item: any) => {
    Object.keys(filterMap).forEach((key: any) => {
      if (item[key]) {
        filterMap[key].push({ label: item[key], value: item[key] });
      }
    });
  });

  //数据去重
  Object.keys(filterMap).map((key) => {
    filterMap[key] = lodash.uniqBy(filterMap[key], "value");
  });

  return filterMap;
});
//列
const tableColumn: any = computed(() => {
  return getTableCols(prop.tableConfig.tableColumn);
});
//多选
const checkboxData: any = computed(() => {
  if (xGrid.value) {
    return xGrid.value.getCheckboxRecords();
  } else {
    return [];
  }
});
//查询
function query() {
  if (typeof prop.tableConfig.query === "function") {
    nextTick(() => {
      loading.value = true;
      const page = unref(paginationRef).page;
      const size = unref(paginationRef).size;

      prop.tableConfig
        .query({ page, size })
        .then((res: any) => {
          tableData.value = res.data.list;
          total.value = res.data.total;
          loading.value = false;
        })
        .catch(() => {
          loading.value = false;
        })
        .finally(() => {
          loading.value = false;
        });
    });
  }
}
//插入数据
const addLine = async (rows: any) => {
  const $grid = xGrid.value;

  if ($grid) {
    if ($grid.isEditByRow(null)) {
      const { row: newRow } = await $grid.insertAt(
        { ...rows, isAddRow: true },
        null
      );

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
//基本配置
const gridOptions = reactive<VxeGridProps<any>>({
  border: true,
  stripe: true,
  round: true,
  size: "small",
  height: 500,
  loading,
  align: "center",
  keepSource: true,
  showOverflow: "tooltip",
  showHeaderOverflow: "title",
  scrollX: { enabled: true, gt: 20 },
  scrollY: { enabled: true, gt: 50 },
  rowConfig: {
    keyField: "_row_index",
    isHover: true,
    isCurrent: true,
  },
  columnConfig: { isCurrent: true, isHover: true },
  editConfig: {
    trigger: "manual",
    mode: "row",
    autoClear: false,
    showStatus: false,
    showIcon: false,
  },
  printConfig: {
    columns: prop.tableConfig.columns,
  },
  loadingConfig: { icon: "vxe-icon-indicator roll", text: "正在拼命加载中..." },
  checkboxConfig: {
    trigger: "row",
    reserve: true,
    highlight: true,
    range: true,
  },
  toolbarConfig: {
    slots: {
      buttons: "toolbar_buttons",
    },
    refresh: {
      queryMethod() {
        query();
      },
    },
    print: true, // 显示打印按钮
    zoom: true, // 显示全屏按钮
    custom: true, // 显示自定义列按钮
  },
  editRules: rules,
});

if (prop.tableConfig.createLoad) {
  query();
}

defineExpose({
  query,
  addLine,
  checkboxData,
});
</script>

<style lang="scss" scoped>
.pagination {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: end;
  margin-top: 15px;
}
</style>
