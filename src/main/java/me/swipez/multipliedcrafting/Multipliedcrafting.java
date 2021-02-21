package me.swipez.multipliedcrafting;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
