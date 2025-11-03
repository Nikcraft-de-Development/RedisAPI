package de.einnik.redisapi;

import de.einnik.redisapi.db.RedisCore;
import de.einnik.redisapi.manager.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class RedisAPI extends JavaPlugin {

    private static RedisAPI instance;
    private static RedisCore core;

    @Override
    public void onLoad(){
        instance = this;
        core = new RedisCore(true);
    }

    @Override
    public void onEnable() {
        registerListeners();
    }

    @Override
    public void onDisable(){
        core.close();
        ProfileManager.saveValue();
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ProfileManager(), this);
    }

    public static RedisAPI getInstance(){
        return instance;
    }

    public static RedisCore getRedisCore(){
        return core != null ? core : new RedisCore(true);
    }

    public BukkitTask runTaskTimer(Runnable runnable, int delay, int period) {
        return Bukkit.getScheduler().runTaskTimer(this, runnable, delay, period);
    }
}