package de.einnik.redisapi.manager;

import de.einnik.redisapi.RedisAPI;
import de.einnik.redisapi.db.RedisCore;
import de.einnik.redisapi.db.types.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager implements Listener {

    private static final HashMap<Player, HashMap<UUID, Profile>> map = new HashMap<>();
    private static final RedisCore core = RedisAPI.getRedisCore();

    public static void add(Player player, Profile profile){
        HashMap<UUID, Profile> hashMap = new HashMap<>();
        hashMap.put(player.getUniqueId(), profile);

        map.put(player, hashMap);
    }

    public static boolean exists(Player player){
        return core.existsTable("profiles", player.getName().toLowerCase());
    }

    public static HashMap<UUID, Profile> get(Player player){
        return map.get(player);
    }

    public static void saveValue(){
        for (Map.Entry<Player, HashMap<UUID, Profile>> outerEntry : map.entrySet()){

            Player player = outerEntry.getKey();
            HashMap<UUID, Profile> raw = outerEntry.getValue();
            String value = raw.toString();

            core.updateTable("profiles", player.getName().toLowerCase(), value);
        }
    }

    public static void saveValue(Player player){
        HashMap<UUID, Profile> value = map.get(player);
        core.updateTable("profiles", player.getName().toLowerCase(), value.toString());

        map.remove(player);
    }

    public static void loadValue(Player player){
        String name = player.getName().toLowerCase();

        HashMap<String, Object> hashMap = core.getTableHashMap("profiles", name);

        HashMap<UUID, Profile> uuidProfileMap = new HashMap<>();

        for (String key : hashMap.keySet()) {
            try {
                UUID uuid = UUID.fromString(key);
                Profile profile = (Profile) hashMap.get(key);
                uuidProfileMap.put(uuid, profile);
            } catch (IllegalArgumentException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        }

        map.put(player, uuidProfileMap);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (exists(e.getPlayer())) {
            loadValue(e.getPlayer());
        } else {
            add(e.getPlayer(), new Profile());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        saveValue(e.getPlayer());
    }
}