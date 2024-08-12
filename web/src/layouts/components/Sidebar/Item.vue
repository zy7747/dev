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
  margin: 3px 0;
  transition: 0.2s all;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  span {
    color: #fff !important;
  }
}

.el-menu-item:hover {
  background-color: #e4e8ec !important;
  color: #fff;
  font-weight: 550;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.menu-text {
  font-weight: 550 !important;
  font-size: 15px;
}

.el-menu-item {
  height: 40px !important;
  padding: 0 !important;
  border-radius: 5px;
  // justify-content: center;
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
</style>
