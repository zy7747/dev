<!--  -->
<template>
  <el-container>
    <el-header height="45px">
      <div class="title">
        {{ title }}
      </div>

      <div class="tools">
        <slot name="tools" />
        <el-button
          :icon="show ? ArrowDownBold : ArrowUpBold"
          size="small"
          circle
          @click="show = !show"
        />
      </div>
    </el-header>
    <el-collapse-transition>
      <el-main v-show="show">
        <slot name="content" />
      </el-main>
    </el-collapse-transition>
  </el-container>
</template>

<script lang="ts" setup>
import { ArrowDownBold, ArrowUpBold } from "@element-plus/icons-vue";

defineProps({
  title: {
    text: "标题",
    type: [String, Function],
    default: "",
  },
});

const show = ref(true);
</script>

<style lang="scss" scoped>
.title {
  font-weight: 600;
  font-size: 14px;
}
.el-header {
  align-items: center;
  justify-content: space-between;
  display: flex;
  background-color: #f5f7fa;
  border-bottom: 1px solid #e1e3e7;
}
.tools {
  align-items: center;
  justify-content: space-around;
  display: flex;
}
</style>
