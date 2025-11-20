package de.einnik.redisapi.gui.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public record BasicItem(Material material, Component name, boolean shine, TextComponent[] lore, int amount) implements MenuItem {

    private static ItemStack item;

    @Override
    public void build() {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(Component.text(name == null ? "" : name.toString()));
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        if (shine) {
            stack.addEnchantment(Enchantment.AQUA_AFFINITY, 1);
        }
        stack.lore(List.of(lore));
        stack.setItemMeta(meta);
        stack.setAmount(amount);
        item = stack;
    }

    @Override
    public ItemStack itemStack() {
        return item;
    }
}