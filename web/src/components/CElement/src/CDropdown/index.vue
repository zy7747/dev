<!--  -->
<template>
  <el-dropdown @command="handleCommand">
    <el-button type="primary" :size="size" v-if="title">
      {{ title }}
    </el-button>
    <div class="cursor-pointer">
      <slot name="title"></slot>
    </div>

    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :command="item.value" v-for="item in options">
          <svg-icon :name="item.icon" class="icon" v-if="item.icon" />
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script lang="ts" setup>
const emit = defineEmits(["command"]);

defineProps({
  title: {
    text: "下拉选文本",
    type: [String] as any,
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

function handleCommand(val: string | number | object) {
  const obj = unref(options).find((item: any) => item.value === val);
  value.value = val;
  emit("command", obj);
}
</script>

<style lang="scss" scoped>
.icon {
  margin-right: 5px;
}
</style>
