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

    <template #pager>
      <Pagination ref="paginationRef" :total="total" />
    </template>

    <template #tableEdit="{ row, column }: any">
      <c-schema
        v-if="column.params.form"
        :type="column.params.form.type"
        :params="column.params.form.params"
        v-model="row[column.field]"
      />
    </template>

    <template #operate="{ row }">
      <Action :actions="tableConfig.actions" :row="row" :xGrid="xGrid" />
    </template>
  </vxe-grid>
</template>

<script lang="ts" setup>
import { VxeGridProps, VxeGridInstance } from "vxe-table";
import Action from "./components/action.vue";
import Tools from "./components/tools.vue";

const total = ref(0);
const loading: any = ref(false);
const paginationRef = ref();
const tableData = ref([]);

const xGrid = ref<VxeGridInstance<any>>();

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
    };

    if (!item.editRender && item.form) {
      col.editRender = {};
      col.slots.edit = "tableEdit";

      Object.assign(col.params, { form: item.form });
    }

    if (item.slots) {
      col.slots = item.slots;
    }

    if (item.isFilters) {
      col.filters = filters.value[item.field];
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
const filters: any = computed(() => {
  const filterMap: any = {};

  prop.tableConfig.tableColumn.forEach((item: any) => {
    if (item.isFilters) {
      filterMap[item.field] = [];
    }
  });

  unref(tableData).forEach((item: any) => {
    Object.keys(filterMap).forEach((key: any) => {
      if (item[key]) {
        filterMap[key].push({ label: item[key], value: item[key] });
      }
    });
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
  rowConfig: {
    keyField: "_row_index",
    isHover: true,
  },
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
    refresh: true, // 显示刷新按钮
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
  checkboxData,
});
</script>

<style lang="scss" scoped></style>
