package de.einnik.redisapi.scoreboard.bossbar;

import de.einnik.redisapi.RedisAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class BossBarUpdater {

    private final String id;
    private BukkitTask task;

    public BossBarUpdater(String id){
        this.id = id;
    }

    public void start() {
        if (task != null) {
            return;
        }
        task = RedisAPI.getInstance().runTaskTimer(this::update, 0, 1);
    }

    public void stop() {
        if (task == null) {
            return;
        }
        task.cancel();
        task = null;
    }

    public void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateAndShowPlayersBossBar(player);
        }
    }

    public void updateAndShowPlayersBossBar(Player player){
        final BossBar bossBar = new BossBar(player);
        update(bossBar);
        bossBar.show();
    }

    protected abstract void update(BossBar bossBar);
}