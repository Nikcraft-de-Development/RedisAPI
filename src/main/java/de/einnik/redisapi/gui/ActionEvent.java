package de.einnik.redisapi.gui;

import de.einnik.redisapi.RedisAPI;
import de.einnik.redisapi.gui.items.MenuItem;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ActionEvent implements Listener {

    private final MenuItem item;
    private final Consumer<ClickType> action;

    public ActionEvent(MenuItem item, Consumer<ClickType> action) {
        this.item = item;
        this.action = action;
        this.clickType = null;
        RedisAPI.getInstance().getServer().getPluginManager().registerEvents(this, RedisAPI.getInstance());
    }

    @Getter
    private ClickType clickType;

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        this.clickType = event.getClick();

        if (!item.itemStack().isSimilar(event.getCurrentItem())) return;

        action.accept(this.clickType);
    }
}