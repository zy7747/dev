<!--  -->
<template>
  <el-container>
    <el-header height="50px">
      <div class="title">
        {{ title }}
      </div>

      <div class="tools">
        <slot name="tools" />
        <el-button :icon="Plus" circle @click="show = !show" />
      </div>
    </el-header>
    <el-main>
      <el-collapse-transition>
        <div v-show="show">
          <slot name="content" />
        </div>
      </el-collapse-transition>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { Plus } from "@element-plus/icons-vue";

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
.el-header {
  align-items: center;
  justify-content: space-between;
  display: flex;
}
.tools {
  align-items: center;
  justify-content: space-around;
  display: flex;
}
</style>
