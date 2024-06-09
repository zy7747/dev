<!-- Action -->
<template>
  <template v-for="item in actions">
    <template v-if="item.show ? item.show({ row }) : true">
      <template v-if="item.operation === 'operate'">
        <template v-if="hasActiveEditRow(row)">
          <c-button
            link
            :text="$t('system.save')"
            type="primary"
            :icon="CircleCheckFilled"
            @click="saveRowEvent(row)"
          />

          <c-button
            link
            :text="$t('system.cancel')"
            type="info"
            :icon="CircleCloseFilled"
            @click="clearRowEvent(row)"
          />
        </template>

        <template v-else>
          <c-button
            link
            :text="$t('system.edit')"
            type="primary"
            :icon="Edit"
            @click="editRowEvent(row)"
          />

          <el-popconfirm
            title="是否删除数据?"
            @confirm="removeRowEvent(row, item.remove)"
          >
            <template #reference>
              <c-button
                :text="$t('system.delete')"
                :icon="Delete"
                type="danger"
                link
              />
            </template>
          </el-popconfirm>
        </template>
      </template>

      <c-button
        v-else-if="item.operation === 'add'"
        link
        :text="$t('system.add')"
        v-bind="item"
        type="primary"
        :icon="Plus"
        @click="item.click({ row })"
      />

      <c-button
        v-else-if="item.operation === 'edit'"
        link
        :text="$t('system.edit')"
        v-bind="item"
        type="primary"
        :icon="Edit"
        @handleClick="item.click({ row })"
      />

      <c-button
        v-else-if="item.operation === 'detail'"
        link
        :text="$t('system.detail')"
        v-bind="item"
        type="warning"
        :icon="Share"
        @handleClick="item.click({ row })"
      />

      <el-popconfirm
        v-else-if="item.operation === 'remove'"
        title="是否删除数据?"
        @confirm="item.click({ row })"
      >
        <template #reference>
          <c-button
            link
            v-bind="item"
            :icon="Delete"
            type="danger"
            :text="$t('system.delete')"
          />
        </template>
      </el-popconfirm>

      <c-button
        v-else
        v-bind="item"
        @handleClick="item.click({ row })"
        :icon="SwitchFilled"
        link
        :text="item.text"
      />
    </template>
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
  Plus,
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

  const isEdit = unref(tableData).every((item: any) => {
    return !hasActiveEditRow(item);
  });

  if (!isEdit) {
    ElMessage({
      message: "请先保存数据再编辑",
      type: "warning",
      showClose: true,
      grouping: true,
    });
  } else {
    if ($grid) {
      $grid.setEditRow(row);
    }
  }
};

//取消
const clearRowEvent = (row: any) => {
  const $grid = xGrid;
  if ($grid) {
    if (row.isAdd) {
      removeRowEvent(row);
    }
    $grid.revertData(row);
    $grid.clearEdit();
  }
};

//保存
const saveRowEvent = async (row: any) => {
  const $grid = xGrid;
  if ($grid) {
    if (row.isAdd) {
      delete row.isAdd;
    }
    $grid.clearEdit();
  }
};

//删除
const removeRowEvent = async (row: any, remove?: any) => {
  if (row.id) {
    remove(row);
    return;
  }

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
