package de.einnik.redisapi.gui.pattern;

import de.einnik.redisapi.gui.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public record CollumPattern(int collum) implements Pattern {

    public CollumPattern(int collum) {
        this.collum = collum - 1;
    }

    @Override
    public void set(@NotNull Menu menu) {
        calculate(menu.getInventory());
    }

    private void calculate(@NotNull Inventory inventory) {
        ItemStack i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = i.getItemMeta();
        im.displayName(Component.text(" "));
        i.setItemMeta(im);

        for (int j = collum; j < inventory.getSize(); j=9) {
            inventory.setItem(j, i);
        }
    }
}