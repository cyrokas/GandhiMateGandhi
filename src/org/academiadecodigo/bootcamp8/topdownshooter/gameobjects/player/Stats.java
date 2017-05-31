package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldStats;


/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class Stats {

    //Proprieties
    private int points;                                                             //Points for each player
    private FieldStats fieldStats;

    //Constructor
    public Stats(Field field, int hitpoints) {

        points = 0;
        this.fieldStats = field.createRepresentationStats(points, hitpoints);
    }

    public void addPoints() {

        fieldStats.hide();
        points++;
        fieldStats.showPoints(points);
    }

    public void removeHitPoints(int playerHitPoints) {

        fieldStats.hide();
        fieldStats.showHitPoints(playerHitPoints);
    }

    public void showSpeed(int speed){

    }

    public void showDamage(int damage){


    }

    public void showProjectiels(int projectiels){
    }

    //Getters
    //Getter to get points if needed
    public int getPoints() {
        return points;
    }

}
