# TaskScheduler: Automate Scheduled Task Execution

[中文](README_CN.md)|[English](README.md)

## Supported Versions
| Platform | Version |
|----------|---------|
| Spigot   | 1.20+    |
| Paper    | 1.20+    |
| Purpur   | 1.20+    |
| Leaves   | 1.20+    |

### Note: Other downstream branches of Spigot have not been tested and may not function correctly!

## Deployment Platform
| Platform           |URL|
|--------------------|---|
| SpigotMC (release) |https://www.spigotmc.org/resources/taskscheduler.115092/|
| MineBBS (release)  |https://www.minebbs.com/resources/taskscheduler.6088/|
| Github (release)   |https://github.com/CerealAxis/TaskScheduler/releases|
| Github (Dev)       |https://github.com/CerealAxis/TaskScheduler/actions|

## Config
```yml
# config-version:3.0
# The configuration file has changed since version 1.1.4. Please carefully check and modify before use!

# Define a scheduled task. The key name can be any, but must be unique.
broadcast:
  # Define the interval time for the interval task, in seconds.
  interval: 10
  time: -1 # This indicates that this task is an interval task.
  # Define the command for the scheduled task. It can be any valid console command.
  commands:
    # It should be "commands" not "command"!
    - "say Hello, this is Taskscheduler!"
    - "say Which is created by CerealAxis."
  # To set multiple commands, follow the format and continue writing below. Pay attention to the formatting!

# Example 2
#clear:
#  interval: 30
#  commands:
#    - "say clear the server"
# For a single command, set it up like this.

# Define a scheduled task. The key name can be any, but must be unique.
clearing:
  # Define the interval time for the interval task, in seconds.
  interval: -1 #-1 means disabled
  time: 12:00 # This indicates that this task is a scheduled task.
  # The time and interval parameters cannot be enabled at the same time, otherwise an error will occur.
  # Define the command for the scheduled task. It can be any valid console command.
  commands:
    # It should be "commands" not "command"!
    - "say Hello, this is Taskscheduler!"
    - "say Which is created by CerealAxis."
  # To set multiple commands, follow the format and continue writing below. Pay attention to the formatting!
```
## Commands

#### The **ts** in the command can also be written as TaskScheduler

| Command       | Description                                 |
|---------------|---------------------------------------------|
| /ts reload    | Reloads the configuration of TaskScheduler  |
| /ts help      | Opens the command help menu                  |

## Statistics
[_![](https://bstats.org/signatures/bukkit/TaskScheduler.svg)](https://bstats.org/plugin/bukkit/TaskScheduler/20876)