package de.einnik.redisapi.gui.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public record LeatherItem(Material material, Component name, boolean shine, TextComponent[] lore, Color color, int amount) implements MenuItem{

    private static ItemStack item;

    @Override
    public void build() {
        ItemStack stack = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.displayName(Component.text(name == null ? "" : name.toString()));
        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }
        if (shine) {
            stack.addEnchantment(Enchantment.AQUA_AFFINITY, 1);
        }
        meta.setColor(color);
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