package de.einnik.redisapi.gui;

import de.einnik.redisapi.gui.items.MenuItem;
import de.einnik.redisapi.gui.pattern.Pattern;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Menu {

    @Getter
    private final int rows;
    @Getter
    private final String name;
    @Getter
    private final Inventory inventory;

    public Menu(int rows, String name){
        this.rows = rows;
        this.name = name;
        inventory = Bukkit.createInventory(null, rows * 9, name);
    }

    public void addPattern(@NotNull Pattern pattern){
        pattern.set(this);
    }

    public void setItem(int slot, @NotNull ItemStack item){
        inventory.setItem(slot, item);
    }

    public void addItem(@NotNull ItemStack item){
        inventory.addItem(item);
    }

    public void setItem(int slot, @NotNull MenuItem itemClass){
        itemClass.build();
        inventory.setItem(slot, itemClass.itemStack());
    }

    public void addItem(@NotNull MenuItem itemClass){
        itemClass.build();
        inventory.addItem(itemClass.itemStack());
    }

    public void open(@NotNull Player player){
        player.openInventory(inventory);
    }
}