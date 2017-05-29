package org.academiadecodigo.bootcamp8.topdownshooter;

import apple.laf.JRSUIConstants;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet>
 * <Code Cadet>
 */

public class HitDetection {

    public static void checkEnemyVsPlayer(LinkedList<Enemy> enemyLinkedList, Player player){
        for (Enemy e:enemyLinkedList){
            //if(e.getPos().equals(player.))
        }
    }

    public static void checkProjectileVsEnemy(LinkedList<Projectile> projectileLinkedList,
                                              LinkedList<Enemy> enemyLinkedList){

    }

    public static void checkBonusVsPlayer(LinkedList<Bonus> bonusLinkedList, Player player){

    }

}
