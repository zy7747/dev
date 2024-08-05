<!--  -->
<template>
  <div id="map-main" ref="mapMainRef"></div>
</template>

<script lang="ts" setup>
import ECharts from "@/plugins/echarts";

const mapMainRef = ref();

function createChart(params: any) {
  console.log(90, params);

  var myChart = ECharts.init(mapMainRef.value);

  myChart.setOption({
    title: {
      text: "用户分布图",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    geo: {
      map: "china", //必须写
      zoom: 1.25, //地图缩放比例
      roam: "move", //可拖拽
      label: {
        show: true,
        color: "#fff",
        position: "inside",
        distance: 0,
        fontSize: 10,
        // rotate: 45,
      },
      // 所有地图的区域颜色
      itemStyle: {
        areaColor: "rgba(0,60,153,0.8)",
        borderColor: "#02c0ff",
      },
      //触摸样式
      emphasis: {
        itemStyle: {
          areaColor: "rgba(0,60,153,0.5)",
          shadowColor: "rgba(0,0,0,0.8)",
          shadowBlur: 5,
          shadowOffsetY: 5,
        },
      },
    },
    visualMap: {
      min: 0,
      max: 1000,
      text: ["高", "低"],
      calculable: true,
    },
    series: [
      {
        type: "scatter",
        coordinateSystem: "geo",
        data: [
          { name: "江苏省", value: [120.15, 31.99, 9] }, // 值为，经纬度，数据
          { name: "湖北省", value: [111, 31.89, 15477] },
          { name: "四川省", value: [102.15, 31.89, 31686] },
          { name: "浙江省", value: [120.15, 29.89, 6992] },
          { name: "山东省", value: [118.15, 36.9, 44045] },
        ],
        SymbolSize: 4,
      },
    ],
  });
}

defineExpose({
  createChart,
});
</script>

<style lang="scss" scoped>
#map-main {
  width: 100%;
  height: 550px;
}
</style>
