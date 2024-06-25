//下载封装
function download(data: any, fileName: any, mineType: any) {
  // 创建 blob
  let blob = new Blob([data], { type: mineType });
  // 创建 href 超链接，点击进行下载
  window.URL = window.URL || window.webkitURL;
  let href = URL.createObjectURL(blob);
  let downA = document.createElement("a");
  downA.href = href;
  downA.download = fileName;
  downA.click();
  // 销毁超连接
  window.URL.revokeObjectURL(href);
}
const downloadFun = {
  // 下载 Excel 方法
  excel(data: any, fileName: any) {
    download(
      data,
      fileName + ".xlsx",
      "application/vnd.ms-excel;charset=utf-8'"
    );
  },

  // 下载 Zip 方法
  zip(data: any, fileName: any) {
    download(data, fileName, "application/zip");
  },

  // 下载 Html 方法
  html(data: any, fileName: any) {
    download(data, fileName, "text/html");
  },

  // 下载 Markdown 方法
  markdown(data: any, fileName: any) {
    download(data, fileName, "text/markdown");
  },

  // 下载 pdf 方法
  pdf(data: any, fileName: any) {
    download(data, fileName, "application/pdf");
  },
};

export default downloadFun;
