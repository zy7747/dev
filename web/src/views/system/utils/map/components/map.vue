<!--  -->
<template>
  <div id="container"></div>
</template>

<script lang="ts" setup>
import AMapLoader from "@amap/amap-jsapi-loader";

window._AMapSecurityConfig = {
  securityJsCode: "df30cb1d05f5a3b1a18bdba5ea6162bb",
};

const cityInfo = reactive({
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

function initMap() {
  AMapLoader.load({
    key: "310037c5935efb07c9749c207cd05866", // 申请好的Web端开发者Key，首次调用 load 时必填
    version: "2.0",
  })
    .then((AMap) => {
      //获取当前定位信息
      new AMap.plugin("AMap.Geolocation", function () {
        var geolocation = new AMap.Geolocation({
          // 是否使用高精度定位，默认：true
          enableHighAccuracy: true,
          // 设置定位超时时间，默认：无穷大
          timeout: 10000,
          needAddress: true,
          addressComponent: true,
          roads: true,
          crosses: true,
        });

        geolocation.getCurrentPosition();
        AMap.Event.addListener(geolocation, "complete", onComplete);

        function onComplete(data: any) {
          // data是具体的定位信息
          const position = data.position;

          cityInfo.lng = `${position.lng}`;
          cityInfo.lat = `${position.lat}`;
          cityInfo.formattedAddress = data.formattedAddress;
          cityInfo.addressComponent = data.addressComponent;

          //初始化地图
          const map = new AMap.Map("container", {
            viewMode: "3D", //是否为3D地图模式
            zoom: 15,
            pitch: 30,
            terrain: true,
            showIndoorMap: true,
            wallColor: "#fb8c55",
            // skyColor: "#ffffff",//天空颜色
            mapStyle: "amap://styles/fresh", //设置地图的显示样式
            center: [position.lng, position.lat], //地图中心点
          });

          setController(AMap, map);

          getWeather(AMap, data.addressComponent.adcode);

          setMarker(AMap, map, position.lng, position.lat);
        }
      });
    })
    .catch((e) => {
      console.log(e);
    });
}

//定位获取天气
function getWeather(AMap: any, adcode: any) {
  //加载天气查询插件
  AMap.plugin("AMap.Weather", function () {
    //创建天气查询实例
    var weather = new AMap.Weather();
    //执行实时天气信息查询
    weather.getLive(adcode, function (err: any, data: any) {
      console.log("天气", err, data);
      cityInfo.weather = data.weather;
      cityInfo.windDirection = data.windDirection;
      cityInfo.temperature = data.temperature;
      cityInfo.windPower = data.windPower;
      cityInfo.humidity = data.humidity;
    });
  });
}

function setMarker(AMap: any, map: any, lng: number, lat: number) {
  //创建一个 Marker 实例：
  const marker = new AMap.Marker({
    position: new AMap.LngLat(lng, lat), //经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
    title: "当前位置",
  });
  //将创建的点标记添加到已有的地图实例：
  map.add(marker);
}

function setController(AMap: any, map: any) {
  map.plugin(["AMap.ControlBar"], function () {
    //组合了旋转、倾斜、复位、缩放在内的地图控件，在3D地图模式下会显示（自V1.4.0版本新增）
    map.addControl(
      new AMap.ControlBar({
        position: "LT",
      })
    );
  });
  //地图比例尺插件
  map.plugin(["AMap.Scale"], function () {
    map.addControl(
      new AMap.Scale({
        position: "LB",
      })
    );
  });
  //地图工具条插件，可以用来控制地图的缩放和平移
  map.plugin(["AMap.ToolBar"], function () {
    map.addControl(
      new AMap.ToolBar({
        position: "RT",
      })
    );
  });
  //地图类型切换插件，用来切换固定的几个常用图层
  map.plugin(["AMap.MapType"], function () {
    map.addControl(
      new AMap.MapType({
        position: {
          right: "10px",
          bottom: "110px",
        },
      })
    );
  });
}

initMap();

defineExpose({
  cityInfo,
});
</script>

<style lang="scss" scoped>
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 750px;
}
</style>
