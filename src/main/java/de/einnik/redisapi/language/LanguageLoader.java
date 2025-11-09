package de.einnik.redisapi.language;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class LanguageLoader {

    public static final Map<Locale, YamlConfiguration> languages = new HashMap<>();
    public static final Map<UUID, Locale> data = new HashMap<>();

    public static void addUser(UUID uuid, String name){
        if (name.equals("en")) {
            data.put(uuid, Locale.ENGLISH);
        } else {
            data.put(uuid, Locale.GERMAN);
        }
    }

    public static Component getMessage(Player player, String path){
        Locale locale = data.get(player.getUniqueId());
        if (locale == null){
            locale = Locale.GERMAN;
        }

        YamlConfiguration config = languages.get(locale);
        String value = config.getString(path);
        if (value == null){
            value = getDefault(path);
        }

        return Component.text(value);
    }

    public static String getDefault(String path){
        YamlConfiguration lang = languages.get(Locale.GERMAN);
        return lang.getString(path);
    }
}