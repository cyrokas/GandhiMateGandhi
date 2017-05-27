package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;

/**
 * Created by codecadet on 24/05/17.
 */
public class GameObjectFactory {

    /*
    public static Enemy getNewEnemy(Field field){
        return new RegularEnemy(field.createRepresentation("/Users/codecadet/Desktop/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/player/1571779_k_152.jpg"));
    }

   */
    public static Player createNewPlayer(Field field, PlayerNumber playerNumber) {
        return new Player(field, playerNumber);
    }

    public static Enemy getNewRegularEnemy(Field field){
        AbstractPosition pos=field.createRepresentation("/Users/codecadet/Desktop/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/player/1571779_k_152.jpg");
        while (!pos.isEdge()){
            pos=field.createRepresentation("/Users/codecadet/Desktop/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/player/1571779_k_152.jpg");
        }
        return new RegularEnemy(pos);
    }



}
