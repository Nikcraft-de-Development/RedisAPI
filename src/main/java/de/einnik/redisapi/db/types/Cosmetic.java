package de.einnik.redisapi.db.types;

import de.einnik.redisapi.db.enums.Rarity;
import de.einnik.redisapi.db.enums.Slot;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
@SuppressWarnings("unused")
public class Cosmetic {

    private final Rarity rarity;
    private final Slot slot;
    private final String name;
    @Setter
    private Material material;
    @Setter
    private int modelID;
    @Setter
    private Particle particle;

    public Cosmetic(Rarity rarity, Slot slot, String name) {
        this.rarity = rarity;
        this.slot = slot;
        this.name = name;
    }

    public ItemStack getAsItemStack(){
        if (material == null || name == null) return null;

        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.displayName(Component.text(name));
        for (ItemFlag flag : ItemFlag.values()){
            im.addItemFlags(flag);
        }
        im.setCustomModelData(modelID);
        i.setItemMeta(im);

        return i;
    }
}