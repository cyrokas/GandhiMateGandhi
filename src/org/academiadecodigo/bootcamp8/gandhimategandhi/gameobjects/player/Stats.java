package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldStats;


/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Cyrille Feijó
 */

public class Stats {

    //Proprieties
    private int killCount;                                                             //Points for each player
    private FieldStats stats;

    //Constructor
    public Stats(Field field, int hitPoints, int maxHitPoints, int speed, int damage, int projectile) {
        killCount = 0;
        stats = field.createRepresentationStats(killCount, hitPoints, maxHitPoints, speed, damage, projectile);
    }

    public void addKill() {
        killCount++;
        stats.showPoints(killCount);
    }

    public void showHitPoints(int playerHitPoints, int maxHitPoints) {
        stats.showHitPoints(playerHitPoints, maxHitPoints);
    }

    public void showSpeed(int speed) {
        stats.showSpeed(speed);
    }

    public void showDamage(int damage) {
        stats.showDamage(damage);
    }

    public void showProjectiles(int projectiles) {
        stats.showProjectiles(projectiles);
    }

    public FieldStats getStats() {
        return stats;
    }

    public int getKillCount() {
        return killCount;
    }
}
