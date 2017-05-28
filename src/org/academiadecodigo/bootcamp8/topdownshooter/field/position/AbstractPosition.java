package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;

/**
 * Created by codecadet on 25/05/17.
 */
public abstract class AbstractPosition implements FieldPosition {

    private int column;                                     //Horizontal position
    private int row;                                        //Vertical position
    private Field field;                                    //Game field

    //Constructor
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

    //Move object in argument direction. Do nothing if direction is STOPPED
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

    @Override                                                                                                                   //What's this?
    public boolean equals(FieldPosition position) {
        return column == position.getColumn() && row == position.getRow();
    }

    //Can move right AND down
    private void moveDownRight() {

        if (column + 1 + getWidth() < field.getColumns() && row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column + 1);
        }
    }

    //Can move down AND left
    private void moveDownLeft() {

        if (column - 1 > 0 && row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column - 1);
        }
    }

    //Can move up AND right
    private void moveUpRight() {

        if (column + 1 + getWidth() < field.getColumns() && row - 1 > 0) {
            setPosition(row - 1, column + 1);
        }
    }

    //Can move up AND left
    private void moveUpLeft() {

        if (column - 1 > 0 && row - 1 > 0) {
            setPosition(row - 1, column - 1);
        }
    }

    //Can move up
    private void moveUp() {

        if (row - 1 > 0) {
            setPosition(row - 1, column);
        }
    }

    //Can move down
    private void moveDown() {

        if (row + 1 + getHeight() < field.getRows()) {
            setPosition(row + 1, column);
        }
    }

    //Can move left
    public void moveLeft() {

        if (column - 1 > 0) {
            setPosition(row, column - 1);
        }
    }

    //Can move right
    public void moveRight() {

        if (column + 1 + getWidth() < field.getColumns()) {
            setPosition(row, column + 1);
        }
    }

    //Detects field borders, without overlapping them
    @Override
    public boolean isEdge() {
        return column == 1 || column == field.getColumns() - 1 ||
                row == 1 || row == field.getRows() - 1;
    }
}
