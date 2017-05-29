package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 28/05/17.
 */
public class Score {

    //Proprieties
    private int points;                                                             //Points for each player
    private Field field;
    private FieldPosition fieldPosition;
    private String textMessage;

    //Constructor
    public Score(Field field){

        points = 0;
        this.field = field;
        this.fieldPosition = field.createRepresentationScore();
        textMessage = "P1 points : " + points;

    }

    public void incrementPoints(){
        points++;
        System.out.println("Player1 points: " + points);
    }


    //Getters
    //Getter to get points if needed
    public int getPoints(){
        return points;
    }

    public void textMessage(){

    }
}
