<!--  -->
<template>
  <div class="">
    <el-scrollbar height="400px" v-if="active === 0">
      <p
        v-for="item in formOptions"
        class="scrollbar-demo-item"
        @click="addForm(item)"
      >
        {{ item.name }}
      </p>
    </el-scrollbar>

    <el-scrollbar
      height="400px"
      v-else-if="active === 1 && pageData.tables.length"
    >
      <p
        v-for="item in tableOptions"
        class="scrollbar-demo-item"
        @click="addTableColum(item)"
      >
        {{ item.name }}
      </p>
    </el-scrollbar>

    <el-scrollbar height="400px" v-else-if="active === 2">
      <p
        v-for="item in formOptions"
        class="scrollbar-demo-item"
        @click="addEdit(item)"
      >
        {{ item.name }}
      </p>
    </el-scrollbar>
  </div>
</template>

<script lang="ts" setup>
const pageData: any = defineModel();

const prop: any = defineProps({
  active: {
    text: "active",
    type: Number,
  },
});

const formOptions = ref([
  {
    name: "输入框",
    attr: {
      label: "输入框",
      prop: "input",
      type: "input",
    },
  },
  {
    name: "下拉框",
    attr: {
      label: "下拉框",
      prop: "select",
      type: "select",
      options: [
        {
          label: "测试",
          value: "测试",
        },
      ],
    },
  },
  {
    name: "单选按钮",
    attr: {
      label: "单选按钮",
      prop: "radio",
      type: "radio",
      options: [
        {
          label: "测试",
          value: "测试",
        },
      ],
    },
  },
  {
    name: "多选按钮",
    attr: {
      label: "多选按钮",
      prop: "checkbox",
      type: "checkbox",
      options: [
        {
          label: "测试",
          value: "测试",
        },
      ],
    },
  },
]);
const tableOptions = ref([
  {
    name: "单选框",
    attr: { type: "radio", width: 50 },
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
    },
  },
  {
    name: "操作栏",
    attr: {
      title: "操作",
      cType: "action",
      fixed: "right",
    },
  },
]);

function addForm(item: any) {
  unref(pageData).form.push(item.attr);
}

function addTableColum(item: any) {
  if (prop.active === 1) {
    pageData.value.tables[pageData.value.tabIndex].tableColumn.push(item.attr);
  }
}

function addEdit(item: any) {
  if (prop.active === 2) {
    pageData.value.tables[pageData.value.tabIndex].dialogForm.push(item.attr);
  }
}
</script>

<style lang="scss" scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
  &:hover {
    border: 1px solid var(--el-color-primary);
    background-color: #fb8c55;
    color: #ffffff;
    cursor: pointer;
  }
}
</style>
