<!--  -->
<template>
  <div id="visits-main" ref="visitsMainRef"></div>
</template>

<script lang="ts" setup>
import ECharts from "@/plugins/echarts";
const visitsMainRef = ref();

function createChart(params: any) {
  var myChart = ECharts.init(visitsMainRef.value);

  const userOnlineTotal = params.userOnlineTotal;
  const userOfflineTotal = params.userTotal - params.userOnlineTotal;

  myChart.setOption({
    title: {
      text: "在线用户统计图",
      subtext: "Online User Chart",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      orient: "vertical",
      left: "left",
    },
    series: [
      {
        name: "Access From",
        type: "pie",
        radius: "50%",
        data: [
          {
            value: userOfflineTotal,
            name: "离线用户",
          },
          { value: userOnlineTotal, name: "在线用户" },
        ],
      },
    ],
  });
}

defineExpose({
  createChart,
});
</script>

<style lang="scss" scoped>
#visits-main {
  width: 100%;
  height: 240px;
}
</style>
