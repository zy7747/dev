## 1.创建和切换分支

```
git checkout -b 分支名
```

## 2.从指定分支拉取代码

```
git clone -b 远程分支名称 远程地址
git pull origin 本地分支名称:远程分支名称
```

## 3.推送到指定的远程分支

```
git push origin 本地分支名称:远程分支名称
```

## 4.查看本地分支与远程分支绑定

```
git branch -vv
```

## 5.绑定某一个远程分支

```
git push --set-upstream origin 分支名
```

## 6.合并两个本地分支

```
git merge myself
```

## 1000.git提交命令规范

- feat：新增功能
- fix：bug 修复
- docs：文档更新
- style：不影响程序逻辑的代码修改(修改空白字符，格式缩进，补全缺失的分号等，没有改变代码逻辑)
- refactor：重构代码(既没有新增功能，也没有修复 bug)
- perf：性能, 体验优化
- test：新增测试用例或是更新现有测试
- build：主要目的是修改项目构建系统(例如 glup，webpack，rollup 的配置等)的提交
- ci：主要目的是修改项目继续集成流程(例如 Travis，Jenkins，GitLab CI，Circle等)的提交
- chore：不属于以上类型的其他类，比如构建流程, 依赖管理
- revert：回滚某个更早之前的提交
