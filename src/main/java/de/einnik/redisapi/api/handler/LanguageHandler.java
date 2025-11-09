package de.einnik.redisapi.api.handler;

import de.einnik.redisapi.RedisAPI;
import de.einnik.redisapi.db.RedisCore;
import de.einnik.redisapi.language.LanguageLoader;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class LanguageHandler implements Listener {

    private final static RedisCore core = RedisAPI.getRedisCore();

    private static String prefix(UUID uuid){
        return "lang:" + uuid.toString();
    }

    public static void setLanguage(UUID uuid, String language){
        core.insert(prefix(uuid), language);
    }

    public static String getLanguage(UUID uuid){
        Object object = core.get(prefix(uuid));
        String value = String.valueOf(object);

        return value != null ? value : "de";
    }

    public static boolean exists(UUID uuid){
        return core.exists(prefix(uuid));
    }

    public static void delete(UUID uuid){
        core.delete(prefix(uuid));
    }

    public static Component getMessage(Player player, String path){
        return LanguageLoader.getMessage(player, path);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        LanguageLoader.addUser(player.getUniqueId(), getLanguage(player.getUniqueId()));
    }
}