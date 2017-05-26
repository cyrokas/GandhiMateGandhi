package org.academiadecodigo.bootcamp8.topdownshooter.field;

import org.academiadecodigo.bootcamp8.topdownshooter.field.position.Representation;

/**
 * Created by codecadet on 25/05/17.
 */
public interface Field {

    void setup();
    int getColumns();
    int getRows();
    Representation createRepresentation();

}
