<!--  -->
<template>
  <div class="table">
    <el-steps :space="100" simple :active="active">
      <el-step
        class="step"
        v-for="(item, index) in steps"
        :title="item.title"
        :icon="item.icon"
        @click="stepChange(index)"
      />
    </el-steps>

    <div class="edit">
      <div class="left">
        <div>{{ "表单组件" }}</div>
        <el-scrollbar height="200px">
          <p
            v-for="item in formOptions"
            class="scrollbar-demo-item"
            @click="addLine(item)"
          >
            {{ item.name }}
          </p>
        </el-scrollbar>
        <div>{{ "表格栏位" }}</div>
        <el-scrollbar height="200px">
          <p
            v-for="item in tableOptions"
            class="scrollbar-demo-item"
            @click="addLine(item)"
          >
            {{ item.name }}
          </p>
        </el-scrollbar>
        <div>{{ "操作按钮" }}</div>
        <el-scrollbar height="200px">
          <p
            v-for="item in btnOptions"
            class="scrollbar-demo-item"
            @click="addLine(item)"
          >
            {{ item.name }}
          </p>
        </el-scrollbar>
      </div>
      <div class="right">
        <c-table ref="tableRef" :tableConfig="list[active].tableConfig" />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Monitor, DataLine, Picture, EditPen } from "@element-plus/icons-vue";
const active: any = ref(1);
const pageData: any = ref({
  form: [],
  tables: [],
});

const tableColumn = () => {
  return [
    { type: "checkbox", width: 50 },
    {
      title: "序号",
      type: "seq",
      width: 100,
    },
    {
      title: "字段名称", //字段名称
      field: "title",
      sortable: true,
      isFilters: true,
      width: 200,
      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
    },
    {
      title: "字段值", //字段值
      field: "field",
      sortable: true,
      isFilters: true,
      width: 200,
      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
    },
    {
      title: "类型", //字段值
      field: "type",
      sortable: true,
      isFilters: true,
      width: 200,
      rules: [
        {
          type: "date",
          required: true,
          message: "Please pick a date",
          trigger: "change",
        },
      ],
    },
    {
      title: "值集", //值集
      field: "options",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要校验必填", //是否需要校验必填
      field: "isFilters",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要筛选", //是否需要筛选
      field: "isFilters",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "是否需要排序", //是否需要排序
      field: "sortable",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "表单宽度", //基本宽度
      field: "span",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "表格栏位宽度", //基本宽度
      field: "width",
      sortable: true,
      isFilters: true,
      width: 200,
    },
    {
      title: "操作",
      cType: "action",
      fixed: "right",
      width: 250,
    },
  ];
};

const tableConfig = ref({
  tools: [
    {
      type: "add",
      click() {},
    },
    {
      type: "remove",
      click() {
        Service.page.remove().then((res: any) => {
          if (res.code === 200) {
            ElMessage({
              message: "删除成功",
              type: "success",
            });
          }
        });
      },
    },
    {
      type: "import",
    },
    {
      type: "export",
    },
  ],
  tableColumn: tableColumn(),
  actions: [
    {
      type: "edit",
      click({ row }: any) {
        console.log(row);
      },
    },
    {
      type: "detail",
      click({ row }: any) {
        console.log(row);
      },
    },
    {
      type: "remove",
      click({ row }: any) {
        console.log(row);
      },
    },
  ],
  data: pageData.value.form,
});

const steps = ref([
  {
    title: "查询条件",
    icon: DataLine,
  },
  {
    title: "表格",
    icon: Monitor,
  },
  {
    title: "编辑界面",
    icon: EditPen,
  },
  {
    title: "浏览界面",
    icon: Picture,
  },
]);

const list = ref([
  {
    title: "查询条件",
    tableConfig: tableConfig.value,
  },
  {
    title: "操作按钮",
    tableConfig: tableConfig.value,
  },
  {
    title: "表格",
    tableConfig: tableConfig.value,
  },
  {
    title: "编辑界面",
    tableConfig: tableConfig.value,
  },
]);

const formOptions = ref([
  {
    name: "输入框",
    attr: {
      label: "输入框",
      prop: "prop",
      type: "input",
      span: 6,
    },
  },
  {
    name: "下拉框",
    attr: {
      label: "下拉框",
      prop: "prop",
      type: "select",
      options: [],
      span: 6,
    },
  },
  {
    name: "单选按钮",
    attr: {
      label: "单选按钮",
      prop: "prop",
      type: "radio",
      options: [],
      span: 6,
    },
  },
  {
    name: "多选按钮",
    attr: {
      label: "单选按钮",
      prop: "prop",
      type: "checkbox",
      options: [],
      span: 6,
    },
  },
]);
const tableOptions = ref([
  {
    name: "单选框",
    attr: {},
  },
  {
    name: "多选框",
    attr: { type: "checkbox", width: 50 },
  },
  {
    name: "序号",
    attr: {
      title: "序号", //用户名
      type: "seq",
      width: 100,
    },
  },
  {
    name: "图片",
    attr: {
      title: "图片",
      cType: "picture",
      width: 200,
    },
  },
  {
    name: "链接",
    attr: {
      title: "链接",
      cType: "link",
      width: 200,
    },
  },
  {
    name: "操作栏",
    attr: {
      title: "操作",
      cType: "action",
      fixed: "right",
      width: 250,
    },
  },
]);
const btnOptions = ref([
  {
    name: "单选框",
    attr: {},
  },
  {
    name: "多选框",
    attr: { type: "checkbox", width: 50 },
  },
  {
    name: "序号",
    attr: {
      title: "序号", //用户名
      type: "seq",
      width: 100,
    },
  },
  {
    name: "操作栏",
    attr: {
      title: "操作",
      cType: "action",
      fixed: "right",
      width: 250,
    },
  },
  {
    name: "新增",
    attr: {},
  },
  {
    name: "编辑",
    attr: {},
  },
  {
    name: "批量删除",
    attr: {},
  },
  {
    name: "导入",
    attr: {},
  },
  {
    name: "导出",
    attr: {},
  },
  {
    name: "编辑",
    attr: {},
  },
  {
    name: "详情",
    attr: {},
  },
  {
    name: "删除",
    attr: {},
  },
]);

const stepChange = (index: Number) => {
  active.value = index;
};

function addLine(item: any) {
  pageData.value.form.push(item.attr);
}
</script>

<style lang="scss" scoped>
.table {
  width: 100%;
  .step:hover {
    cursor: pointer;
  }
}

.edit {
  display: flex;
  .left {
    width: 30%;
  }

  .right {
    padding: 5px;
    width: 70%;
  }
}

.el-scrollbar {
  height: 30%;
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  text-align: center;
  border-radius: 4px;
  margin: 10px 0;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.scrollbar-demo-item:hover {
  cursor: pointer;
}
</style>
