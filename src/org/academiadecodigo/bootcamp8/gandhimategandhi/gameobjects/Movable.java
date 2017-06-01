package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 */

public interface Movable {

    Direction chooseDirection();

    void move(Direction direction);

}
