package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.representable.Representation;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


/**
 * Created by codecadet on 25/05/17.
 */
public class SimpleGFXField implements Field {

    private final  int PADDING = 10;
    private int columns;
    private int rows;
    private Rectangle field;
    private final int CELL_SIZE = 20;                             //for testing


    public SimpleGFXField(int rows, int columns) {

     this.rows = rows;
     this.columns = columns;

    }

    @Override
    public void setup() {
        field = new Rectangle(PADDING, PADDING, columns * CELL_SIZE, rows * CELL_SIZE);
        field.draw();
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public Representation createRepresentation() {
        return new SimpleGFXRepresentation();
    }

}
