package de.einnik.redisapi;

import de.einnik.redisapi.api.handler.LanguageHandler;
import de.einnik.redisapi.db.RedisCore;
import de.einnik.redisapi.language.LanguageLoader;
import de.einnik.redisapi.manager.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.Reader;
import java.util.Locale;

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
        Bukkit.getScheduler().runTaskAsynchronously(this, this::loadYaml);
    }

    @Override
    public void onDisable(){
        core.close();
        ProfileManager.saveValue();
    }

    public static RedisAPI getInstance() {
        return instance != null ? instance : (RedisAPI) Bukkit.getPluginManager().getPlugin("RedisAPI");
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ProfileManager(), this);
        Bukkit.getPluginManager().registerEvents(new LanguageHandler(), this);
    }

    public static RedisCore getRedisCore(){
        return core != null ? core : new RedisCore(true);
    }

    public BukkitTask runTaskTimer(Runnable runnable, int delay, int period) {
        return Bukkit.getScheduler().runTaskTimer(this, runnable, delay, period);
    }

    public void loadYaml(){
        Reader reader_de = getTextResource("german.yml");
        if (reader_de == null){
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        LanguageLoader.languages.put(Locale.GERMAN, YamlConfiguration.loadConfiguration(reader_de));

        Reader reader_en = getTextResource("en.yml");
        if (reader_en == null){
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        LanguageLoader.languages.put(Locale.ENGLISH, YamlConfiguration.loadConfiguration(reader_en));
    }
}