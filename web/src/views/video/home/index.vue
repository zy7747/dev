<template>
  <div class="navbar">
    <ul class="menus">
      <li
        class="menusItem"
        :class="{ active: current === item.params }"
        @click="menuClick(item)"
        v-for="(item, index) of menus"
        :key="index"
      >
        <svg-icon :name="item.icon" style="height: 1rem" />
        <span style="margin-left: 0.2rem">{{ item.label }}</span>
      </li>
    </ul>
  </div>
  <RouterView />
</template>

<script lang="ts" setup>
defineOptions({
  name: "VideoIndex",
});
const router = useRouter();
const route = useRoute();
const current = ref<string>(route.path);

const menus: any = computed(() => {
  return [{ label: "首页", value: "home", params: "/videoIndex/home" }].concat(
    Dict("video_category")
  );
});

function menuClick(menu: any) {
  current.value = menu.params;

  router.push({ path: current.value });
}
</script>

<style lang="scss" scoped>
.navbar {
  margin-top: 5px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .menus {
    display: flex;
    font-size: 1rem;
    .menusItem {
      display: flex;
      align-items: center;
      margin-right: 5px;
      background-color: #f6f7f8;
      padding: 3px 5px;
      border-radius: 3px;
      &:hover {
        cursor: pointer;
        color: #f3793d;
        box-sizing: border-box;
        border: 1px solid #b1b2b3;
      }
    }

    .active {
      color: #fff;
      background-color: #e3e5e7;
    }
  }
}
</style>
