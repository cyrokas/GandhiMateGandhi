package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 25/05/17.
 */
public class SimpleGFXPosition extends AbstractPosition {

    //private Rectangle rectangle;
    private SimpleGFXField simpleGFXField;
    private Picture picture; //future implementation

    private final int HEIGHT;
    private final int WIDTH;

    public SimpleGFXPosition(String image, SimpleGFXField field) {

        super((int) (Math.random() * field.getRows()), (int) (Math.random() * field.getColumns()), field);

        /*
        super((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()), field);
        while (!this.isEdge()){
            this.setPosition((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()));
        }
        */
        simpleGFXField = field;
        //rectangle = new Rectangle(field.columntoX(super.getColumn()),
        //        field.rowToY(super.getRow()), field.getCELL_SIZE(), field.getCELL_SIZE());

        picture = new Picture(field.columntoX(getColumn()), field.rowToY(getRow()), image);
        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();

    }

    public SimpleGFXPosition(int row, int columns, String image, SimpleGFXField field) {

        super(row, columns, field);

        simpleGFXField = field;
        //rectangle = new Rectangle(field.columntoX(col), field.rowToY(row), field.getCELL_SIZE(),
        //        field.getCELL_SIZE());

        picture = new Picture(field.columntoX(columns), field.rowToY(row), image);
        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();
        show();
    }


    @Override
    public void show() {
        //rectangle.draw();
        picture.draw();
    }

    @Override
    public void hide() {
        //rectangle.delete();
        picture.delete();
    }

    @Override
    public void moveInDirection(Direction direction) {

        int initialColumn = simpleGFXField.columntoX(super.getColumn());
        int initialRow = simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction);

        int finalColumn = simpleGFXField.columntoX(super.getColumn());
        int finalRow = simpleGFXField.rowToY(super.getRow());

        //rectangle.translate(finalColumn - initialColumn, finalRow - initialRow);
        picture.translate(finalColumn - initialColumn, finalRow - initialRow);
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public boolean isEdge() {
        int rightEdge = simpleGFXField.getColumns() - 1 - Math.round(WIDTH / simpleGFXField.getCELL_SIZE());
        int lowerEdge = simpleGFXField.getRows() - 1 - Math.round(HEIGHT / simpleGFXField.getCELL_SIZE());
        if (getColumn() == rightEdge || getRow() == lowerEdge || getColumn() == 1 || getRow() == 1) {
            return true;
        }
        return false;
    }
}
