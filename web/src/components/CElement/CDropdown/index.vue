<!--  -->
<template>
  <el-dropdown @command="handleCommand">
    <el-button type="primary" :size="size">
      {{ text }}
    </el-button>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :command="item.value" v-for="item in options">
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script lang="ts" setup>
defineProps({
  text: {
    text: "下拉选按钮名称",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  size: {
    text: "单选框大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
});
const value: any = defineModel();
const options: any = defineModel("options");

function handleCommand(command: string | number | object) {
  value.value = command;
}
</script>

<style lang="scss" scoped></style>
