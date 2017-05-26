package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 25/05/17.
 */
public class SimpleGFXPosition extends AbstractPosition {

    private Rectangle rectangle;
    private SimpleGFXField simpleGFXField;
    private Picture picture; //future implementation


    public SimpleGFXPosition(SimpleGFXField field){

        super((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()), field);

        simpleGFXField = field;
        rectangle = new Rectangle(field.columntoX(super.getCol()),
                field.rowToY(super.getRow()), field.getCELL_SIZE(), field.getCELL_SIZE());
        show();
    }

    public SimpleGFXPosition(int row, int col, SimpleGFXField field){
        super(row, col, field);
        simpleGFXField=field;
        rectangle=new Rectangle(field.columntoX(col), field.rowToY(row), field.getCELL_SIZE(),
                field.getCELL_SIZE());
        show();
    }


    @Override
    public void show(){
        rectangle.draw();
        //picture.draw();
    }

    @Override
    public void hide(){
        rectangle.delete();
    }

    @Override
    public void moveInDirection(Direction direction, int distance){

        int initialColumn=simpleGFXField.columntoX(super.getCol());
        int initialRow=simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction,distance);

        int finalColumn=simpleGFXField.columntoX(super.getCol());
        int finalRow=simpleGFXField.rowToY(super.getRow());

        rectangle.translate(finalColumn-initialColumn, finalRow-initialRow);
    }

}
