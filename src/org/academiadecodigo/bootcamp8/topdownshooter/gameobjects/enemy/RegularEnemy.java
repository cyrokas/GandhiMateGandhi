package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 25/05/17.
 */
public class RegularEnemy extends Enemy {

    public RegularEnemy(AbstractPosition pos){
        super(100, pos);
    }
}
