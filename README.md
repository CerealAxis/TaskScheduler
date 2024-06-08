# TaskScheduler 

_Automate Scheduled Task Execution_

[![GitHub release](https://img.shields.io/github/v/release/CerealAxis/TaskScheduler?style=flat-square)](https://github.com/CerealAxis/TaskScheduler/releases)
[![GitHub Actions CI](https://img.shields.io/github/actions/workflow/status/CerealAxis/TaskScheduler/dev-build.yml?style=flat-square)](https://github.com/CerealAxis/TaskScheduler/actions)
[![License: MIT](https://img.shields.io/badge/license-MIT-yellow.svg)](https://github.com/CerealAxis/TaskScheduler)
![GitHub all releases](https://img.shields.io/github/downloads/CerealAxis/TaskScheduler/total?style=flat-square)

[中文](README_CN.md) | [English](README.md)

## Supported Versions

| Platform | Version     |
|----------|-------------|
| Spigot   | 1.20-1.20.6 |
| Paper    | 1.20-1.20.6 |
| Purpur   | 1.20-1.20.6 |
| Leaves   | 1.20-1.20.6 |

> Note: Other downstream branches of Spigot have not been tested and may not function correctly.

## Download

| Platform           | URL                                                                        |
|--------------------|----------------------------------------------------------------------------|
| SpigotMC (release) | [SpigotMC](https://www.spigotmc.org/resources/taskscheduler.115092/)       |
| MineBBS (release)  | [MineBBS](https://www.minebbs.com/resources/taskscheduler.6088/)           |
| GitHub (release)   | [GitHub Releases](https://github.com/CerealAxis/TaskScheduler/releases)    |
| GitHub (Dev)       | [GitHub Actions](https://github.com/CerealAxis/TaskScheduler/actions)      |

## Config

```yml
# config-version: 3.0
# The configuration file has changed since version 1.1.4. Please review and update before use.

# Define a scheduled task. The key name can be any unique identifier.
broadcast:
  # Set the interval time for the task in seconds.
  interval: 10
  time: -1 # Indicates this is an interval task.
  # Define the commands to be executed. These can be any valid console commands.
  commands:
    - "say Hello, this is TaskScheduler!"
    - "say Created by CerealAxis."

# Example 2
# clear:
#   interval: 30
#   commands:
#     - "say Clearing the server"
# For a single command, configure it similarly.

# Define another scheduled task.
clearing:
  # Set the interval time for the task in seconds.
  interval: -1 # -1 means disabled
  time: 12:00 # Indicates this is a scheduled task.
  # Note: The 'time' and 'interval' parameters cannot be enabled simultaneously.
  # Define the commands to be executed.
  commands:
    - "say Hello, this is TaskScheduler!"
    - "say Created by CerealAxis."
```

## Commands

> The **ts** in the commands can also be written as **TaskScheduler**.

| Command    | Description                               |
|------------|-------------------------------------------|
| /ts reload | Reloads the TaskScheduler configuration.  |
| /ts help   | Opens the command help menu.              |

## Statistics

[![bStats](https://bstats.org/signatures/bukkit/TaskScheduler.svg)](https://bstats.org/plugin/bukkit/TaskScheduler/20876)
