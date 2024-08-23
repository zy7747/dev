# linux

```bash
sudo apt install mysql-server            #安装mysql-server  查看版本 mysql -V
sudo apt-get install nginx            #安装mysql-server  查看版本 nginx -v
sudo apt-get install openjdk-8-jdk		#安装jdk	查看版本  java --version
sudo sh test-docker.sh
```



```cmd
sudo service mysql start            #启动数据库
sudo mysql -u root -p              	#登录数据库
sudo service mysql restart         	#重启数据库
```

```bash
show databases;                  #查看当前数据库列表
show tables;				    #查看当前数据库列表
创建数据库
mysql> create database abc;      # 创建数据库
mysql> use abc;                  # 使用已创建的数据库 
mysql> set names utf8;           # 设置编码
mysql> source /home/abc/abc.sql  # 导入备份数据库
mysql> show variables like 'port';

exit                              #退场命令行
```

```
select user from mysql.user; #查看自己数据库的用户
```

```bash
设置数据库密码
mysql> use mysql;
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY '123';
mysql> flush privileges;
```

nginx

```
cd /etc/init.d
nginx
cd /etc/nginx/sites-available
vi default
```

```
cp /etc/conf/nginx.conf /etc/conf/nginx.conf.cp
```

```
sudo nano /etc/nginx/nginx.conf
```