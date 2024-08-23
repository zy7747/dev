<!--  -->
<template>
  <Card padding="0">
    <template #title>
      <div>
        <el-tag type="primary"> 中国 </el-tag>
        <el-tag class="btn" type="primary">
          {{ cityInfo.formattedAddress }}
        </el-tag>
        <el-tag class="btn" type="primary">
          天气: {{ cityInfo.weather }}
        </el-tag>
        <el-tag class="btn" type="primary">
          {{ cityInfo.windDirection }}风
        </el-tag>
        <el-tag class="btn" type="primary">
          气温: {{ cityInfo.temperature }}°c
        </el-tag>
        <el-tag class="btn" type="primary">
          风力: {{ cityInfo.windPower }}级
        </el-tag>
      </div>
    </template>
    <template #tools>
      <el-tag type="primary">
        经纬度（{{ cityInfo.lng }},{{ cityInfo.lat }}）</el-tag
      >
    </template>
    <template #content>
      <div class="map">
        <Map ref="mapRef" />
      </div>
    </template>
  </Card>
</template>

<script lang="ts" setup>
import Map from "./components/map.vue";
const mapRef = ref();

const cityInfo = ref({
  lng: "", //精度
  lat: "", //维度
  formattedAddress: "", //精确地址
  addressComponent: {}, //地址信息
  weather: "", //天气
  windDirection: "", //风向
  temperature: "", //实时气温，单位：摄氏度
  windPower: "", //风力，风力编码对应风力级别，单位：级
  humidity: "", //空气湿度（百分比）
});

onMounted(() => {
  watch(
    unref(mapRef).cityInfo,
    (value: any) => {
      cityInfo.value = value;
    },
    { immediate: true }
  );
});
</script>

<style lang="scss" scoped>
.map {
  height: 750px;
}
</style>
