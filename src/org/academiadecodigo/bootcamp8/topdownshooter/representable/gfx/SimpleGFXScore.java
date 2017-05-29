package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 28/05/17.
 */
public class SimpleGFXScore implements FieldPosition {

    //Proprieties
    private Text text;                                                           // Text to show points
    private SimpleGFXField simpleGFXField;

    //Constructor
    public SimpleGFXScore(SimpleGFXField simpleGFXField){

        text = new Text(20,0,"Points player1: ");                                //Creating Text with fix number to test
        this.simpleGFXField = simpleGFXField;

        show();
        System.out.println("Show score");

    }


    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public void setPosition(int row, int column) {

    }

    @Override
    public void moveInDirection(Direction direction) {


    }

    @Override
    public void show() {
        text.draw();
        System.out.println("Draw score");

    }

    @Override
    public void hide() {
        //text.delete();
    }

    @Override
    public boolean equals(FieldPosition position) {
        return false;
    }

    @Override
    public boolean isEdge() {
        return false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }
}
