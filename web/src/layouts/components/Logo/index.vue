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
    <span
      class="appName"
      :title="$t('system.Data Management Center', '数据管理中心')"
      v-if="hasTitle || collapse"
    >
      {{ $t("system.Data Management Center", "数据管理中心") }}
    </span>
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
  border-radius: 5px;
  padding: 5px 15px;
  font-weight: 700;
  font-size: 18px;
  color: #fff;
  text-align: center;
  text-shadow: 1px -2px 0px black; /* 描边效果 */
  background-size: 100% 30px !important;
  background-position: 0;
  background: url("@/assets/images/button/输入框.png") no-repeat center;
  &:hover {
    color: #f0f0f0;
  }
}
</style>
