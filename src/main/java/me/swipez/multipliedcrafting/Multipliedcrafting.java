package me.swipez.multipliedcrafting;

import me.swipez.multipliedcrafting.bstats.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Multipliedcrafting extends JavaPlugin {
    HashMap<UUID, Integer> craftamount = new HashMap<>();
    boolean gamestarted = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new CraftingListener(this), this);
        getCommand("craftingchallenge").setExecutor(new CraftCommand(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("craftingchallenge").setTabCompleter(new CommandComplete());
        new Metrics(this, 10431);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
