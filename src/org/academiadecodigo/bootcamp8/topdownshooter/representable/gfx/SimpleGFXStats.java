package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldStats;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class SimpleGFXStats implements FieldStats {

    //Proprieties
    private Text textPoints;                        // Text to show points
    private Text textHitPoints;                    // Text to show HitPoints
    private int points;

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGFXStats(int points) {

        textPoints = new Text(40, 10, "");             // X = 20 && Y = 0
        textHitPoints = new Text(740, 10, "");        // X = 10 && Y = 750

        this.points = points;

        textPoints.grow(10,5);
        textHitPoints.grow(10,5);

        showPoints(points);                 //Show Points
        showHitPoints(100);                 //Show HitPoints 100 is just for testing

    }

    @Override
    public void showPoints(int points) {

        textPoints.setText("Player points: " + points);
        textPoints.draw();

    }

    @Override
    public void showHitPoints(int hitPoints) {

        textHitPoints.setText("Player Health: " + hitPoints);
        textHitPoints.draw();
        System.out.println("Draw health");

    }

    @Override
    public void hide() {
        //text.delete();
    }
}
