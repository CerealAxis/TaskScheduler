package xaviermc.top.taskscheduler.updatechecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xaviermc.top.taskscheduler.TaskScheduler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

// 来源：https://www.spigotmc.org/wiki/creating-an-update-checker-that-checks-for-updates
public class UpdateChecker {

    private final JavaPlugin plugin;
    private final int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check the plugin version,reason:" + exception.getMessage());
            }
        });
    }

    public void checkForUpdates() {
        getVersion(version -> {
            String currentVersion = plugin.getDescription().getVersion();
            if (currentVersion.compareToIgnoreCase(version) > 0) {
                plugin.getLogger().info(ChatColor.GREEN + "您当前使用的可能是测试版，若追求稳定请使用最新的正式版！");
                plugin.getLogger().info(ChatColor.GREEN + "当前最新稳定版版本号为：" + version);
                String updateUrl = "https://www.spigotmc.org/resources/taskscheduler.115092/updates";
                plugin.getLogger().info(ChatColor.GREEN + "更新链接：" + updateUrl);
            } else if (currentVersion.equals(version)) {
                plugin.getLogger().info(ChatColor.GREEN + "没有可用的新更新。");
            } else {
                plugin.getLogger().info(ChatColor.RED + "有一个新的更新可用。版本号为：" + version);
                String updateUrl = "https://www.spigotmc.org/resources/taskscheduler.115092/updates";
                plugin.getLogger().info(ChatColor.RED + "更新链接：" + updateUrl);
            }
        });
    }
}