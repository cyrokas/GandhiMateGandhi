package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldScore;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class SimpleGFXScore implements FieldScore {

    //Proprieties
    private Text text;                                           // Text to show points
    private SimpleGFXField simpleGFXField;
    private int points;

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGFXScore(SimpleGFXField simpleGFXField, int points) {

        text = new Text(20, 0, "");
        this.simpleGFXField = simpleGFXField;
        this.points = points;

        show(points);
        System.out.println("Show score");

    }

    @Override
    public void show(int points) {
        text.setText("Player points: " + points);
        text.draw();
        System.out.println("Draw score");

    }

    @Override
    public void hide() {
        //text.delete();
    }
}
