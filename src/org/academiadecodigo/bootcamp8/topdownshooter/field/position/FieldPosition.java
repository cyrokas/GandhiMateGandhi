package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;

/**
 * Created by codecadet on 25/05/17.
 */
public interface FieldPosition {

    int getColumn();

    int getRow();

    void setPos(int row, int col);

    void moveInDirection(Direction direction, int speed);

    void show();

    void hide();

    boolean equals(FieldPosition position);

    boolean isEdge();

    int getHeight();

    int getWidth();

}
