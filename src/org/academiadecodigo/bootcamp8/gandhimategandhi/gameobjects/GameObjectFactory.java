package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.PlayerNumber;

/**
 * Developed @ <Academia de Código_>
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
        AbstractPosition pos = field.createRepresentation("resources/images/soldier_enemy/front1.png", true);
        return new RegularEnemy(pos, playerpos);
    }

    public static Bonus createNewBonus(Field field) {
        return new Bonus(field);
    }

    public static Boss getNewBoss(Field field, FieldPosition playerpos) {
        AbstractPosition pos;
        pos = field.createRepresentation(0, Math.round(field.getColumns() / 2), "resources/images/boss/front1.png");
        return new Boss(pos, playerpos, field);
    }
}
