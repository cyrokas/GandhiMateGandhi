package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;

/**
 * Created by codecadet on 24/05/17.
 */
public class GameObjectFactory {

    public static Enemy getNewEnemy(Field field){
        return new RegularEnemy(field.createRepresentation());
    }

}
