package xaviermc.top.taskscheduler;// 导入需要的包

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.*;
import org.checkerframework.checker.units.qual.C;

// 定义一个插件类，继承自JavaPlugin
public class taskscheduler extends JavaPlugin implements CommandExecutor {

    // 插件启用时执行的方法
    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN+"插件加载中");
        // 注册插件的命令执行器
        getCommand("taskscheduler").setExecutor(this);
        // 保存默认的配置文件
        saveDefaultConfig();
        // 获取配置文件对象
        FileConfiguration config = getConfig();
        // 遍历配置文件中的所有键，每个键代表一个定时任务
        for (String key : config.getKeys(false)) {
            // 获取定时任务的间隔时间，单位是秒
            int interval = config.getInt(key + ".interval");
            // 获取定时任务的命令，可以是任何有效的控制台命令
            String command = config.getString(key + ".command");
            // 创建一个新的BukkitRunnable对象，用于执行定时任务
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    // 执行命令
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            };
            // 以固定延迟（interval秒）执行定时任务，从插件启用后开始计时
            task.runTaskTimer(this, 0, interval * 20L);
        }
        getLogger().info(ChatColor.GREEN+"插件加载成功");
    }

    // 插件禁用时执行的方法
    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN+"定时任务取消中");
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info(ChatColor.GREEN+"定时任务已取消");
        getLogger().info("§a插件已成功卸载");
    }

    // 实现onCommand方法
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 判断命令是否是taskscheduler
        if (label.equalsIgnoreCase("taskscheduler") || label.equalsIgnoreCase("ts")) {
            // 判断是否有一个参数，并且是reload
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                // 取消所有已存在的定时任务
                getLogger().info(ChatColor.RED + "取消旧定时任务");
                Bukkit.getScheduler().cancelTasks(this);

                // 重载插件的配置文件
                reloadConfig();

                // 获取配置文件对象
                FileConfiguration config = getConfig();

                // 遍历配置文件中的所有键，每个键代表一个定时任务
                for (String key : config.getKeys(false)) {
                    // 获取定时任务的间隔时间，单位是秒
                    int interval = config.getInt(key + ".interval");
                    // 获取定时任务的命令，可以是任何有效的控制台命令
                    String commandString = config.getString(key + ".command");

                    // 创建一个新的BukkitRunnable对象，用于执行定时任务
                    BukkitRunnable task = new BukkitRunnable() {
                        @Override
                        public void run() {
                            // 执行命令
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandString);
                        }
                    };

                    // 以固定延迟（interval秒）执行定时任务，从插件启用后开始计时
                    task.runTaskTimer(this, 0, interval * 20L);
                }

                // 发送一条消息给命令发送者，表示重载成功
                sender.sendMessage("§a插件已经重新加载");

                // 返回true，表示命令处理完毕
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.GOLD + "TaskScheduler命令帮助");
                sender.sendMessage(ChatColor.GOLD + "命令taskscheduler可缩写为ts");
                sender.sendMessage(ChatColor.GOLD + "/ts help（打开命令帮助）");
                sender.sendMessage(ChatColor.GOLD + "/ts reload（重载配置文件）");

                // 返回true，表示命令处理完毕
                return true;
            }
        }

        // 返回false，表示命令无效或者有误
        sender.sendMessage(ChatColor.RED + "输入的指令有误，请检查");
        return false;
    }


}




