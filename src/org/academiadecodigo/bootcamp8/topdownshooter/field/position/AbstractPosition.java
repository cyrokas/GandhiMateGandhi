package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;

/**
 * Created by codecadet on 25/05/17.
 */
public abstract class AbstractPosition implements FieldPosition {

    private int column;
    private int row;


    private int maxDistance = 0;

    private Field field;

    public AbstractPosition(int row, int column, Field field) {

        this.row = row;
        this.column = column;

        this.field = field;


    }

    public Field getField() {
        return field;
    }

    @Override
    public void setPos(int row, int column) {

        this.row = row;
        this.column = column;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }


    @Override
    public void moveInDirection(Direction direction, int distance) {
        switch (direction) {
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
            case DOWN_RIGHT:
                moveDownRight(distance);
                break;
            case DOWN_LEFT:
                moveDownLeft(distance);
                break;
            case UP_RIGHT:
                moveUpRight(distance);
                break;
            case UP_LEFT:
                moveUpLeft(distance);
                break;
        }
    }

    @Override
    public boolean equals(FieldPosition pos) {

        return (column == pos.getColumn() && row == pos.getRow());
    }

    private void moveDownRight(int distance) {

        if (column + distance + getWidth() < field.getColumns() && row + distance + getHeight() < field.getRows()) {
            maxDistance = distance;
            setPos(row + maxDistance, column + maxDistance);
            show();
            return;
        }

        if (column + distance + getWidth() < field.getColumns()) {
            moveRight(distance);
            return;
        }

        moveDown(distance);
    }

    private void moveDownLeft(int distance) {

        if (column - distance >= 1 && row + distance + getHeight() < field.getRows()) {
            maxDistance = distance;
            setPos(row + maxDistance, column - maxDistance);
            show();
            return;
        }

        if (column - distance >= 1) {
            moveLeft(distance);
            return;
        }

        moveDown(distance);
    }

    private void moveUpRight(int distance) {

        if (column + distance + getWidth() < field.getColumns() && row - distance >= 1) {
            maxDistance = distance;
            setPos(row - maxDistance, column + maxDistance);
            show();
        }

        if (column + distance + getWidth() < field.getColumns()) {
            moveRight(distance);
            return;
        }

        moveUp(distance);
    }

    private void moveUpLeft(int distance) {

        if (column - distance - getHeight() >= 1 && row - distance >= 1) {
            maxDistance = distance;
            setPos(row - maxDistance, column - maxDistance);
            show();
            return;
        }

        if (column - distance + getWidth() >= 1) {
            moveLeft(distance);
            return;
        }

        moveUp(distance);
    }

    private void moveUp(int distance) {

        if (row - distance >= 1) {
            maxDistance = distance;
            setPos(row - maxDistance, column);
            show();
        }
    }

    private void moveDown(int distance) {

        if (row + distance + getHeight() < field.getRows()) {
            maxDistance = distance;
            setPos(row + maxDistance, column);
            show();
        }
    }

    public void moveLeft(int distance) {

        if (column - distance >= 1) {
            maxDistance = distance;
            setPos(row, column - maxDistance);
            show();
        }
    }

    public void moveRight(int distance) {

        if (column + distance + getWidth() < field.getColumns()) {
            maxDistance = distance;
            setPos(row, column + maxDistance);
            show();
        }
    }

    public boolean isEdge(){
        return (column == 0 || column==field.getColumns()-1 || row==0 || row==field.getRows()-1);
    }


}
