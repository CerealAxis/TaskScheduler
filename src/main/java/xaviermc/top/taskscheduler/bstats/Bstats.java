package xaviermc.top.taskscheduler.bstats;

import xaviermc.top.taskscheduler.metrics.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import xaviermc.top.taskscheduler.taskscheduler;

public class Bstats {
    public static void bstats() {
        int pluginId = 20876;
        JavaPlugin plugin = JavaPlugin.getPlugin(taskscheduler.class);
        Metrics metrics = new Metrics(plugin, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));    }
}