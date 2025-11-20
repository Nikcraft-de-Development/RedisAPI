package de.einnik.redisapi.gui.items;

import org.bukkit.inventory.ItemStack;

/**
 * Marker Interface with default methods
 */
public interface MenuItem {

    void build();

    ItemStack itemStack();
}