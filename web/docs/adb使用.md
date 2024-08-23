# 一.基础命令

## 1.基础命令

### 01.开启服务

```cmd
start-server 
```

### 02.结束连接

```cmd
adb kill-server
```

### 03.查看连接设备

```cmd
adb devices
```

## 2.无线连接移动设备

```cmd
adb connect 100.121.201.224
```

## 3.安装与卸载APK

### 01.安装APK命令

```cmd
adb install C:\Users\zy\Desktop\utils\douyu.apk
```

### 02.卸载APK命令

```cmd
adb uninstall
```

## 4.进入退出手机调试模式

进入退出手机调试模式

```cmd
adb shell 
exit
```

## 5.推拉文件

### 01.拉取文件   adb pull 手机路径 电脑路径

```cmd
adb pull /sdcard/DCIM/Camera C:\Users\zy\Desktop\util
```

### 02.推送文件 adb push  电脑路径 手机路径

```cmd
adb push C:\Users\zy\Desktop\util /sdcard/DCIM/Camera 
```

