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
    private Field field;
    private FieldStats fieldStats;

    //Constructor
    public Stats(Field field) {

        points = 0;
        this.field = field;
        this.fieldStats = field.createRepresentationStats(points);

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


    //Getters
    //Getter to get points if needed
    public int getPoints() {
        return points;
    }

}
