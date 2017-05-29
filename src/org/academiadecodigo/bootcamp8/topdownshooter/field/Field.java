package org.academiadecodigo.bootcamp8.topdownshooter.field;

import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 */

public interface Field {

    void setup();

    int getColumns();

    int getRows();

    AbstractPosition createRepresentation(String image, boolean edge);

    AbstractPosition createRepresentation(int x, int y, String image);
}
