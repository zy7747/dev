<!--  -->
<template>
  <el-tabs v-model="value" type="card" closable>
    <el-tab-pane v-for="item in tabs" :key="item.name" :name="item.name">
      <template #label>
        <svg-icon :name="`app-${item.icon}`" class="icon" />{{ item.title }}
      </template>
    </el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
const value = ref("2");

const tabs: any = ref([
  {
    title: "Tab 1",
    name: "1",
    content: "Tab 1 content",
    icon: "哔哩哔哩",
  },
  {
    title: "Tab 2",
    name: "2",
    content: "Tab 2 content",
    icon: "哔哩哔哩",
  },
]);
</script>

<style lang="scss" scoped></style>
