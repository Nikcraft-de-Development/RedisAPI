package de.einnik.redisapi.scoreboard;

import de.einnik.redisapi.RedisAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class ScoreBoardUpdater {

    private final String identifier;
    private BukkitTask task;

    public String getID(){
        return identifier;
    }

    public ScoreBoardUpdater(String identifier){
        this.identifier = identifier;
        start();
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
            updateAndShowPlayersTextBoard(player);
        }
    }

    private void updateAndShowPlayersTextBoard(Player player) {
        final TextBoard textBoard = new TextBoard(player, this);
        update(textBoard);
        textBoard.sortLines();
        textBoard.show();
    }

    protected abstract void update(TextBoard textBoard);
}