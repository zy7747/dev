<!--  -->
<template>
  <el-container>
    <el-header height="45px">
      <div class="title">
        {{ title }}
        <slot name="title" />
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
      <el-main v-show="show" :style="{ padding }">
        <slot name="content" />
      </el-main>
    </el-collapse-transition>
  </el-container>
</template>

<script lang="ts" setup>
import { ArrowDownBold, ArrowUpBold } from "@element-plus/icons-vue";

const prop = defineProps({
  title: {
    text: "标题",
    type: [String, Function],
    default: "",
  },
  width: {
    text: "width",
    type: [String],
    default: "100%",
  },
  padding: {
    text: "padding",
    type: [String],
    default: "10px",
  },
  isShow: {
    text: "是否默认展开",
    type: [Boolean, Function],
    default: true,
  },
});

const show: any = ref(true);

show.value = prop.isShow;
</script>

<style lang="scss" scoped>
.el-container {
  width: 100%;
  overflow: hidden;
  margin: 10px 0;
  border-radius: 10px;
  border: 1px solid var(--el-border-color-darker);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px -1px rgba(0, 0, 0, 0.1);
  transition: all 0.7s;
  &:hover {
    box-shadow: -1px -2px 8px 2px rgba(0, 0, 0, 0.1),
      0 1px 2px -1px rgba(0, 0, 0, 0.1);
  }
}

.title {
  font-weight: 600;
  font-size: 14px;
}
.el-header {
  align-items: center;
  justify-content: space-between;
  display: flex;
  border-bottom: 1px solid var(--el-border-color-darker);
  background-color: var(--el-fill-color-light);
}

.el-main {
  background-color: var(--el-bg-color);
}

.tools {
  align-items: center;
  justify-content: space-around;
  display: flex;
}
</style>
