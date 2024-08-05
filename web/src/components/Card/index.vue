<!--  -->
<template>
  <div class="card">
    <div v-if="title || slots.title" class="card-title">
      <span v-if="title">{{ title }}</span>
      <slot name="title" />
      <slot name="tools" />
    </div>

    <div class="card-body" :style="{ padding }">
      <slot name="content" />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useSlots } from "vue";

const slots = useSlots();

defineProps({
  title: {
    text: "标题",
    type: [String, Function],
    default: "",
  },
  padding: {
    text: "padding",
    type: [String],
    default: "10px",
  },
});
</script>

<style lang="scss" scoped>
.card {
  background-color: #fff;
  margin: 10px 0;
  border-radius: 5px;
  border: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px -1px rgba(0, 0, 0, 0.1);
}

.card-title {
  display: flex;
  justify-content: space-between;
  font-weight: 600;
  font-size: 14px;
  padding: 5px;
  border-bottom: 1px solid var(--el-border-color-darker);
}
</style>
