# HTML(超文本标记语言)❤️

HyperText Markup Language

## 1.主要结构组成😘

```html
<!-- 声明这是一个html5文档 -->
<!DOCTYPE html>
<!-- html页面标签 lang属性设置页面默认语言 -->
<html lang="en">
  <!-- 头部元素 -->
  <head>
    <!-- 编码形式 -->
    <meta charset="UTF-8" />
    <!-- 内核 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- 视口 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- 标题 -->
    <title>标题</title>
    <!-- 引入外部css样式 -->
    <link rel="stylesheet" href="./CSS(指层叠样式表).css" />
     <!-- 引入外部js -->
     <scrpit src=""></scrpit>
  </head>
  <!-- 身体元素 -->
  <body>
    <!-- 头部语义化标签 -->
    <header></header>
    <!-- 导航栏语义化标签 -->
    <nav></nav>
    <!-- 文章内容语义化标签 -->
    <article></article>
    <!-- 页脚语义化标签 -->
    <footer></footer>
    <!-- 部分语义化标签 -->
    <section></section>
      <!-- 换行符 -->
	<br/>
	<!-- 换行线 -->
	<hr/>
  </body>
</html>
```

## 2.HTML 基础页面标签🐖

```html
<h1>标题</h1>
<h6>最小的标题</h6>
<p>段落</p>
<i>斜体</i>
<b>加粗</b>
<del>删除线</del>
<a>&gt;<a>大于号<a>&lt;<a>小于号 &nbsp;空格
```

## 3.盒子模型-属性🤠

```html
<!-- 
   1. div为块级元素独占一行 
   2. class为类名属性
 -->
<div class="box"></div>
<!-- 
   1. span行内元素一行显示
   2. id属性
 -->
<span id="line"></span>
```

## 4.链接-图像-视频-音频👍

### 01.a --标签

```html
<!-- 
    1.target="\_bank" :在新窗口打开 
    2.默认值在当前页面跳转
-->
<a href="http://www.baidu.com" target="_bank">链接</a>
<!-- 
    1.href="#" 刷新页面
    2.download ="" 属性会下载链接中的文件
 -->
<a href="#" download="#">下载链接</a>
```

### 02.img --图像标签

```html
<img src="/img/pic.png" alt="代替文本" title="鼠标悬停标题" draggable="true"/>
```

### 03.audio --音频

```html
<!-- 
1.controls:如果出现该属性，则向用户显示音频控件（比如播放/暂停按钮）。
2.loop:如果出现该属性，则每当音频结束时重新开始播放。         
 -->
<audio src="./audio/欧得洋-孤单北半球.mp3" controls loop>
  这些文本信息将会被显示在那些不支持avdio的浏览器
</audio>
```

### 04.vido --视频

```html
<!-- poster 属性指定视频下载时显示的图像，或者在用户点击播放按钮前显示的图像 -->
<video src="./vido/一梦·蜃影.mp4" controls poster="./img/pic.png">
  这些文本信息将会被显示在那些不支持video的浏览器
</video>
```

### 05.source--可以选择视频或音频

```html
  <source src="horse.mp3" type="audio/mpeg">
```

## 5.table 表格🤐

```html
<!-- 表格声明 -->
<table border="1">
  <!-- 表头 -->
  <thead>
    <!-- 行 -->
    <tr>
      <!-- 单元格 -->
      <th>序号</th>
      <th>姓名</th>
      <th>年龄</th>
      <th>备注</th>
    </tr>
  </thead>
  <!-- 表体 -->
  <tbody>
    <tr>
      <td>1</td>
      <td>张三</td>
      <td>18</td>
      <!-- 合并行 -->
      <td rowspan="2"></td>
    </tr>
    <tr>
      <td>2</td>
      <td>李四</td>
      <td>19</td>
    </tr>
  </tbody>
  <!-- 表尾 -->
  <tfoot>
    <tr>
      <td>总计</td>
      <!-- 合并列 -->
      <td colspan="3"></td>
    </tr>
  </tfoot>
</table>
```

## 6.无序列表🔞

```html
<ul>
  <li>一般用来布局</li>
  <li>ul最为常用</li>
</ul>
```

## 7.表单 💕

### 01.input 表单

```html
<!-- 所有组件放在表单域中 action为表单域发送数据位置 提交切跳转-->
<form action="http://www.baidu.com">
  <!-- 用户名或输入框  -->  
 <input type="text" name="username" id="username" placeholder="请输入用户名" />
  <!-- 密码框 -->
  <input type="password" name="password" id="password" />
  <!-- disabled禁用表单,一般用于验证码 -->
  <input type="text" disabled /> <button>发送验证码</button>
  <!-- 邮箱 -->
  <input type="email" name="email" />
  <!-- 搜索框 -->
  <input type="search" name="seach" />
  <!-- 数字 -->
  <input type="number" name="number" max="5" min="1" />
  <!-- 滑块 -->
  <input type="range" name="points" min="1" max="10" />
  <!-- 电话号码 -->
  <input type="tel" />
  <!-- 提交表单按钮不会刷新页面 -->
  <input type="submit" value="提交" />
</form>
```

### 02.单选与多选按钮

单选与多选需要同一个 name 属性值

```html
<!-- 多选 -->
<input type="checkbox" name="hobby" />吃饭
<input type="checkbox" name="hobby" />睡觉
<input type="checkbox" name="hobby" />打豆豆
<!-- 单选  label标签,点击文字也会触发对应id的input-->
<label for="man">男</label><input type="radio" name="sex" id="man"/>  <input type="radio" name="sex" /> 女
```

### 03.下拉框

下拉框:提供下拉选项
1.multiple 属性允许下拉按住 Ctrl 来多选

```html
<!-- 下拉框 -->
<select name="city" id="city" multiple>
  <option value="北京">北京</option>
  <option value="上海">上海</option>
  <option value="广州">广州</option>
  <option value="深圳">深圳</option>
  <option value="南宁">南宁</option>
</select>
```

### 04.文本域

文本域:一般用来评论或者发表文章

```html
<!-- 文本域  -->
<textarea name="comment" id="comment" cols="60" rows="10"></textarea>
```

## 	8.iframe框架

用来在页面中显示其他页面：类似插槽

```html
<iframe loading="lazy" src="demo_iframe.htm" width="200" height="200"></iframe>
```

## 9.画布

```html
<canvas></canvas>   
```

## 10.SVG矢量图

```html
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="190">
  <polygon points="100,10 40,180 190,60 10,60 160,180"
  style="fill:lime;stroke:purple;stroke-width:5;fill-rule:evenodd;">
</svg>
```

