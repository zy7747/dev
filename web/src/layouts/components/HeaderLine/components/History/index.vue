<!--  -->
<template>
  <div class="flex flex-col items-center cursor-pointer">
    <svg-icon name="历史记录" class="icon" />
    <span style="font-size: 12px; margin-top: 3px">
      {{ $t("system.history") }}
    </span>
  </div>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped>
.icon {
  width: 25px;
  height: 25px;
}
</style>
