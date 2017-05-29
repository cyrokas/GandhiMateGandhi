package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldScore;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class SimpleGFXScore implements FieldScore {

    //Proprieties
    private Text text;                                                           // Text to show points
    private SimpleGFXField simpleGFXField;
    private int points;

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGFXScore(SimpleGFXField simpleGFXField, int points) {

        text = new Text(20, 0, "Points player1: " + points);//Creating Text with fix number to test
        this.simpleGFXField = simpleGFXField;
        this.points = points;                                          //Variable points

        //show();
        System.out.println("Show score");

    }

    @Override
    public void show(int points, PlayerNumber playerNumber) {
        text.setText(playerNumber + " player1: " + points);
        text.draw();
        System.out.println("Draw score");

    }

    @Override
    public void hide() {
        //text.delete();
    }
}
