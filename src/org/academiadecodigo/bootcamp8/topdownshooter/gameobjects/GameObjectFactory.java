package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;

/**
 * Created by codecadet on 24/05/17.
 */
public class GameObjectFactory {

    public static Enemy getNewEnemy(Field field){
        return new RegularEnemy(field.createRepresentation());
    }

    public static Player createNewPlayer(Field field, PlayerNumber playerNumber) {

        return new Player(field.createRepresentation(), playerNumber);

    }

}
