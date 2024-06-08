# TaskScheduler

_自动化定时任务执行_

[![GitHub release](https://img.shields.io/github/v/release/CerealAxis/TaskScheduler?style=flat-square)](https://github.com/CerealAxis/TaskScheduler/releases)
[![GitHub Actions CI](https://img.shields.io/github/actions/workflow/status/CerealAxis/TaskScheduler/dev-build.yml?style=flat-square)](https://github.com/CerealAxis/TaskScheduler/actions)
[![License: MIT](https://img.shields.io/badge/license-MIT-yellow.svg)](https://github.com/CerealAxis/TaskScheduler)
![GitHub all releases](https://img.shields.io/github/downloads/CerealAxis/TaskScheduler/total?style=flat-square)

[中文](README_CN.md) | [English](README.md)

## 支持的版本

| 平台    | 版本        |
|---------|-------------|
| Spigot  | 1.20-1.20.6 |
| Paper   | 1.20-1.20.6 |
| Purpur  | 1.20-1.20.6 |
| Leaves  | 1.20-1.20.6 |

> 注意：尚未测试Spigot的其他下游分支，可能无法正常运行。

## 下载

| 平台               | URL                                                                          |
|--------------------|------------------------------------------------------------------------------|
| SpigotMC (发布版)  | [SpigotMC](https://www.spigotmc.org/resources/taskscheduler.115092/)         |
| MineBBS (发布版)   | [MineBBS](https://www.minebbs.com/resources/taskscheduler.6088/)             |
| GitHub (发布版)    | [GitHub Releases](https://github.com/CerealAxis/TaskScheduler/releases)      |
| GitHub (开发版)    | [GitHub Actions](https://github.com/CerealAxis/TaskScheduler/actions)   

## Config
```yml
# config-version:3.0
# 配置文件于1.1.4版本后有改变，请仔细检查并修改后后再使用！

# 定义一个定时任务，键名可以任意取，但不能重复
broadcast:
  # 定义间隔任务的间隔时间，单位是秒
  interval: 10
  time: -1 # 这样表示此任务为间隔任务
  # 定义定时任务的命令，可以是任何有效的控制台命令
  commands:
    # 是commands不是command！
    - "say Hello, this is Taskscheduler!"
    - "say Which is created by CerealAxis."
  # 设置多条命令就仿照格式接着往下写就可以，千万注意格式！

# 示例2
#clear:
#  interval: 30
#  time: -1
#  commands:
#    - "say clear the server"
#单条命令就按照这样设置。

clearing:
  # 定义间隔任务的间隔时间，单位是秒
  interval: -1 #-1为禁用
  time: 12:00 # 这样表示此任务为定时任务
  # time和interval参数不能同时启用，否则会报错。
  # 定义定时任务的命令，可以是任何有效的控制台命令
  commands:
    # 是commands不是command！
    - "say Hello, this is Taskscheduler!"
    - "say Which is created by CerealAxis."
  # 设置多条命令就仿照格式接着往下写就可以，千万注意格式！
```
## 命令

> 命令里面的 **ts** 也可以写为 TaskScheduler

| Command       | Description |
|---------------|-------------|
| /ts reload    | 重载插件        |
| /ts help      | 打开帮助菜单      |

## 统计
[_![](https://bstats.org/signatures/bukkit/TaskScheduler.svg)](https://bstats.org/plugin/bukkit/TaskScheduler/20876)
