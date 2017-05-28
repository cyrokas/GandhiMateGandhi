package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Shellazar;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx.SimpleGFXPosition;

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

    public static Enemy getNewRegularEnemy(Field field, FieldPosition playerpos) {
        /*
        int row=-1;
        int col=-1;
        while (!(col == 0 || col==field.getColumns()-1 || row==0 || row==field.getRows()-1)){
           row = (int) (Math.random() * field.getRows());
           col = (int) (Math.random() * field.getColumns());
        }
        */
        AbstractPosition pos = field.createRepresentation("/Users/codecadet/TopDownShooter/1571779_k_152.jpg");
        return new RegularEnemy(pos, playerpos);
    }

    public static Enemy getNewShellazar(Field field, FieldPosition playerpos) {
        AbstractPosition pos = field.createRepresentation(0, Math.round(field.getColumns() / 2),
                "/Users/codecadet/TopDownShooter/pt01_07b_normal.jpg");
        return new Shellazar(pos, playerpos);
    }
}
