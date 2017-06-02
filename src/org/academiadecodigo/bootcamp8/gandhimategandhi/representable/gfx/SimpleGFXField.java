package org.academiadecodigo.bootcamp8.gandhimategandhi.representable.gfx;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 */

public class SimpleGFXField implements Field {

    private final int PADDING = 10;
    private final int PADDING_TOP = 30;
    private int columns;
    private int rows;
    private Picture picture;
    private final int CELL_SIZE = 1;

    public SimpleGFXField(int rows, int columns) {

        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void setup() {

        picture = new Picture(PADDING, PADDING_TOP, "resources/images/padding.png");
        picture.draw();
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public int columnToX(int col) {
        return PADDING + col * CELL_SIZE;
    }

    public int rowToY(int row) {
        return PADDING_TOP + row * CELL_SIZE;
    }

    @Override
    public Picture getPicture() {
        return picture;
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
    public SimpleGFXStats createRepresentationStats(int points, int hitPoints, int maxHitpoints, int speed, int damage, int projectile) {
        return new SimpleGFXStats(points, hitPoints, maxHitpoints, speed, damage, projectile);
    }
}
