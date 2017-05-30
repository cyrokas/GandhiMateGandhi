package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Robin Opinião
 */

public class Game {

    //Game field
    private Field field;

    //GameObjects list
    private LinkedList<GameObject> gameObjectList;                           //subject to change

    //Game delay
    private final int DELAY;

    //Testing
    private Player playerOne;
    private Enemy reg1;
    private Enemy reg2;
    private Projectile p1;
    private Bonus bonus;
    private final int BONUS_CHANCE = 2;
    private final int BONUS_DURATION;
    private ArrayList<Bonus> bonusList = new ArrayList<>();
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private int maxEnemiesPerLevel = 1;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        BONUS_DURATION = 500 * DELAY;

    }

    //Game setup
    public void setup() {

        field.setup();

        //Test
        //reg1 = GameObjectFactory.getNewEnemy(field);

        playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);

        //reg1 = GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition());
        //reg2 = GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition());
        //reg1= GameObjectFactory.getNewRegularEnemy(field);
        //reg2= GameObjectFactory.getNewRegularEnemy(field);
        //p1 = new Projectile(playerOne, ProjectileType.FIRE);
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (!playerOne.isDead()) {                                                      //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            if (BONUS_CHANCE > (int) (Math.random() * 100) * DELAY) {

                bonusList.add(GameObjectFactory.createNewBonus(field, DELAY));

            }

            gameRound();

        }

    }

    //Game Round
    public void gameRound() {


        for (Bonus b : bonusList) {

            if (b.isActive()) {

                b.playRound();

            }

        }

        int activeProjectiles = 0;


        playerOne.playRound();

        for (Projectile p : playerOne.getProjectileList()) {
            if (p.isActive()) {
                activeProjectiles++;
                p.playRound();
                for (int i = 0; i < enemyArrayList.size(); i++) {
                    Enemy enemy = enemyArrayList.get(i);
                    if (p.getFieldPosition().isColliding(enemy.getPosition())) {
                        enemy.hit(p.getProjectileDamage());
                        //p.use();
                        if (enemy.isDead()) {
                            enemyArrayList.remove(enemy);
                            playerOne.addPoints();
                        }
                    }
                }
            }
        }

        if (activeProjectiles == 0) {
            playerOne.reload();
        }

        int enemyodds = (int) (Math.random() * 200);
        if (enemyArrayList.size() < maxEnemiesPerLevel) {
            if (enemyodds < 3) {
                enemyArrayList.add(GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition()));
            }
        }

        for (int i = 0; i < enemyArrayList.size(); i++) {
            Enemy e = enemyArrayList.get(i);
            e.playRound();
            if (e.getPosition().isColliding(playerOne.getFieldPosition())) {
                playerOne.hit(e.getEnemyDamage());
            }
        }
        //p1.playRound();

    }
}