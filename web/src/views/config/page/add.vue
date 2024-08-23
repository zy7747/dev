<!--  -->
<template>
  <Collapse title="配置界面" padding="0">
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
    </template>
    <template #content>
      <Steps :steps="steps" v-model="active" />
      <el-container>
        <el-container>
          <el-aside width="250px" style="padding: 0 10px" v-if="3 > active">
            <Collapse title="组件" padding="0">
              <template #content>
                <Scrollbar :active="active" v-model="pageData" />
              </template>
            </Collapse>
          </el-aside>

          <el-main style="padding: 0">
            <Collapse title="主界面" padding="10px">
              <template #content>
                <component
                  ref="component"
                  v-model="pageData"
                  :pageData="pageData"
                  :is="componentName(active)"
                  :active="active"
                />
              </template>
            </Collapse>
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
  if (Route.query.id) {
    Service.config.page.detail({ id: Route.query.id }).then((res: any) => {
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
}
//保存
function save() {
  if (pageData.form) {
    return Service.config.page
      .save({
        ...pageData,
        options: JSON.stringify({
          tabIndex: pageData.tabIndex,
          form: pageData.form,
          tables: pageData.tables,
        }),
      })
      .then((res: any) => {
        submitSuccess(res);
      });
  }
}

function back() {
  Router.push({
    path: "/config/page",
  });
}

load();
</script>

<style lang="scss" scoped>
.el-header {
  padding: 0;
}
</style>
