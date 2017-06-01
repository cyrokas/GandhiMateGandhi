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
    private int points;                                                             //Points for each player
    private FieldStats fieldStats;

    //Constructor
    public Stats(Field field, int hitPoints, int maxHitPoints, int speed, int damage, int projectile) {

        points = 0;
        this.fieldStats = field.createRepresentationStats(points, hitPoints, maxHitPoints, speed, damage, projectile);
    }

    public void addPoints() {

        points++;
        fieldStats.showPoints(points);

    }

    public void showHitPoints(int playerHitPoints, int maxHitPoints) {

        fieldStats.showHitPoints(playerHitPoints, maxHitPoints);

    }

    public void showSpeed(int speed) {

        fieldStats.showSpeed(speed);

    }

    public void showDamage(int damage) {

        fieldStats.showDamage(damage);

    }


    public void showProjectiles(int projectiles) {

        fieldStats.showProjectiles(projectiles);

    }

    public FieldStats getFieldStats() {
        return fieldStats;
    }

    //Getters
    public int getPoints() {
        return points;
    }

}
