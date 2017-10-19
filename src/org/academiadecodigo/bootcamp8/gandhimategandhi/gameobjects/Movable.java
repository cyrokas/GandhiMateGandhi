package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;

public interface Movable {

    Direction chooseDirection();

    void move(Direction direction);

}
