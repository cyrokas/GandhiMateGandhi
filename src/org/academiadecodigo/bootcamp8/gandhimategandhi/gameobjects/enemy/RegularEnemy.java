package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;

public class RegularEnemy extends Enemy {

    public RegularEnemy(AbstractPosition pos, FieldPosition playerpos) {

        super(10, pos, 1, playerpos); // health 10, speed 2
    }
}
