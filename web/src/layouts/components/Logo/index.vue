<template>
  <div
    class="flex items-center justify-center cursor-pointer"
    :style="{
      width: theme.asideWidth ? theme.asideWidth + 'px' : 'auto',
      height: theme.height + 'px',
    }"
  >
    <div class="logo">
      <img
        class="logoPng"
        src="https://cos-1307762674.cos.ap-shanghai.myqcloud.com/picture/logo/logo.png"
        alt=""
      />
    </div>
    <div
      class="app-name"
      :appName="theme.appName"
      :title="theme.appName"
      v-if="hasTitle || collapse"
    >
      {{ theme.appName }}
    </div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  theme: {
    text: "主题",
    type: [Object],
    default: () => {
      return {};
    },
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
