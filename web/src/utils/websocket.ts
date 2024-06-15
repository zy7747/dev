import { useUserStore } from "@/store/user";
const wsUrl = import.meta.env.VITE_APP_WEB_WS;

//传入需要执行的函数
export function getWebsocket(fun: any) {
  let websocket: any = null;

  const userStore = useUserStore();

  // 获取登录用户
  const userId = unref(userStore.userId);

  //判断当前浏览器是否支持WebSocket
  if ("WebSocket" in window) {
    websocket = new WebSocket(wsUrl + "/websocket/" + userId);
  } else {
    alert("当前浏览器 不支持 websocket");
  }

  //连接发生错误的回调方法
  websocket.onerror = function () {
    setTimeout(() => {
      console.log("WebSocket连接发生错误,正在尝试重连.....");
      getWebsocket(fun);
    }, 3000);
  };

  //连接成功建立的回调方法
  websocket.onopen = function () {
    console.log("WebSocket连接成功");
    fun();
  };

  //接收到消息的回调方法
  websocket.onmessage = function (event: any) {
    console.log("收到消息", event);
    fun();
  };
}
