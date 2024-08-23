# Mysql语句

## 一.查询数据

### 1.基础查询

```sql
select * from userlist /*查询表返回所有内容*/
select id from userlist /*查询表返回指定内容*/
```

```sql
SELECT * from userlist where sex = '男' AND age>10;/*按某种条件查询表*/
```

### 2.查询升序与降序排序

```sql
SELECT * from userlist ORDER BY age ASC;/*按年龄升序排列*/
SELECT * from userlist WHERE sex= '男' ORDER BY age desc;/*按降序排列*/
```

### 3.获取前几条数据

```sql
SELECT * from userlist LIMIT 0,2 ;/*获取0到2的数据*/
```

## 二.新增数据

```sql
INSERT INTO userlist(username,sex,password,phone) VALUES('孙悟空','男',3000,110),('猪八戒','男',3001,120);/*增加数据*/
```

## 三.修改数据

**更新操作会将表中所有数据改变，在修改时候必须加上条件**

```sql
UPDATE userlist set age = 10,sex = '男' WHERE id = 2;/*改变某个数据*/
```

## 四.删除数据

**删除操作会将表中所有数据改变，在修改时候必须加上条件**

```sql
DELETE from userlist WHERE id = 3;
```

