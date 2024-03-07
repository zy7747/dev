<!--  -->
<template>
  <div class="">
    <template v-for="item in tools">
      <el-button
        v-if="item.type === 'add'"
        :icon="Plus"
        @click="item.click"
        type="primary"
        size="small"
      >
        新增
      </el-button>

      <el-button
        v-else-if="item.type === 'remove'"
        :icon="Delete"
        @click="item.click"
        type="danger"
        size="small"
      >
        批量删除
      </el-button>

      <el-button
        v-else-if="item.type === 'import'"
        :icon="Upload"
        @click="item.click"
        type="success"
        size="small"
      >
        导入
      </el-button>

      <el-button
        v-else-if="item.type === 'export'"
        :icon="Download"
        @click="item.click"
        color="#626aef"
        size="small"
      >
        导出
      </el-button>

      <el-button
        type="info"
        :icon="SwitchFilled"
        v-else
        size="small"
        @click="item.click"
      >
        {{ item.text }}
      </el-button>
    </template>
  </div>
</template>

<script lang="ts" setup>
import {
  Plus,
  Delete,
  Download,
  Upload,
  SwitchFilled,
} from "@element-plus/icons-vue";

defineProps({
  tools: {
    text: "工具栏",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});
</script>

<style lang="scss" scoped></style>
