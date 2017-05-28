package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;

/**
 * Created by codecadet on 25/05/17.
 */
public abstract class AbstractPosition implements FieldPosition {

    private int column;
    private int row;

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
    public void setPosition(int row, int column) {

        this.row = row;
        this.column = column;
        show();
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
    public void moveInDirection(Direction direction) {

        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN_RIGHT:
                moveDownRight();
                break;
            case DOWN_LEFT:
                moveDownLeft();
                break;
            case UP_RIGHT:
                moveUpRight();
                break;
            case UP_LEFT:
                moveUpLeft();
                break;
            case STOPPED:
               break;

        }
    }

    @Override
    public boolean equals(FieldPosition position) {

        return (column == position.getColumn() && row == position.getRow());
    }

    private void moveDownRight() {

        if (column + 1 + getWidth() < field.getColumns() && row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column + 1);
            return;
        }

    }

    private void moveDownLeft() {

        if (column - 1 > 0 && row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column - 1);
            return;
        }

    }

    private void moveUpRight() {

        if (column + 1 + getWidth() < field.getColumns() && row - 1 > 0) {
            setPosition(row - 1, column + 1);
        }

    }

    private void moveUpLeft() {

        if (column - 1 > 0 && row - 1 > 0) {
            setPosition(row - 1, column - 1);
            return;
        }

    }

    private void moveUp() {

        if (row - 1 > 0) {
            setPosition(row - 1, column);
        }
    }

    private void moveDown() {

        if (row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column);
        }
    }

    public void moveLeft() {

        if (column - 1 > 0) {
            setPosition(row, column - 1);
        }
    }

    public void moveRight() {

        if (column + 1 + getWidth() < field.getColumns()) {
            setPosition(row, column + 1);
        }
    }

    @Override
    public boolean isEdge() {
        return column == 1 || column == field.getColumns() - 1 ||
                row == 1 || row == field.getRows() - 1;
    }


}
