<!--  -->
<template>
  <Collapse title="配置页面">
    <template #tools>
      <c-button
        size="small"
        type="primary"
        :icon="DocumentChecked"
        :text="$t('system.save')"
        @handleClick="save"
      />
      <c-button
        :icon="RefreshLeft"
        size="small"
        :text="$t('system.back')"
        @handleClick="back"
      />
      <c-button
        :icon="RefreshLeft"
        size="small"
        text="弹窗"
        @handleClick="dialog"
      />
    </template>
    <template #content>
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
  </Collapse>
</template>

<script lang="ts" setup>
import Steps from "./components/steps.vue";
import Scrollbar from "./components/scrollbar.vue";
import Query from "./components/query.vue";
import Table from "./components/table.vue";
import EditForm from "./components/edit.vue";
import View from "./components/view.vue";
import JsonView from "./components/jsonView.vue";

import {
  Monitor,
  DataLine,
  Picture,
  Edit,
  RefreshLeft,
  DocumentChecked,
} from "@element-plus/icons-vue";

import { useDialog } from "@/hooks/useDialog";

//页面数据
const pageData: any = reactive({
  tabIndex: "0",
  form: [],
  tables: [],
});
const component = ref();
const Route = useRoute();
const Router = useRouter();

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
//加载数据
function load() {
  Service.configuration.page.detail({ id: Route.query.id }).then((res: any) => {
    const options = JSON.parse(res.data.options);

    if (options && options !== "") {
      Object.assign(pageData, { id: res.data.id });

      pageData.form.push(...options.form);
      pageData.tables.push(...options.tables);
    } else {
      Object.assign(pageData, { id: res.data.id });
    }
  });
}
//保存
function save() {
  if (pageData.form) {
    return Service.configuration.page
      .save({
        ...pageData,
        options: JSON.stringify({
          tabIndex: pageData.tabIndex,
          form: pageData.form,
          tables: pageData.tables,
        }),
      })
      .then((res: any) => {
        if (res.code === 200) {
          ElMessage({
            message: "提交成功",
            type: "success",
          });
          back();
        }
      });
  }
}

function back() {
  Router.push({
    path: "/configuration/page",
  });
}

function dialog() {
  useDialog();
}

load();
</script>

<style lang="scss" scoped>
.el-header {
  padding: 0;
}
</style>
