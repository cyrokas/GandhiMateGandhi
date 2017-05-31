package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.BonusType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Shellazar;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Tiago Santos
 * <Code Cadet> João Portela>
 * <Code Cadet> Cyrille Feijó>
 */

public class GameObjectFactory {

    public static Player createNewPlayer(Field field, PlayerNumber playerNumber) {
        return new Player(field, playerNumber);
    }

    public static Enemy getNewRegularEnemy(Field field, FieldPosition playerpos) {

        AbstractPosition pos = field.createRepresentation("images/soldier_enemy/front1.png", true);
        return new RegularEnemy(pos, playerpos);
    }

    public static Bonus createNewBonus(Field field) {

        return new Bonus(field);

    }

    public static Enemy getNewShellazar(Field field, FieldPosition playerpos) {
        AbstractPosition pos = field.createRepresentation(0, Math.round(field.getColumns() / 2),
                "images/player/down1.png");
        return new Shellazar(pos, playerpos);
    }
}
