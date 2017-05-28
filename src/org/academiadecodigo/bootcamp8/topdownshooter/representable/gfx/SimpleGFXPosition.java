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

        int row = -1;
        int col = -1;
        while (!(col == 0 || col == field.getColumns() - 1 || row == 0 || row == field.getRows() - 1)) {
            row = (int) (Math.random() * field.getRows());
            col = (int) (Math.random() * field.getColumns());
        }

        /*
        super((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()), field);
        while (!this.isEdge()){
            this.setPos((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()));
        }
        */
        simpleGFXField = field;
        //rectangle = new Rectangle(field.columntoX(super.getColumn()),
        //        field.rowToY(super.getRow()), field.getCELL_SIZE(), field.getCELL_SIZE());

        picture = new Picture(field.columntoX(col), field.rowToY(row), image);
        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();
        int dx = 0;
        int dy = 0;

        if (field.rowToY(row) + HEIGHT > field.rowToY(field.getRows() - 1)) {
            dy = field.rowToY(field.getRows() - 1) - HEIGHT - field.rowToY(row);
            row = field.getRows() - 1 - Math.round(HEIGHT / field.getCELL_SIZE());
        }
        if (field.columntoX(col) + WIDTH > field.columntoX(field.getColumns() - 1)) {
            dx = field.columntoX(field.getColumns() - 1) - WIDTH - field.columntoX(col);
            col = field.getColumns() - 1 - Math.round(WIDTH / field.getCELL_SIZE());
        }
        setPos(row, col);

        picture.translate(dx, dy);

        show();

    }

    public SimpleGFXPosition(int row, int col, String image, SimpleGFXField field) {

        super(row, col, field);

        simpleGFXField = field;
        //rectangle = new Rectangle(field.columntoX(col), field.rowToY(row), field.getCELL_SIZE(),
        //        field.getCELL_SIZE());


        picture = new Picture(field.columntoX(col), field.rowToY(row), image);
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
    }

    @Override
    public void moveInDirection(Direction direction, int distance) {

        int initialColumn = simpleGFXField.columntoX(super.getColumn());
        int initialRow = simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction, distance);

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
        int rightedge = simpleGFXField.getColumns() - 1 - Math.round(WIDTH / simpleGFXField.getCELL_SIZE());
        int loweredge = simpleGFXField.getRows() - 1 - Math.round(HEIGHT / simpleGFXField.getCELL_SIZE());
        if (getColumn()==rightedge || getRow()==loweredge || getColumn() == 0 || getRow() == 0) {
            return true;
        }
        return false;
    }
}
