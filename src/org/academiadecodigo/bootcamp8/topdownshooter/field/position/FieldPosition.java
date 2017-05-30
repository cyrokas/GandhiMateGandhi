package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 */

public interface FieldPosition {

    int getColumn();

    int getRow();

    void setPosition(int row, int column);

    void moveInDirection(Direction direction);

    void show();

    void hide();

    boolean isColliding(FieldPosition position);                             //What's this?

    boolean isEdge();

    int getHeight();

    int getWidth();

    int getMaxRow();

    int getMaxColumn();

}
