package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;

/**
 * Created by codecadet on 25/05/17.
 */
public interface FieldPosition {

    int getColumn();

    int getRow();

    void setPosition(int row, int column);

    void moveInDirection(Direction direction);

    void show();

    void hide();

    boolean equals(FieldPosition position);                             //What's this?

    boolean isEdge();

    int getHeight();

    int getWidth();

}
