package xaviermc.top.taskscheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.*;

import xaviermc.top.taskscheduler.bstats.Bstats;
import xaviermc.top.taskscheduler.updatechecker.UpdateChecker;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskScheduler extends JavaPlugin implements CommandExecutor {

    private static final Logger logger = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        logger.info(ChatColor.GREEN + "插件正在加载...");

        Bstats.bstats();

        UpdateChecker updateChecker = new UpdateChecker(this, 115092);
        updateChecker.checkForUpdates();

        getCommand("taskscheduler").setExecutor(this);

        saveDefaultConfig();

        loadTasksFromConfig();

        logger.info(ChatColor.GREEN + "插件加载成功。");
    }

    private void loadTasksFromConfig() {
        FileConfiguration config = getConfig();
        for (String key : config.getKeys(false)) {
            Optional<Runnable> task = createTask(key, config);
            task.ifPresent(runnable -> {
                if (config.getInt(key + ".interval") > 0) {
                    BukkitRunnable bukkitTask = new BukkitRunnable() {
                        @Override
                        public void run() {
                            runnable.run();
                        }
                    };
                    bukkitTask.runTaskTimer(this, 0, config.getInt(key + ".interval") * 20L);
                    logger.log(Level.INFO, ChatColor.GREEN + "成功注册间隔任务：" + key);
                } else {
                    BukkitRunnable bukkitTask = new BukkitRunnable() {
                        @Override
                        public void run() {
                            LocalTime currentTime = LocalTime.now();
                            int currentMinutes = currentTime.getHour() * 60 + currentTime.getMinute();
                            if (currentMinutes >= config.getInt(key + ".time") && currentMinutes < config.getInt(key + ".time") + 0.05) {
                                runnable.run();
                            }
                        }
                    };
                    bukkitTask.runTaskTimer(this, 0, 60 * 20L);
                    logger.log(Level.INFO, ChatColor.GREEN + "成功注册定时任务：" + key);
                }
            });
        }
    }

    private Optional<Runnable> createTask(String key, FileConfiguration config) {
        List<String> commands = config.getStringList(key + ".commands");
        if (commands.isEmpty()) {
            logger.log(Level.WARNING, "Error");
            return Optional.empty();
        }
        return Optional.of(() -> {
            logger.log(Level.INFO, "执行任务：" + key);
            executeCommands(commands);
        });
    }

    private void executeCommands(List<String> commands) {
        for (String command : commands) {
            if (command != null && !command.isEmpty()) {
                Bukkit.getScheduler().runTask(this, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                });
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
                logger.info(ChatColor.RED + "取消所有定时任务");
                Bukkit.getScheduler().cancelTasks(this);

                reloadConfig();
                loadTasksFromConfig();

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