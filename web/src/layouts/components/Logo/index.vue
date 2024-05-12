<template>
  <div
    class="logo flex items-center justify-center cursor-pointer"
    :style="{
      width: theme.asideWidth ? theme.asideWidth + 'px' : 'auto',
      height: theme.height + 'px',
    }"
  >
    <div class="logo">
      <img class="logoPng" src="@/assets/images/logo/logo.png" alt="" />
    </div>
    <div class="appName" :title="theme.appName" v-if="hasTitle || collapse">
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

<style lang="scss" scoped>
.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  width: 30px;
  .logoPng {
    width: 100%;
    object-fit: cover;
  }
}

.appName {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  min-width: 120px;
  font-weight: 600;
  font-size: 18px;
  color: #00aeec;
  text-align: center;
  line-height: 30px;
  text-shadow: -2px -2px 0px rgb(233, 230, 230); /* 描边效果 */
  background-size: 100% 30px !important;
  background-position: 0;
  font-family: "Microsoft Yahei";
  background: url("@/assets/images/button/输入框.png") no-repeat center;
  &:hover {
    color: #76d7fa;
  }
}
</style>
