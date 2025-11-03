package de.einnik.redisapi.scoreboard.bossbar;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class BossBar {

    private final Player player;
    private Component msg;
    private float progress;
    private net.kyori.adventure.bossbar.BossBar.Color color;
    private net.kyori.adventure.bossbar.BossBar.Overlay overlay;

    public BossBar(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void setProgress(float progress){
        this.progress = progress;
    }

    public void setTitle(Component component){
        msg = component;
    }

    public void setColor(net.kyori.adventure.bossbar.BossBar.Color color){
        this.color = color;
    }

    public void setOverlay(net.kyori.adventure.bossbar.BossBar.Overlay overlay){
        this.overlay = overlay;
    }

    public void show(){
        update();
    }

    private void update(){
        net.kyori.adventure.bossbar.BossBar bossBar = net.kyori.adventure.bossbar.BossBar.bossBar(msg, progress, color, overlay);
        player.showBossBar(bossBar);
    }
}