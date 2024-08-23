## 10.Mysql安装

### 1.配置文件

打开刚刚解压的文件夹 C:\web\mysql-8.0.27-winx64 ，在该文件夹下创建 my.ini 配置文件，编辑 my.ini 配置以下基本信息：

```jsx
[client]
# 设置mysql客户端默认字符集
default-character-set=utf8
[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录
basedir=E:\\mysql
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```

### 2.以管理员身份打开 cmd 命令行工具，切换目录：

```
cd c:\\web\mysql-8.0.27-winx64\bin
```

### 3.初始化数据库：

```jsx
mysqld --initialize --console
```

```jsx
...
2018-04-20T02:35:05.464644Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: N7irlhbTlJ%_,
...
```

WAkr.mLd_7(c   就是初始密码，后续登录需要用到，你也可以在登陆后修改密码。

### 4.输入以下安装命令：

```jsx
mysqld install
```

### 5.启动数据库：

```
net start mysql
```

### 6.登录Mysql

```
 mysql -u root -p
 mysql -h 主机地址 -P 端口号 -u用户名 －p用户密码
```

```
Enter password:
```

```
sc query mysql
```

```
sc delete mysql
```



### 8.退出Mysql

```
exit
```

停止mysql

```
net stop mysql
```

### 7.修改数据库密码

```
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
```

8.

```
nginx -s reload
```
