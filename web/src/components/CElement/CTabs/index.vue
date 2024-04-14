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
          <slot name="content" :item="item" :index="index" />
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
  height: {
    text: "height",
    type: [String] as any,
    default: "600px",
  },
});
</script>

<style lang="scss">
.CTabs {
  position: relative;
  .action {
    position: absolute;
    right: 18px;
    top: 8px;
    z-index: 1;
  }
}

.el-tabs__content {
  padding: 0 !important;
}

.collapse {
  padding: 0 15px 15px;
}
</style>
