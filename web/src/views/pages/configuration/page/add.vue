<!--  -->
<template>
  <el-container>
    <el-header>
      <Steps :steps="steps" v-model="active" />
    </el-header>
    <el-container>
      <el-aside width="250px" v-if="active !== 3">
        <Scrollbar :active="active" v-model="pageData" />
      </el-aside>

      <el-main>
        <component
          ref="component"
          v-model="pageData"
          :pageData="pageData"
          :is="componentName(active)"
          :active="active"
        />
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import Steps from "./components/steps.vue";
import Scrollbar from "./components/scrollbar.vue";
import Query from "./components/query.vue";
import Table from "./components/table.vue";
import EditForm from "./components/edit.vue";
import View from "./components/view.vue";
import JsonView from "./components/jsonView.vue";

import { Monitor, DataLine, Picture, Edit } from "@element-plus/icons-vue";

const component = ref();
const pageData: any = ref({
  tabIndex: "0",
  form: [],
  tables: [],
});

const active: any = ref(0);

const steps: any = ref([
  {
    title: "查询",
    icon: markRaw(DataLine),
    component: markRaw(Query),
  },
  {
    title: "表格",
    icon: markRaw(Monitor),
    component: markRaw(Table),
  },
  {
    title: "编辑框",
    icon: markRaw(Edit),
    component: markRaw(EditForm),
  },
  {
    title: "查看JSON",
    icon: markRaw(Picture),
    component: markRaw(JsonView),
  },
  {
    title: "浏览界面",
    icon: markRaw(Picture),
    component: markRaw(View),
  },
]);

function componentName(active: number) {
  return steps.value[active].component;
}
</script>

<style lang="scss" scoped>
.el-header {
  padding: 0;
}
</style>
