package de.einnik.redisapi.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TextBoard {

    @Getter
    private final Player player;
    private final ScoreBoardUpdater updater;
    private HashMap<Integer, String> map = new HashMap<>();
    private int count = 0;
    @Setter
    private String title;

    public TextBoard(Player player, ScoreBoardUpdater updater){
        this.player = player;
        this.updater = updater;
    }

    public void addLine(String line){
        count++;
        map.put(count, line);
    }

    public void addEmptyLine(){
        count++;
        map.put(count, " ");
    }

    protected void sortLines(){
        int maxKey = Collections.max(map.keySet());

        map = map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> maxKey - entry.getKey() + 1,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        HashMap::new
                ));
    }

    public void show(){
        update();
    }

    private void update(){
        if (title == null) throw new IllegalArgumentException("The Title of a ScoreBoard can not be null");

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(updater.getID(), "dummy", ChatColor.BOLD + title);

        for (Map.Entry<Integer, String> map : map.entrySet()){
            int id = map.getKey();
            String value = map.getValue();

            Score score = objective.getScore(value);
            score.setScore(id);
        }

        player.setScoreboard(scoreboard);
    }
}