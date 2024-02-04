package xaviermc.top.taskscheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.*;

import xaviermc.top.taskscheduler.bstats.Bstats;

import java.util.List;
import java.util.logging.Logger;

public class TaskScheduler extends JavaPlugin implements CommandExecutor {

    private static final Logger logger = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        logger.info(ChatColor.GREEN + "插件正在加载...");

        Bstats.bstats();

        getCommand("taskscheduler").setExecutor(this);

        saveDefaultConfig();

        FileConfiguration config = getConfig();

        for (String key : config.getKeys(false)) {
            int interval = config.getInt(key + ".interval");
            List<String> commands = config.getStringList(key + ".commands");

            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    logger.info("执行定时任务：" + key);
                    executeCommands(commands);
                }
            };

            task.runTaskTimer(this, 0, interval * 20L);
        }

        logger.info(ChatColor.GREEN + "插件加载成功。");
    }

    private void executeCommands(List<String> commands) {
        for (String command : commands) {
            if (command != null && !command.isEmpty()) {
                Bukkit.getScheduler().runTask(this, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                });
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDisable() {
        logger.info(ChatColor.GREEN + "取消定时任务中...");
        Bukkit.getScheduler().cancelTasks(this);
        logger.info(ChatColor.GREEN + "定时任务已取消。");
        logger.info("§a插件已成功卸载。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("taskscheduler") || label.equalsIgnoreCase("ts")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                logger.info(ChatColor.RED + "取消旧定时任务");
                Bukkit.getScheduler().cancelTasks(this);

                reloadConfig();
                FileConfiguration config = getConfig();

                for (String key : config.getKeys(false)) {
                    int interval = config.getInt(key + ".interval");
                    List<String> commands = config.getStringList(key + ".commands");

                    BukkitRunnable task = new BukkitRunnable() {
                        @Override
                        public void run() {
                            logger.info("执行定时任务: " + key);
                            executeCommands(commands);
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

        logger.warning("未知命令或输入的指令有误，请检查");
        sender.sendMessage(ChatColor.RED + "输入的指令有误，请检查");
        return false;
    }
}

