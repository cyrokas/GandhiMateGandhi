package org.academiadecodigo.bootcamp8.topdownshooter.field.position;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;

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

    void moveInDirection(Direction direction, GameObject gameObject);

    void moveInDirection(Direction direction, GameObject gameObject, boolean kitting);

    void show();

    void hide();

    boolean isColliding(FieldPosition position);

    boolean isEdge();

    int getHeight();

    int getWidth();

    int getMaxRow();

    int getMaxColumn();

}
