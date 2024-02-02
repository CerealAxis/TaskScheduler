package xaviermc.top.taskscheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.*;

import xaviermc.top.taskscheduler.bstats.Bstats;

public class taskscheduler extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "插件加载中");
        Bstats.bstats();
        getCommand("taskscheduler").setExecutor(this);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        for (String key : config.getKeys(false)) {
            int interval = config.getInt(key + ".interval");
            String command = config.getString(key + ".command");
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            };
            task.runTaskTimer(this, 0, interval * 20L);
        }
        getLogger().info(ChatColor.GREEN + "插件加载成功");
    }


    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "定时任务取消中");
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info(ChatColor.GREEN + "定时任务已取消");
        getLogger().info("§a插件已成功卸载");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("taskscheduler") || label.equalsIgnoreCase("ts")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                getLogger().info(ChatColor.RED + "取消旧定时任务");
                Bukkit.getScheduler().cancelTasks(this);
                reloadConfig();
                FileConfiguration config = getConfig();
                for (String key : config.getKeys(false)) {
                    int interval = config.getInt(key + ".interval");
                    String commandString = config.getString(key + ".command");
                    BukkitRunnable task = new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandString);
                        }
                    };
                    task.runTaskTimer(this, 0, interval * 20L);
                }
                sender.sendMessage("§a插件已经重新加载");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.YELLOW + "╔════════════════════════════════════╗");
                sender.sendMessage(ChatColor.GOLD + "        " + ChatColor.BOLD + "TaskScheduler 命令帮助");
                sender.sendMessage(ChatColor.YELLOW + "╠════════════════════════════════════╣");
                sender.sendMessage(ChatColor.GOLD + "命令中 taskscheduler 可缩写为 ts");
                sender.sendMessage(ChatColor.GOLD + "/ts help" + ChatColor.WHITE + " - 打开命令帮助");
                sender.sendMessage(ChatColor.GOLD + "/ts reload" + ChatColor.WHITE + " - 重载配置文件");
                sender.sendMessage(ChatColor.YELLOW + "╚════════════════════════════════════╝");
                return true;
            }
        }
        sender.sendMessage(ChatColor.RED + "输入的指令有误，请检查");
        return false;
    }
}




