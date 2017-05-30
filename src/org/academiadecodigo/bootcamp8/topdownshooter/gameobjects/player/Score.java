package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldScore;


/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class Score {

    //Proprieties
    private int points;                                                             //Points for each player
    private Field field;
    private FieldScore fieldScore;
    private PlayerNumber playerNumber;

    //Constructor
    public Score(Field field, PlayerNumber playerNumber) {

        points = 0;
        this.field = field;
        this.playerNumber = playerNumber;
        this.fieldScore = field.createRepresentationScore(points);

    }

    public void addPoints() {
        fieldScore.hide();
        points++;
        fieldScore.show(points);
        System.out.println(playerNumber + " points: " + points);
    }


    //Getters
    //Getter to get points if needed
    public int getPoints() {
        return points;
    }

}
