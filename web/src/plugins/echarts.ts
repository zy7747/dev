import * as echarts from "echarts";

import chinaJSON from "./json/china.json";

echarts.registerMap("china", JSON.stringify(chinaJSON)); //注册可用的地图

export default echarts;
