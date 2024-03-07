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
            :icon="CircleCloseFilled"
            link
            type="info"
            @click="clearRowEvent"
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
        type="primary"
        :icon="Edit"
        @click="item.click(row)"
      >
        编辑
      </el-button>

      <el-button
        type="warning"
        :icon="Share"
        v-else-if="item.type === 'detail'"
        @click="item.click(row)"
        link
      >
        详情
      </el-button>

      <el-popconfirm
        v-else-if="item.type === 'remove'"
        title="是否删除数据?"
        @confirm="item.click(row)"
      >
        <template #reference>
          <el-button link :icon="Delete" type="danger">删除</el-button>
        </template>
      </el-popconfirm>

      <el-button v-else @click="item.click(row)" :icon="SwitchFilled" link>
        {{ item.text }}
      </el-button>
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

const prop: any = defineProps({
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

//是否在编辑状态
const hasActiveEditRow = (row: any) => {
  const $grid = prop.xGrid;
  if ($grid) {
    return $grid.isEditByRow(row);
  }
  return false;
};

//编辑
const editRowEvent = (row: any) => {
  const $grid = prop.xGrid;
  if ($grid) {
    $grid.setEditRow(row);
  }
};

//取消
const clearRowEvent = () => {
  const $grid = prop.xGrid;
  if ($grid) {
    $grid.clearEdit();
  }
};

//保存
const saveRowEvent = async () => {
  const $grid = prop.xGrid;
  if ($grid) {
    await $grid.clearEdit();
  }
};

//删除
const removeRowEvent = async (row: any) => {
  const $grid = prop.xGrid;
  if ($grid) {
    await $grid.remove(row);
  }
};
</script>

<style lang="scss" scoped></style>
