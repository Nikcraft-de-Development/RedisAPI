package de.einnik.redisapi.gui.pattern;

import de.einnik.redisapi.gui.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public record RowPattern(int row) implements Pattern {

    public RowPattern(int row) {
        this.row = row - 1;
    }

    @Override
    public void set(@NotNull Menu menu) {
        calculate(menu.getInventory(), row);
    }

    private void calculate(@NotNull Inventory inventory, int row) {
        ItemStack i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = i.getItemMeta();
        im.displayName(Component.text(" "));
        i.setItemMeta(im);

        int start = switch (row) {
            case 2 -> 9;
            case 3 -> 18;
            case 4 -> 27;
            case 5 -> 36;
            case 6 -> 45;
            case 7 -> 49;
            default -> 0;
        };

        for (int slot = start; slot < start + 8; slot++) {
            inventory.setItem(slot, i);
        }
    }
}