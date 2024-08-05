<!--  -->
<template>
  <Card>
    <template #title>
      <el-tag type="primary"> 在线人数分布图 </el-tag>
    </template>
    <template #tools>
      <el-tag type="primary"> 中国 </el-tag>
    </template>
    <template #content>
      <UserMap ref="UserMapRef" />
    </template>
  </Card>
</template>

<script lang="ts" setup>
import UserMap from "../components/UserMap.vue";

const prop = defineProps({
  countObj: {
    text: "统计对象",
    type: [Object] as any,
    default: () => {
      return {};
    },
  },
});

const UserMapRef = ref();

watch(
  () => prop.countObj,
  (val) => {
    if (val) {
      nextTick(() => {
        unref(UserMapRef).createChart(val);
      });
    }
  }
);
</script>

<style lang="scss" scoped></style>
