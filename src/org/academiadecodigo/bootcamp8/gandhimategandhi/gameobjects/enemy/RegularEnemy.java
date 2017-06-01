package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Robin Opinião
 * <Code Cadet> João Portela
 */

public class RegularEnemy extends Enemy {

    public RegularEnemy(AbstractPosition pos, FieldPosition playerpos) {

        super(10, pos, 1, playerpos); // health=10, speed=2
    }
}
