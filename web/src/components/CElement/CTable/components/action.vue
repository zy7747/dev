<!-- Action -->
<template>
  <template v-for="item in actions">
    <template v-if="item.type === 'operate'">
      <template v-if="hasActiveEditRow(row)">
        <c-button
          link
          text="保存"
          type="primary"
          :icon="CircleCheckFilled"
          @click="saveRowEvent()"
        />

        <c-button
          link
          text="取消"
          type="info"
          :icon="CircleCloseFilled"
          @click="clearRowEvent()"
        />
      </template>

      <template v-else>
        <c-button
          link
          text="编辑"
          type="primary"
          :icon="Edit"
          @click="editRowEvent(row)"
        />

        <el-popconfirm title="是否删除数据?" @confirm="removeRowEvent(row)">
          <template #reference>
            <c-button text="删除" :icon="Delete" type="danger" link />
          </template>
        </el-popconfirm>
      </template>
    </template>

    <c-button
      v-else-if="item.type === 'edit'"
      link
      text="编辑"
      v-bind="item"
      type="primary"
      :icon="Edit"
      @click="item.click({ row })"
    />

    <c-button
      v-else-if="item.type === 'detail'"
      link
      text="详情"
      v-bind="item"
      type="warning"
      :icon="Share"
      @click="item.click({ row })"
    />

    <el-popconfirm
      v-else-if="item.type === 'remove'"
      title="是否删除数据?"
      @confirm="item.click({ row })"
    >
      <template #reference>
        <c-button link v-bind="item" :icon="Delete" type="danger" text="删除" />
      </template>
    </el-popconfirm>

    <c-button
      v-else
      v-bind="item"
      @click="item.click({ row })"
      :icon="SwitchFilled"
      link
      :text="item.text"
    />
  </template>
</template>

<script lang="ts" setup>
import {
  Edit,
  Delete,
  Share,
  SwitchFilled,
  CircleCloseFilled,
  CircleCheckFilled,
} from "@element-plus/icons-vue";

const { xGrid } = defineProps({
  row: {
    text: "行信息",
    type: [Object],
    default: () => {
      return {};
    },
  },
  actions: {
    text: "操作栏",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
  xGrid: {
    text: "表格",
    type: [Object],
  },
});

const tableData: any = defineModel();

//是否在编辑状态
const hasActiveEditRow = (row: any) => {
  const $grid = xGrid;
  if ($grid) {
    return $grid.isEditByRow(row);
  }
  return false;
};

//编辑
const editRowEvent = (row: any) => {
  const $grid = xGrid;
  if ($grid) {
    $grid.setEditRow(row);
  }
};

//取消
const clearRowEvent = () => {
  const $grid = xGrid;
  if ($grid) {
    $grid.clearEdit();
  }
};

//保存
const saveRowEvent = async () => {
  const $grid = xGrid;
  if ($grid) {
    $grid.clearEdit();
  }
};

//删除
const removeRowEvent = async (row: any) => {
  const $grid = xGrid;
  if ($grid) {
    const index = tableData.value.findIndex(
      (item: any) => item._row_index === row._row_index
    );
    if (index > -1) {
      tableData.value.splice(index, 1);
    }
  }
};
</script>
