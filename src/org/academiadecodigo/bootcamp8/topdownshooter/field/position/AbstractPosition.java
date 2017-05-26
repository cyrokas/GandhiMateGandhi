package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;

/**
 * Created by codecadet on 25/05/17.
 */
public abstract class AbstractPosition implements FieldPosition{

    private int col;
    private int row;
    private Field field;

    public AbstractPosition(int row, int col, Field field){
        this.row=row;
        this.col=col;
        this.field=field;
    }

    public Field getField(){
        return field;
    }

    @Override
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
        show();
    }

    @Override
    public int getCol(){
        return col;
    }

    @Override
    public int getRow(){
        return row;
    }

    public void moveInDirection(Direction direction, int distance){
        switch (direction){
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
            case RIGHT:
                moveRight(distance);
                break;
        }
    }

    @Override
    public boolean equals(FieldPosition pos){

        return (col==pos.getCol() && row==pos.getRow());

    }

    public void moveUp(int dist){

        int maxRowsUp;

        if(dist<getRow()){
            maxRowsUp=dist;
        }
        else {
            maxRowsUp=getRow();
        }

        setPos( getRow()-maxRowsUp, getCol());
    }

    public void moveDown(int dist){

        int maxRowsDown;

        if(dist>getField().getRows()-(getRow()+1)){
            maxRowsDown=getField().getRows()-(getRow()+1);
        }
        else {
            maxRowsDown=dist;
        }

        setPos( getRow()+maxRowsDown, getCol());
    }

    public void moveLeft(int dist){

        int maxRowsLeft;

        if(dist<getCol()){
            maxRowsLeft=dist;
        }
        else {
            maxRowsLeft=getCol();
        }

        setPos(getRow(),getCol()-maxRowsLeft);
    }

    public void moveRight(int dist){

        int maxRowsRight;

        if(dist>getField().getColumns()-(getCol()+1)){
            maxRowsRight=getField().getColumns()-(getCol()+1);
        }
        else {
            maxRowsRight=dist;
        }

        setPos(getRow(),getCol() + maxRowsRight);
    }
}
