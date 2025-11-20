package de.einnik.redisapi.gui.items;

import de.einnik.redisapi.RedisAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public record HeadItem(Component name, boolean shine, TextComponent[] lore, String value, int amount) implements MenuItem {

    private static ItemStack item;

    @Override
    public void build() {
        ItemStack i = createHeadItem(value, "placeholder", List.of("placeholder"));
        ItemMeta meta = i.getItemMeta();
        meta.displayName(name);
        meta.lore(List.of(lore));
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        if (shine) {
            i.addEnchantment(Enchantment.AQUA_AFFINITY, 1);
        }
        i.setItemMeta(meta);
        i.setAmount(amount);
        item = i;
    }

    @Override
    public ItemStack itemStack() {
        return item;
    }

    private @NotNull ItemStack createHeadItem(String texture, String displayName, List<String> lore) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        if (meta == null) return head;

        try {
            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            URL skinUrl = new URL("https://textures.minecraft.net/texture/" + texture);
            textures.setSkin(skinUrl);
            profile.setTextures(textures);

            meta.setOwnerProfile(profile);
            meta.setDisplayName(displayName);
            meta.setLore(lore);

            head.setItemMeta(meta);
        } catch (Exception e) {
            RedisAPI.getInstance().getLogger().log(Level.SEVERE, "Fehler beim Erstellen des Heads: " + texture, e);
        }

        return head;
    }
}