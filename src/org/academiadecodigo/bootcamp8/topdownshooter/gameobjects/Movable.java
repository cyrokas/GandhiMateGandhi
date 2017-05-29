package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 24/05/17.
 */
public interface Movable {

    void playRound();           //call move, direction
    Direction chooseDirection();
    void move(Direction direction);

}
