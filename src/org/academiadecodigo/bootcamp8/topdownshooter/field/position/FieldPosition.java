package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

/**
 * Created by codecadet on 25/05/17.
 */
public interface FieldPosition {

    public int getCol();

    public int getRow();

    public void setPos(int row, int col);

    public void show();

    public void hide();

    public boolean equals(FieldPosition position);

}
