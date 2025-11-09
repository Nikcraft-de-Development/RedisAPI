package de.einnik.redisapi.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Colors {

    public static Component prefix(){
        return MiniMessage.miniMessage().deserialize("<gray>[</gray><gradient:#ff5900:#cc9c66>Nikcraft.de</gradient><gray>]</gray>");
    }

    public static Component red(){
        return MiniMessage.miniMessage().deserialize("<gradient:#ff0000:#ff0000>");
    }

    public static Component lime(){
        return MiniMessage.miniMessage().deserialize("<gradient:#80ff00:#80ff00>");
    }

    public static Component purple(){
        return MiniMessage.miniMessage().deserialize("<gradient:#d400ff:#d400ff>");
    }
}