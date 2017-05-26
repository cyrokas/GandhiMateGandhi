package org.academiadecodigo.bootcamp8.topdownshooter.field;

import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 25/05/17.
 */
public interface Field {

    void setup();
    int getColumns();
    int getRows();
    FieldPosition createRepresentation();

}
