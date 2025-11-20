package de.einnik.redisapi.gui.pattern;

import de.einnik.redisapi.gui.Menu;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class BorderPattern implements Pattern {

    @Override
    public void set(@NotNull Menu menu) {
        calculate(menu.getInventory());
    }

    private void calculate(@NotNull Inventory inventory) {
        ItemStack i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta im = i.getItemMeta();
        im.displayName(Component.text(" "));
        i.setItemMeta(im);

        for (int x = 0; x < inventory.getSize(); x++) {
            inventory.setItem(x, i);
        }

        for (int x = 0; x < inventory.getSize(); x+=9) {
            inventory.setItem(x, i);
        }

        for (int x = 8; x < inventory.getSize(); x+=9) {
            inventory.setItem(x, i);
        }

        for (int x = 0; inventory.getSize() - 9 > x; x++) {
            inventory.setItem(x, i);
        }
    }
}