<template>
  <div
    class="logo"
    :style="{ width: width ? width + 'px' : 'auto', height: height + 'px' }"
  >
    <svg-icon :name="`app-哔哩哔哩`" class="icon" />
    <span v-if="hasTitle || collapse" style="margin-left: 10px">{{
      "应用名称"
    }}</span>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  width: {
    text: "宽度",
    type: [Number],
  },
  height: {
    text: "宽度",
    type: [Number],
  },
  hasTitle: {
    text: "是否显示标题",
    type: [Boolean],
    default: true,
  },
  collapse: {
    text: "菜单伸缩开关",
    type: [Boolean],
  },
});
</script>

<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  background: transparent;
  text-align: center;
  .icon {
    width: 30px;
    height: 30px;
  }

  .icon:hover {
    cursor: pointer;
  }
}
</style>
