package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 */

public class RegularEnemy extends Enemy {

    public RegularEnemy(AbstractPosition pos, FieldPosition playerpos) {

        super(10, pos, 2, playerpos); // health=10, speed=2
    }
}
