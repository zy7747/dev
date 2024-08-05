<!--  -->
<template>
  <Card>
    <template #title>
      <el-tag type="primary">
        在线人数（{{ countObj.userOnlineTotal }}人）
      </el-tag>
    </template>
    <template #tools>
      <el-tag type="primary">用户总数（{{ countObj.userTotal }}人）</el-tag>
    </template>
    <template #content>
      <Visits ref="VisitsRef" />
    </template>
  </Card>
</template>

<script lang="ts" setup>
import Visits from "../components/UserVisits.vue";

const prop = defineProps({
  countObj: {
    text: "统计对象",
    type: [Object] as any,
    default: () => {
      return {};
    },
  },
});

const VisitsRef = ref();

watch(
  () => prop.countObj,
  (val) => {
    if (val) {
      nextTick(() => {
        unref(VisitsRef).createChart(val);
      });
    }
  }
);
</script>

<style lang="scss" scoped></style>
