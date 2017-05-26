package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;

/**
 * Created by codecadet on 25/05/17.
 */
public abstract class AbstractPosition implements FieldPosition {

    private int col;
    private int row;

    private Field field;

    public AbstractPosition(int row, int col, Field field) {

        this.row = row;
        this.col = col;

        this.field = field;

    }

    public Field getField() {
        return field;
    }

    @Override
    public void setPos(int row, int col) {

        this.row = row;
        this.col = col;

        show();
    }

    @Override
    public int getCol() {
        return col;
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

        return (col == pos.getCol() && row == pos.getRow());

    }

    public void moveDownRight(int distance) {

        int maxDiagonal = 0 ;

        if (col + distance < field.getColumns() && row + distance < field.getRows()) {
            maxDiagonal = distance;
        }
        else if (col + distance < field.getColumns()) {
            moveRight(distance);
            return;
        }
        else if (row + distance < field.getRows()) {
            moveDown(distance);
            return;
        }

        setPos(row + maxDiagonal, col + maxDiagonal);

    }

    public void moveDownLeft(int distance) {

        int maxDiagonal = 0;

        if (col - distance >= 0 && row + distance < field.getRows()) {
            maxDiagonal = distance;
        }
        else if (col - distance >= 0) {
            moveLeft(distance);
            return;
        }
        else if (row + distance < field.getRows()) {
            moveDown(distance);
            return;
        }

        setPos(row + maxDiagonal, col - maxDiagonal);
    }

    public void moveUpRight(int distance) {

        int maxDiagonal = 0;

        if (col + distance < field.getColumns() && row - distance >= 0) {
            maxDiagonal = distance;
        }
        else if (col + distance < field.getColumns()) {
            moveRight(distance);
            return;
        }
        else if (row - distance >= 0) {
            moveUp(distance);
            return;
        }

        setPos(row - maxDiagonal, col + maxDiagonal);
    }

    public void moveUpLeft(int distance) {

        int maxDiagonal = 0;

        if (col - distance >= 0 && row - distance >= 0) {
            maxDiagonal = distance;
        }
        else if (col - distance >= 0) {
            moveLeft(distance);
            return;
        }
        else if (row - distance >= 0) {
            moveUp(distance);
            return;
        }

        setPos(row - maxDiagonal, col - maxDiagonal);
    }

    public void moveUp(int distance) {

        int maxRowsUp;

        if (distance < getRow()) {
            maxRowsUp = distance;
        } else {
            maxRowsUp = getRow();
        }

        setPos(getRow() - maxRowsUp, getCol());
    }

    public void moveDown(int distance) {

        int maxRowsDown;

        if (distance > getField().getRows() - (getRow() + 1)) {
            maxRowsDown = getField().getRows() - (getRow() + 1);
        } else {
            maxRowsDown = distance;
        }

        setPos(getRow() + maxRowsDown, getCol());
    }

    public void moveLeft(int distance) {

        int maxRowsLeft;

        if (distance < getCol()) {
            maxRowsLeft = distance;
        } else {
            maxRowsLeft = getCol();
        }

        setPos(getRow(), getCol() - maxRowsLeft);
    }

    public void moveRight(int distance) {

        int maxRowsRight;

        if (distance > getField().getColumns() - (getCol() + 1)) {
            maxRowsRight = getField().getColumns() - (getCol() + 1);
        } else {
            maxRowsRight = distance;
        }

        setPos(getRow(), getCol() + maxRowsRight);
    }
}
