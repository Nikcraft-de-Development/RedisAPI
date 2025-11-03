package de.einnik.redisapi.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PermissionManager {

    private static LuckPerms luckPerms;

    private PermissionManager(){
        luckPerms = LuckPermsProvider.get();
    }

    public static void addPermission(UUID uuid, String permission){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return;

        user.data().add(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static void removePermission(UUID uuid, String permission){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return;

        user.data().remove(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static boolean hasPermission(UUID uuid, String permission){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return false;

        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    public static String getRank(UUID uuid){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return null;

        String group = user.getPrimaryGroup();

        return switch (group) {
            case "administrator" -> "Administrator";
            case "developer" -> "Developer";
            case "content" -> "Content";
            case "default" -> "Spieler";
            case "creator+" -> "Creator+";
            case "creator" -> "Creator";
            case "moderator" -> "Moderator";
            case "vip" -> "Vip";
            case "premium" -> "Premium";
            default -> null;
        };
    }

    public static NamedTextColor getRankColor(UUID uuid){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return null;

        String group = user.getPrimaryGroup();
        return switch (group){
            case "administrator" -> NamedTextColor.DARK_RED;
            case "developer", "moderator", "content" -> NamedTextColor.RED;
            case "default" -> NamedTextColor.GRAY;
            case "creator+" -> NamedTextColor.DARK_PURPLE;
            case "creator" -> NamedTextColor.LIGHT_PURPLE;
            case "vip" -> NamedTextColor.YELLOW;
            case "premium" -> NamedTextColor.AQUA;
            default -> null;
        };
    }

    public static Component getPlayerName(UUID uuid){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return null;

        Player player = Bukkit.getPlayer(uuid);
        if (player == null) return null;
        String group = user.getPrimaryGroup();
        return switch (group){
            case "administrator" -> Component.text("§4Admin §7| §4" + player.getName());
            case "developer" -> Component.text("§cDev §7| §c" + player.getName());
            case "content" -> Component.text("§4cContent §7| §c" + player.getName());
            case "default" -> Component.text("§7" + player.getName());
            case "creator+" -> Component.text("§5Creator+ §7| §5" + player.getName());
            case "creator" -> Component.text("§dCreator §7| §d" + player.getName());
            case "moderator" -> Component.text("§cMod §7| §c" + player.getName());
            case "vip" -> Component.text("§eVip §7| §e" + player.getName());
            case "premium" -> Component.text("§bPremium §7| §b" + player.getName());
            default -> null;
        };
    }

    public static boolean isRankPermanent(UUID uuid){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return false;

        List<InheritanceNode> groupNodes = user.getNodes().stream()
                .filter(n -> n.getType() == NodeType.INHERITANCE)
                .map(n -> (InheritanceNode) n)
                .toList();

        if (groupNodes.isEmpty()) return false;

        Optional<InheritanceNode> highestOpt = groupNodes.stream()
                .max(Comparator.comparingInt(node -> {
                    Group g = luckPerms.getGroupManager().getGroup(node.getGroupName());
                    return g == null ? 0 : g.getWeight().orElse(0);
                }));

        return highestOpt.map(node -> !node.hasExpiry()).orElse(false);
    }

    public static String getRemainTime(UUID uuid){
        User user = luckPerms.getUserManager().getUser(uuid);
        if (user == null) return null;

        List<InheritanceNode> groupNodes = user.getNodes().stream()
                .filter(n -> n.getType() == NodeType.INHERITANCE)
                .map(n -> (InheritanceNode) n)
                .toList();

        if (groupNodes.isEmpty()) return "Permanent";

        Optional<InheritanceNode> highestOpt = groupNodes.stream()
                .max(Comparator.comparingInt(node -> {
                    Group g = luckPerms.getGroupManager().getGroup(node.getGroupName());
                    return g == null ? 0 : g.getWeight().orElse(0);
                }));

        InheritanceNode highestNode = highestOpt.get();

        if (!highestNode.hasExpiry()) {
            return "Permanent";
        }


        Instant expiresAt = highestNode.getExpiry();
        if (expiresAt == null) return "In wenigen Sekunden";

        Duration remaining = Duration.between(Instant.now(), expiresAt);

        long days = remaining.toDays();
        long hours = remaining.minusDays(days).toHours();
        long minutes = remaining.minusDays(days).minusHours(hours).toMinutes();

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("§7Tage §a");
        if (hours > 0 || days > 0) sb.append(hours).append("§7Stunden §a");
        sb.append(minutes).append("§7Minuten§a");

        return sb.toString().trim();
    }
}