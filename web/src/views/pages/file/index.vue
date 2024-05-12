<!-- 文件 file -->
<template>
  <el-container>
    <el-aside width="15vw">
      <Tree :data="tree" @node-click="nodeClick" />
    </el-aside>
    <el-main>
      <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
        <template #fileView>
          <View :data="list" />
        </template>
      </c-page>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import View from "./components/View.vue";
import Tree from "./components/Tree.vue";
import { handleTree } from "@/utils/formatData";
const fileUrl = import.meta.env.VITE_APP_FILE_URL;

defineOptions({
  name: "File",
});

const pageData: any = reactive({
  queryData: {},
  editData: {},
});

const nodeInfo: any = ref({
  id: null,
});
const list: any = ref([]);
const tree: any = ref([]);

const { pageOption, pageRef, ids, removeSuccess, submitSuccess } = usePage({
  createLoad: true,
  title: $t("file.file", "文件"),
  formConfig: {
    formParams: [
      {
        label: $t("file.fileName", "文件名称"),
        prop: "fileName",
        type: "input",
        span: 6,
      },
      {
        label: $t("file.fileType", "文件类型"),
        prop: "fileType",
        type: "input",
        span: 6,
      },
      {
        label: $t("table.status", "状态"),
        prop: "status",
        type: "input",
        span: 6,
      },
    ],
  },
  tableConfig: [
    {
      title: $t("file.file list", "文件列表"),
      tools: [
        {
          operation: "remove",
          permission: ["file:remove"],
          click() {
            return Service.file.remove(ids()).then((res: any) => {
              removeSuccess(res);
            });
          },
        },
        {
          operation: "export",
          permission: ["file:export"],
          api() {
            return Service.file.exports(unref(pageData).queryData);
          },
          fileName: $t("file.file", "文件"),
        },
      ],
      tableColumn: [
        { type: "checkbox", width: 50, fixed: "left" },
        {
          title: $t("system.no", "序号"),
          type: "seq",
          width: 60,
          fixed: "left",
        },
        {
          title: $t("file.fileName", "文件名称"),
          field: "fileName",
          cType: "link",
          on: {
            click({ row }: any) {
              if (row.url) {
                window.open(fileUrl + row.url);
              }
            },
          },
          isFilters: true,
          width: 200,
        },
        {
          title: $t("file.fileType", "文件类型"),
          field: "fileType",
          isFilters: true,
          width: 150,
        },
        {
          title: $t("file.fileSize", "文件大小"),
          field: "fileSize",
          isFilters: true,
          width: 100,
          formatter: ({ row }: any) => {
            const value = row.fileSize;

            if (value) {
              return Number(value / 1024 / 1024).toFixed(2) + "MB";
            } else {
              return null;
            }
          },
        },
        {
          title: $t("file.url", "url地址"),
          field: "url",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("file.filePath", "文件路径"),
          field: "filePath",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("table.status", "状态"),
          field: "status",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          isFilters: true,
          width: 200,
        },
        {
          title: $t("system.action", "操作"),
          cType: "action",
          fixed: "right",
          width: 200,
        },
      ],
      actions: [
        {
          operation: "detail",
          click({ row }: any) {
            return Service.file.detail({ id: row.id }).then((res: any) => {
              unref(pageRef).handleOpen({
                type: "detail",
                data: res.data,
              });
            });
          },
        },
        {
          operation: "remove",
          permission: ["file:remove"],
          click({ row }: any) {
            return Service.file.remove([row.id]).then((res: any) => {
              removeSuccess(res);
            });
          },
        },
      ],
      dialogConfig: {
        width: "1000px",
        formConfig: {
          formParams: [
            {
              label: $t("file.fileName", "文件名称"),
              prop: "fileName",
              type: "input",
              span: 6,
            },
            {
              label: $t("file.url", "url地址"),
              prop: "url",
              type: "input",
              span: 6,
            },
            {
              label: $t("file.fileType", "文件类型"),
              prop: "fileType",
              type: "input",
              span: 6,
            },
            {
              label: $t("file.filePath", "文件路径"),
              prop: "filePath",
              type: "input",
              span: 6,
            },
            {
              label: $t("file.fileSize", "文件大小"),
              prop: "fileSize",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.status", "状态"),
              prop: "status",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.remark", "备注"),
              prop: "remark",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.creator", "创建人"),
              prop: "creator",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.updater", "更新人"),
              prop: "updater",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.createTime", "创建时间"),
              prop: "createTime",
              type: "input",
              span: 6,
            },
            {
              label: $t("table.updateTime", "更新时间"),
              prop: "updateTime",
              type: "input",
              span: 6,
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.file
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res);
            });
        },
      },
      data: unref(list),
    },
    {
      title: $t("file.file view", "文件视图"),
      slot: "fileView",
    },
  ],
});
//获取文件夹树
function getList() {
  Service.file.list({ fileType: "folder" }).then((res: any) => {
    tree.value = handleTree(res.data);
    queryFile();
  });
}
//查询文件详情
function queryFile() {
  Service.file
    .fileDetailList({ parentId: unref(nodeInfo).id })
    .then((res: any) => {
      list.value.splice(0);
      nextTick(() => {
        list.value.push(...res.data);
      });
    });
}
//左侧树节点触发
function nodeClick(data: any) {
  nodeInfo.value = data;
  queryFile();
}
getList();
</script>
<style lang="scss" scoped></style>
