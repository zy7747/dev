<!-- select -->
<template>
  <el-select-v2
    v-model="value"
    :options="iconData"
    v-bind="$attrs"
    :placeholder="placeholder"
    :filterable="filterable"
    :clearable="clearable"
    :size="size"
    :style="`width:${width};`"
  >
    <template #default="{ item }">
      <div class="flex items-center">
        <svg-icon :name="item.value" class="icon" />
        <span>{{ item.label }}</span>
      </div>
    </template>

    <template #prefix>
      <svg-icon :name="value" class="icon" />
    </template>
  </el-select-v2>
</template>

<script lang="ts" setup>
import requireIcons from "./requireIcons";

const prop = defineProps({
  size: {
    text: "下拉框大小",
    type: [String] as any,
    default: () => {
      return "default";
    },
  },
  width: {
    type: [String],
    default: "100%",
  },
  options: {
    type: [String],
    default: "flag",
  },
  clearable: {
    text: "可清除",
    type: [Boolean],
    default: () => {
      return true;
    },
  },
  filterable: {
    text: "筛选",
    type: [Boolean],
    default: () => {
      return true;
    },
  },
  placeholder: {
    text: "提示语句",
    type: [String],
    default: () => {
      return "请选择";
    },
  },
});

const value: any = defineModel();
const iconData: any = ref([]);

function render() {
  iconData.value = requireIcons(prop.options).map((item: string) => {
    return { label: item, value: item };
  });
}

render();
</script>

<style lang="scss" scoped>
.icon {
  margin-right: 15px;
}
</style>
