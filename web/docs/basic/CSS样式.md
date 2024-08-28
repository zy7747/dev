# CSS(指层叠样式表)

Cascading Style Sheets

## 一 . 三种 CSS 样式书写位置

行内样式 外部样式 内部样式

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <!-- link引入外部css样式 -->
    <link rel="stylesheet" href="./CSS(指层叠样式表).css" />
    <!-- style书写内部样式 -->
    <style>
      body {
        background-color: #fff;
      }
    </style>
  </head>
  <body>
    <!-- 行内样式 -->
    <div style="width: 80px; background-color: blue; height: 50px"></div>
  </body>
</html>
```

## 二. 设置盒子样式

创建一个高 300px 宽 200px 的盒子

### 1.盒子基本属性

页面部分 先准备一个 div 盒子标签

```html
<div class="box"></div>
```

#### 01.设置宽高

```css
/* .class选择器选择box盒子添加宽高属性 */
.box {
  width: 300px;
  height: 200px;
}
```

#### 02.透明度

可用于做遮罩

```css
.box {
  background-color: #000;
  /* 透明度0.1最小1最大 */
  opacity: 0.3;
}
```

#### 03.盒子阴影

连写语法

box-shadow: _h-shadow v-shadow blur spread color_ inset;

```css
.box {
  box-shadow: 10px 10px 5px #888888 inset;
}
```

| _h-shadow_ | 必需的。水平阴影的位置。允许负值                                                                          |
| ---------- | --------------------------------------------------------------------------------------------------------- |
| _v-shadow_ | 必需的。垂直阴影的位置。允许负值                                                                          |
| _blur_     | 可选。模糊距离                                                                                            |
| _spread_   | 可选。阴影的大小                                                                                          |
| _color_    | 可选。阴影的颜色。在[CSS 颜色值](https://www.runoob.com/cssref/css_colors_legal.aspx)寻找颜色值的完整列表 |
| inset      | 可选。从外层的阴影（开始时）改变阴影内侧阴影                                                              |

### 2.background 背景

#### 01.背景颜色

##### 01.单色颜色

背景颜色三种写法

```css
.box {
  background-color: red;
  background-color: rgb(251, 1, 1);
  background-color: #ff0225;
}
```

##### 02.线性渐变色

渐变色色卡查询: [https://uigradients.com/]
菜鸟工具: [https://c.runoob.com/more/gradients/#LemonLime]

```css
.box {
  /* 默认从上到下 */
  background-image: linear-gradient(#e66465, #9198e5);
  /* 从左到右 */
  background-image: linear-gradient(to right, red, yellow);
  /* 从左上到右下 */
  background-image: linear-gradient(to bottom right, red, yellow);
}
```

#### 02.背景图片

添加一张背景图片

```css
.box {
  background-image: url("./../upload/pic.png");
}
```

##### 05.背景图片样式 书写的连写

background: bg-color bg-image position/bg-size bg-repeat bg-clip

| background-color    | 指定要使用的背景颜色           |
| :------------------ | ------------------------------ |
| background-position | 指定背景图像的初始位置         |
| background-size     | 指定背景图片的大小             |
| background-repeat   | 指定如何重复背景图像           |
| background-image    | 指定要使用的一个或多个背景图像 |
| background-clip     | 指定背景图像的绘画区域         |

```css
.box {
  background: url("./../upload/pic.png") 0 0 / cover no-repeat;
}
```

##### 01.background-position 背景图像的初始位置

| center center | 文字       |
| ------------- | ---------- |
| x% y%         | 百分比位置 |
| 5px 4px       | 像素       |

```css
.box {
  background-position: center center;
  background-position: 50% 50%;
  background-position: 50px 50px;
}
```

##### 02.background-size 背景图片的大小

| 50px 50px | 设置背景图片高度和宽度。第一个值设置宽度，第二个值设置的高度。如果只给出一个值，第二个是设置为 **auto**(自动) |
| --------- | ------------------------------------------------------------------------------------------------------------- |
| cover     | 此时会保持图像的纵横比并将图像缩放成将完全覆盖背景定位区域的最小大小                                          |
| contain   | 此时会保持图像的纵横比并将图像缩放成将适合背景定位区域的最大大小。                                            |

```css
.box {
  background-size: 80px 60px;
  background-size: cover;
  background-size: contain;
}
```

##### 03.background-repeat 指定如何重复背景图像

| repeat                 | 背景图像将向垂直和水平方向重复。这是默认                   |
| ---------------------- | ---------------------------------------------------------- |
| repeat-x \|\| repeat-y | 只有水平位置会重复背景图像 \|\| 只有垂直位置会重复背景图像 |
| no-repeat              | background-image 不会重复                                  |

```css
.box {
  background-repeat: no-repeat;
}
```

##### 04. background-clip 指定背景图像的绘画区域

| padding-box | 默认值。背景绘制在边框方框内（剪切成边框方框）。 |
| ----------- | ------------------------------------------------ |
| border-box  | 背景绘制在衬距方框内（剪切成衬距方框）。         |
| content-box | 背景绘制在内容方框内（剪切成内容方框）。         |

一般会填充边框来达到拉伸效果

```css
.box {
  background-clip: border-box;
}
```

### 3.border 边框

#### 01.边框样式 boder-style

| dotted | 定义一个点线边框       |
| ------ | ---------------------- |
| dashed | 定义一个虚线边框       |
| double | 定义双边框             |
| inse   | 定义一个 3D 的嵌入边框 |
| outset | 定义一个 3D 突出边框   |
| none   | 无边框                 |
| soild  | 实线边框               |

```css
/* 无边框 */
p.none {
  border-style: none;
}
/*  双边框 */
p.dotted {
  border-style: dotted;
}
/* 虚线边框 */
p.dashed {
  border-style: dashed;
}
/* 实线边框 */
p.solid {
  border-style: solid;
}
/* 双边框 */
p.double {
  border-style: double;
}
/* 嵌入边框 */
p.inset {
  border-style: inset;
}
/* 外凸边框 */
p.outset {
  border-style: outset;
}
```

#### 02.边框连写

border:👍width soild black

```css
.box {
  border: 1px soild black;
}
```

#### 03.border-radius 圆角边框

设置四个角弧度

```css
.box {
  border-radius: 50%;
  border-radius: 5%;
  border-left-radius: 25%;
}
```

#### 04.border-collapse 合并内边框

表格合并边距

```css
table {
  border-collapse: collapse;
}
```

#### 05.border-image 边框速记

边框图片快速使用

```
border-image: url(border.png) 30 round;
```

用于拉伸边框(防止图片变形)

```css
border-image: source slice width outset repeat|initial|inherit;
.box {
  border-image: url(./../images/border.png) 55 41 23 131 fill 2.2917rem
    1.7083rem 0.9583rem 5.4583rem;
}
.box2 {
  border-image-source: url(./../images/border.png);
  border-image-slice: 55 41 23 131 fill;
  border-image-width: 2.2917rem 1.7083rem 0.9583rem 5.4583rem;
}
```

## 三. 设置文字样式

### 1.文字颜色

```css
p {
  color: red;
}
```

### 2.文字字体

连写 顺序依次为 字体 字体粗细 字体大小 / 行高 字体风格

```css
p {
  font: font-family font-weight font-size/line-height font-style;
}
```

```css
p {
  font: 900 50px /50px normal;
}
```

#### 01.字体粗细

```css
p {
  font-weight: 500;
}
```

#### 02.文字大小

```css
p {
  font-size: 55px;
}
```

#### 03.文本风格

记住斜体就好

```css
p {
  font-style: italic;
}
```

### 3.修饰文本

```css
/* 中划线 */
p {
  text-decoration: line-through;
}
/* 下划线 */
p {
  text-decoration: underline;
}
```

### 4.文本缩进

```css
p {
  text-indent: 20px;
}
```

### 5.行高与水平对齐

```css
p {
  line-height: 40px;
  text-align: center;
}
```

## 四.设置列表样式

去掉列表默认样式

```css
ul {
  list-style: none;
}
```

## 五.选择器

### 01.通配符选择器

选中所有元素(可以清除默认样式,消除自带的内外边距)

```css
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
```

### 02.id 选择器

```html
<div id="content-id"></div>
#content-id { color: red; }
```

### 03.类选择器

```css
<div class="content-class" > </div > .content-class {
  text-align: center;
}
```

### 04.元素选择器

```css
<p > nihao</p > p {
  background-color: yellow;
}
```

### 05.伪类选择器

```css
<a href="" > </a > a:link {
  color: green;
}
链接未访问的样式 a:visited {
  color: green;
} //已经访问过的
a:hover {
  color: red;
} //鼠标经过
a:active {
  color: yellow;
} //点击的时候
<input type="text" / > input:focus {
  background-color: pink;
} //聚焦时候
<div></div>
//在元素前面插入
 div::before {
  background-color: pink;
}
//在元素后面插入
div::after {
  background-color: pink;
}
```

### 06.子代选择器

```css
<ul>
      <li>1</li>
      <li>2</li>
      <li>3</li>
      <li>4</li>
    </ul>
 ul li:nth-child(2)//第二个
{
  background: #ff0000;
}
ul li:nth-child(odd)//单数
{
  background: #ff0000;
}
ul li:nth-child(even) //双数
{
  background: #0000ff;
}
ul li:nth-child(n + 2)/从第二个开始 {
  background: #ff0000;
}
ul li:frist-child {
  background: #ff0000;
}
ul li:last-child {
  background: #ff0000;
}
```

### 07.组合选择器

```tex
后代选择器(以空格     分隔)
子元素选择器(以大于 > 号分隔）
相邻兄弟选择器（以加号 + 分隔）
普通兄弟选择器（以波浪号 ～ 分隔）
```

### 08.属性选择器

```css
<div class="title"></div>
<input type="text"></input>
[title]
{
    color:blue;
}
input[type="text"]
```

### 09.选择器权重表

//最高权重

```css
p {
  color: red !important;
}
```

![](https://www.runoob.com/wp-content/uploads/2017/06/css_weight.png)

## 六.内容溢出与隐藏处理

### 1.溢出隐藏

```css
overflow: hidden;
```

### 2.溢出添加滚动条

```css
overflow: scroll;
```

### 3.溢出显示省略号

```css
text-overflow: ellipsis;
```

### 4..溢出不换行

```css
white-space: nowrap;
```

### 5.隐藏元素

```css
display: none;
```

### 6.显示元素（转换为块元素）

```css
display: block;
```

### 7.转换为行内块元素

```css
display: inline;
```
