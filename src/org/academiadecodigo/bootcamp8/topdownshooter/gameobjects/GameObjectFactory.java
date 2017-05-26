package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;

/**
 * Created by codecadet on 24/05/17.
 */
public class GameObjectFactory {

    public static Enemy getNewRegularEnemy(Field field){
        FieldPosition pos=field.createRepresentation();
        while (!pos.isEdge()){
            pos=field.createRepresentation();
        }
        return new RegularEnemy(pos);
    }

}
