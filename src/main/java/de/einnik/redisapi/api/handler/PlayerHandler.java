package de.einnik.redisapi.api.handler;

import de.einnik.redisapi.manager.PermissionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerHandler {

    public static void addPermission(Player player, String permission){
        PermissionManager.addPermission(player.getUniqueId(), permission);
    }

    public static void addPermission(UUID uuid, String permission){
        PermissionManager.addPermission(uuid, permission);
    }

    public static void removePermission(Player player, String permission){
        PermissionManager.removePermission(player.getUniqueId(), permission);
    }

    public static void removePermission(UUID uuid, String permission){
        PermissionManager.removePermission(uuid, permission);
    }

    public static boolean hasPermission(Player player, String permission){
        return PermissionManager.hasPermission(player.getUniqueId(), permission);
    }

    public static boolean hasPermission(UUID uuid, String permission){
        return PermissionManager.hasPermission(uuid, permission);
    }

    public static String getRank(UUID uuid){
        return PermissionManager.getRank(uuid);
    }

    public static String getRank(Player player){
        return PermissionManager.getRank(player.getUniqueId());
    }

    public static NamedTextColor getRankColor(UUID uuid){
        return PermissionManager.getRankColor(uuid);
    }

    public static NamedTextColor getRankColor(Player player){
        return PermissionManager.getRankColor(player.getUniqueId());
    }

    public static Component getPlayerName(Player player){
        return PermissionManager.getPlayerName(player.getUniqueId());
    }

    public static Component getPlayerName(UUID uuid){
        return PermissionManager.getPlayerName(uuid);
    }

    public static boolean isRankPermanent(UUID uuid){
        return PermissionManager.isRankPermanent(uuid);
    }

    public static boolean isRankPermanent(Player player){
        return PermissionManager.isRankPermanent(player.getUniqueId());
    }

    public static String getRemainTime(Player player){
        return PermissionManager.getRemainTime(player.getUniqueId());
    }

    public static String getRemainTime(UUID uuid){
        return PermissionManager.getRemainTime(uuid);
    }
}