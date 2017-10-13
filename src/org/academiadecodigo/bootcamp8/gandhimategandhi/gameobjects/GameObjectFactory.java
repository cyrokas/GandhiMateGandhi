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

import java.util.ArrayList;
import java.util.LinkedList;

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

    public static void getNewRegularEnemy(Field field, FieldPosition playerpos, ArrayList<Enemy> enemies) {
        AbstractPosition pos = field.createRepresentation("resources/images/soldier_enemy/front1.png", true);

        final double NEW_ENEMY_CHANCE = 0.015;

        if (Math.random() < NEW_ENEMY_CHANCE) {
            enemies.add(new RegularEnemy(pos, playerpos));
        }
    }

    public static void createNewBonus(Field field, LinkedList<Bonus> bonuses) {
        final double BONUS_CHANCE = 0.002d;

        if (BONUS_CHANCE > Math.random()) {
            bonuses.add(new Bonus(field));
        }
    }

    public static Boss getNewBoss(Field field, FieldPosition playerpos) {
        AbstractPosition pos;
        pos = field.createRepresentation(0, Math.round(field.getColumns() / 2), "resources/images/boss/front1.png");
        return new Boss(pos, playerpos, field);
    }
}
