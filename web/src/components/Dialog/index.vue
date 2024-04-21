<!--  -->
<template>
  <div>
    <el-dialog v-model="visible" title="Tips" width="500">
      <span>This is a message</span>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
const visible = ref(true);
</script>

<style lang="scss"></style>
