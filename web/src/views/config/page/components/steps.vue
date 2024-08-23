<!--  -->
<template>
  <el-steps :space="100" simple finish-status="success" :active="active">
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
::v-deep(.step):hover {
  cursor: pointer;
}

::v-deep(.el-step__title):hover {
  color: #409eff !important;
}

.el-steps {
  border-bottom: 1px solid var(--el-border-color-darker);
}
</style>

<style lang="scss">
.is-process {
  color: #409eff !important;
}
</style>
