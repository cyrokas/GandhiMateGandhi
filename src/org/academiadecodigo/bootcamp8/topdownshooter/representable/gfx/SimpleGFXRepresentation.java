package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.Representation;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 25/05/17.
 */
public class SimpleGFXRepresentation extends Representation {

    private Rectangle rectangle;
    private SimpleGFXField simpleGFXField;
    private Picture picture; //future implementation


    public SimpleGFXRepresentation(SimpleGFXField field){
        super((int)(Math.random()*field.getRows()), (int)(Math.random()*field.getColumns()), field);
        simpleGFXField=field;
        rectangle=new Rectangle(field.columntoX(super.getCol()), field.rowToY(super.getRow()), field.getCELL_SIZE(),
                field.getCELL_SIZE());
        show();
    }

    public SimpleGFXRepresentation(int row, int col, SimpleGFXField field){
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
        int colIni=simpleGFXField.columntoX(super.getCol());
        int rowIni=simpleGFXField.rowToY(super.getRow());
        super.moveInDirection(direction,distance);
        int colEnd=simpleGFXField.columntoX(super.getCol());
        int rowEnd=simpleGFXField.rowToY(super.getRow());

        rectangle.translate(colEnd-colIni, rowEnd-rowIni);
    }

}
