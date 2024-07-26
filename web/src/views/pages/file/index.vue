<!-- 文件 file -->
<template>
  <el-container>
    <el-aside width="200">
      <Collapse title="文件列表">
        <template #content>
          <Tree :data="tree" @node-click="nodeClick" />
        </template>
      </Collapse>
    </el-aside>
    <el-main>
      <c-page ref="pageRef" :pageOption="pageOption" :pageData="pageData">
        <template #title>
          <Breadcrumb
            @goBack="goBack"
            @first="firstMenu"
            @pathChange="folderPathChange"
            :list="folderPath"
          />
        </template>

        <template #fileView>
          <View @nodeClick="nodeClick" :data="list" />
        </template>
      </c-page>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
defineOptions({
  name: "File",
});
import View from "./components/View.vue";
import Tree from "./components/Tree.vue";
import { handleTree } from "@/utils/formatData";
import Breadcrumb from "@/components/Breadcrumb/index.vue";

const fileUrl = import.meta.env.VITE_APP_FILE_URL;

const pageData: any = reactive({
  queryData: {},
  editData: {},
});

const nodeInfo: any = ref({
  id: null,
});
const list: any = ref([]);
const tree: any = ref([]);
const treeList: any = ref([]);
const folderPath: any = ref([]);

const { pageOption, pageRef, ids, query } = usePage({
  createLoad: true,
  formConfig: {
    formParams: [
      {
        label: $t("file.fileName", "文件名称"),
        prop: "fileName",
        type: "input",
      },
      {
        label: $t("file.fileType", "文件类型"),
        prop: "fileType",
        type: "input",
      },
      {
        label: $t("table.status", "状态"),
        prop: "status",
        type: "select",
        options: Dict("is_active"),
      },
    ],
  },
  actions: [
    {
      operation: "uploadFile",
      api(files: any) {
        files.append("parentId", unref(nodeInfo).id);
        files.append("path", unref(nodeInfo).filePath);
        return Service.file.uploadFile(files).then((res: any) => {
          importSuccess(res, pageRef);
        });
      },
    },
  ],
  tableConfig: [
    {
      title: $t("file.file list", "文件列表"),
      tools: [
        {
          operation: "add",
          permission: ["file:add"],
          click() {
            unref(pageRef).handleOpen({
              type: "add",
              data: {
                fileType: "folder",
                status: "1",
                parentId: unref(nodeInfo).id,
              },
            });
          },
        },
        {
          operation: "remove",
          permission: ["file:remove"],
          click() {
            return Service.file.remove(ids()).then((res: any) => {
              removeSuccess(res, pageRef);
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
        { type: "checkbox", fixed: "left" },
        {
          type: "seq",
          fixed: "left",
        },
        {
          title: $t("file.fileName", "文件名称"),
          field: "fileName",
          cType: "link",
          on: {
            click({ row }: any) {
              openFile(row);
            },
          },
          isFilters: true,
          width: 150,
        },
        {
          title: $t("file.fileType", "文件类型"),
          field: "fileType",
          isFilters: true,
          width: 120,
        },
        {
          title: $t("file.fileSize", "文件大小"),
          field: "fileSize",
          sortable: true,
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
        },
        {
          title: $t("table.creator", "创建人"),
          field: "creator",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.updater", "更新人"),
          field: "updater",
          translate: "user",
          isFilters: true,
        },
        {
          title: $t("table.createTime", "创建时间"),
          field: "createTime",
          sortable: true,
          width: 150,
        },
        {
          title: $t("table.updateTime", "更新时间"),
          field: "updateTime",
          sortable: true,
          width: 150,
        },
        {
          cType: "action",
          fixed: "right",
        },
      ],
      actions: [
        {
          operation: "edit",
          permission: ["file:edit"],
          click({ row }: any) {
            Service.file.detail({ id: row.id }).then((res: any) => {
              unref(pageRef).handleOpen({
                type: "edit",
                data: res.data,
              });
            });
          },
        },
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
              removeSuccess(res, pageRef);
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
            },
            {
              label: $t("file.parentId", "父节点"),
              prop: "parentId",
              type: "treeSelect",
              params: {
                "check-strictly": true,
              },
              options: () => tree.value,
            },
            {
              label: $t("file.fileType", "文件类型"),
              prop: "fileType",
              type: "input",
              params: {
                disabled: true,
              },
            },
            {
              label: $t("file.filePath", "文件路径"),
              prop: "filePath",
              type: "input",
              params: {
                disabled: true,
              },
            },
            {
              label: $t("table.status", "状态"),
              prop: "status",
              type: "select",
              options: Dict("is_active"),
              params: {
                disabled: true,
              },
            },
            {
              label: $t("table.remark", "备注"),
              prop: "remark",
              type: "input",
            },
          ],
        },
        //提交
        handleConfirm() {
          return Service.file
            .save(unref(pageData).editData)
            .then((res: any) => {
              submitSuccess(res, pageRef);
            });
        },
      },
      list: () => queryFile(),
    },
    {
      title: $t("file.file view", "文件视图"),
      slot: "fileView",
      slotQuery: () => queryFile(),
    },
  ],
});
//获取文件夹树
function getList() {
  Service.file.list({ fileType: "folder" }).then((res: any) => {
    const data = res.data.map((item: any) => {
      return { ...item, label: item.fileName, value: item.id };
    });
    treeList.value = data;
    tree.value = handleTree(data);
    query();
  });
}
//查询文件详情
function queryFile() {
  return Service.file
    .fileDetailList({ parentId: unref(nodeInfo).id, ...pageData.queryData })
    .then((res: any) => {
      list.value.splice(0);
      nextTick(() => {
        list.value.push(...res.data);
      });

      return res;
    });
}
//左侧树节点触发
function nodeClick(data: any) {
  nodeInfo.value = data;

  //向上查找
  if (data) {
    var pathList: any = [];
    getParentId(data, pathList);
    folderPath.value = pathList.map((item: any) => {
      return { ...item, name: item.fileName };
    });
  }

  if (unref(pageRef).getActive() === 1) {
    queryFile();
  } else {
    query();
  }
}
//向上查找
function getParentId(data: any, pathList: any) {
  pathList.unshift(data);
  const hasPar = treeList.value.find((i: any) => {
    return i.id === data.parentId;
  });

  if (hasPar) {
    getParentId(hasPar, pathList);
  }
}
//打开文件
function openFile(item: any) {
  if (item.fileType === "folder") {
    nodeClick(item);
  } else if (
    item.fileType === "jpg" ||
    item.fileType === "jpeg" ||
    item.fileType === "png" ||
    item.fileType === "gif"
  ) {
    window.open(fileUrl + item.url);
  } else if (item.fileType === "pdf") {
  } else if (item.fileType === "xls" || item.fileType === "xlsx") {
  } else if (item.fileType === "docx") {
  } else if (item.fileType === "txt") {
  } else if (item.fileType === "mp3") {
  } else if (item.fileType === "mp4") {
    window.open(fileUrl + item.url);
  } else {
  }
}
//返回
function goBack() {
  if (unref(folderPath).length) {
    folderPath.value.pop();

    openFile(unref(folderPath)[unref(folderPath).length - 1]);
  } else {
    firstMenu();
  }
}
//路径变更
function folderPathChange(item: any) {
  nodeClick(item);
}
//根目录
function firstMenu() {
  nodeClick({
    id: null,
  });
}

getList();
</script>

<style lang="scss" scoped>
.el-main {
  padding: 0 20px;
}
</style>
