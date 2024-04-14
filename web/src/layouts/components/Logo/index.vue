<template>
  <div
    class="logo flex items-center justify-center cursor-pointer"
    :style="{ width: width ? width + 'px' : 'auto', height: height + 'px' }"
  >
    <svg-icon :name="`哔哩哔哩`" class="icon" />
    <span class="appName" v-if="hasTitle || collapse" style="margin-left: 10px">
      {{ theme.appName }}
    </span>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  width: {
    text: "宽度",
    type: [Number],
  },
  theme: {
    text: "主题",
    type: [Object],
    default: () => {
      return {};
    },
  },
  height: {
    text: "宽度",
    type: [Number],
  },
  hasTitle: {
    text: "是否显示标题",
    type: [Boolean],
  },
  collapse: {
    text: "菜单伸缩开关",
    type: [Boolean],
  },
});
</script>

<style lang="scss" scoped>
.logo {
  background: transparent;
  text-align: center;
  .icon {
    width: 30px;
    height: 30px;
  }
}

.appName {
  border-radius: 5px;
  color: #000000;
  &:hover {
    background-color: #999898;
    color: #f0f0f0;
  }
}
</style>
