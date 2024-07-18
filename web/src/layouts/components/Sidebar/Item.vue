<!--  -->
<template>
  <el-sub-menu v-if="menu.children && menu.children.length" :index="menu.path">
    <template #title>
      <svg-icon :name="menu.icon" class="icon" />
      <el-text class="menu-text">{{ menu.title }}</el-text>
    </template>

    <template v-for="item in menu.children">
      <MenuItem :menu="item"></MenuItem>
    </template>
  </el-sub-menu>

  <el-menu-item
    v-else
    @click="() => menuClick(menu)"
    :persistent="false"
    :index="menu.path"
  >
    <svg-icon :name="menu.icon" class="icon" />
    <template #title>
      <el-text class="menuItem">{{ menu.title }}</el-text>
    </template>
  </el-menu-item>
</template>

<script lang="ts" setup>
defineOptions({
  name: "MenuItem",
});

defineProps({
  menu: Object as any,
});

const Router = useRouter();

function menuClick(menu: any) {
  Router.push({
    path: menu.path,
  });
}
</script>

<style lang="scss" scoped>
.icon {
  margin: 10px;
  width: 17px;
  height: 17px;
}

.el-menu-item.is-active {
  background-color: #fb8c55 !important;
  color: #fff;
  font-weight: 550;
  span {
    color: #fff !important;
  }
}

.menu-text {
  font-weight: 550 !important;
  font-size: 15px;
}

//不要箭头
// ::v-deep(.el-sub-menu__icon-arrow) {
//   display: none !important;
// }
</style>

<style lang="scss">
.el-sub-menu .el-menu {
  background-color: transparent !important;
}

.el-menu-item {
  height: 40px !important;
  padding: 0 !important;
  border-radius: 5px;
  // justify-content: center;
}
</style>
