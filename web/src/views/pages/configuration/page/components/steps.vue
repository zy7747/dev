<!--  -->
<template>
  <el-steps :space="100" simple :active="active">
    <el-step
      class="step"
      v-for="(item, index) in steps"
      :title="item.title"
      :icon="item.icon"
      @click="stepChange(index)"
    />
  </el-steps>
</template>

<script lang="ts" setup>
defineProps({
  steps: {
    text: "步骤list",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});

const active: any = defineModel();

const stepChange = (index: Number) => {
  active.value = index;
};
</script>

<style lang="scss" scoped>
.step:hover {
  cursor: pointer;
}
</style>
