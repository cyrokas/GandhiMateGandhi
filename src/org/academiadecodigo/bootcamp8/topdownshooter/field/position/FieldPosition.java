package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

/**
 * Created by codecadet on 25/05/17.
 */
public interface FieldPosition {


    int getCol();

    int getRow();

    void setPos(int row, int col);

    void show();

    void hide();

    boolean equals(FieldPosition position);

}
