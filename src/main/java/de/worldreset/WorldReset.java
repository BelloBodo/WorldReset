package de.worldreset;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldReset extends JavaPlugin {

    private static WorldReset instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("reset").setExecutor(new WorldReset());
    }

    @Override
    public void onDisable() {
    }

    public static WorldReset getInstance() {
        return instance;
    }
}
