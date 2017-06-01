package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 */

public class SimpleGFXField implements Field {

    private final int PADDING_LEFT = 20;
    private final int PADDING_TOP = 10;
    private int columns;
    private int rows;
    //private Rectangle field;
    private Picture field;
    private final int CELL_SIZE = 1;

    public SimpleGFXField(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void setup() {

        //field = new Rectangle(PADDING, PADDING, columns * CELL_SIZE, rows * CELL_SIZE);
        field = new Picture(PADDING_TOP, PADDING_LEFT, "images/padding.png");
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

    /*

public int getWidth() {
    return columns * CELL_SIZE;
}

public int getHeight() {
    return rows * CELL_SIZE;
}

*/
    public int getCellSize() {

        return CELL_SIZE;
    }

    /*
    public int getX() {
        return PADDING;
    }
    public int getY() {
        return PADDING;
    }
    */

    public int columnToX(int col) {

        return PADDING_LEFT + col * CELL_SIZE;
    }

    public int rowToY(int row) {

        return PADDING_TOP + row * CELL_SIZE;
    }

    @Override
    public AbstractPosition createRepresentation(String image, boolean edge) {

        return new SimpleGFXPosition(image, this, edge);
    }

    @Override
    public AbstractPosition createRepresentation(int row, int column, String image) {

        return new SimpleGFXPosition(row, column, image, this);
    }


    @Override
    public SimpleGFXStats createRepresentationStats(int points, int hitPoints,int maxHitpoints, int speed, int damage, int projectile) {

        return new SimpleGFXStats(points, hitPoints, maxHitpoints , speed, damage, projectile);
    }


}
