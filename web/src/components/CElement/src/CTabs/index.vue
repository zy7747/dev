<!--  -->
<template>
  <div class="CTabs">
    <div class="action">
      <el-button
        :icon="show ? ArrowDownBold : ArrowUpBold"
        size="small"
        circle
        @click="show = !show"
      />
    </div>

    <el-tabs v-bind="$attrs" v-model="active">
      <el-tab-pane v-for="(item, index) in options" :key="index" :name="index">
        <template #label>
          <span>{{ item.title }}</span>
        </template>
        <el-collapse-transition class="collapse" v-show="show">
          <div class="content">
            <slot name="content" :item="item" :index="index" />
          </div>
        </el-collapse-transition>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import { ArrowDownBold, ArrowUpBold } from "@element-plus/icons-vue";

const show = ref(true);
const active: any = defineModel();

defineProps({
  options: {
    text: "list",
    type: [Array] as any,
    default: () => {
      return [];
    },
  },
});
</script>

<style lang="scss">
.CTabs {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
  border: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px -1px rgba(0, 0, 0, 0.1);
  .action {
    position: absolute;
    right: 18px;
    top: 8px;
    z-index: 1;
  }
}

.el-tabs__content {
  padding: 0 !important;
  background-color: transparent !important;
}

.collapse {
  padding: 0 15px 15px;
}
</style>
