<!--  -->
<template>
  <div class="">
    <template v-for="item in actions">
      <template v-if="item.type === 'operate'">
        <template v-if="hasActiveEditRow(row)">
          <el-button
            link
            type="primary"
            :icon="CircleCheckFilled"
            @click="saveRowEvent()"
          >
            保存
          </el-button>

          <el-button
            link
            type="info"
            :icon="CircleCloseFilled"
            @click="clearRowEvent()"
          >
            取消
          </el-button>
        </template>

        <template v-else>
          <el-button
            link
            type="primary"
            :icon="Edit"
            @click="editRowEvent(row)"
          >
            编辑
          </el-button>

          <el-popconfirm title="是否删除数据?" @confirm="removeRowEvent(row)">
            <template #reference>
              <el-button :icon="Delete" type="danger" link> 删除 </el-button>
            </template>
          </el-popconfirm>
        </template>
      </template>

      <el-button
        v-else-if="item.type === 'edit'"
        link
        v-bind="item"
        type="primary"
        :icon="Edit"
        @click="item.click({ row })"
      >
        编辑
      </el-button>

      <el-button
        v-else-if="item.type === 'detail'"
        link
        v-bind="item"
        type="warning"
        :icon="Share"
        @click="item.click({ row })"
      >
        详情
      </el-button>

      <el-popconfirm
        v-else-if="item.type === 'remove'"
        title="是否删除数据?"
        @confirm="item.click({ row })"
      >
        <template #reference>
          <c-button
            link
            v-bind="item"
            :icon="Delete"
            type="danger"
            text="删除"
          />
          >
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
  </div>
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

<style lang="scss" scoped></style>
