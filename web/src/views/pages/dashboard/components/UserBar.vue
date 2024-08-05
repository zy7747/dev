<!--  -->
<template>
  <div id="visits-main" ref="visitsMainRef"></div>
</template>

<script lang="ts" setup>
import ECharts from "@/plugins/echarts";
const visitsMainRef = ref();

function createChart() {
  var myChart = ECharts.init(visitsMainRef.value);

  myChart.setOption({
    xAxis: {
      type: "category",
      data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130],
        type: "bar",
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
