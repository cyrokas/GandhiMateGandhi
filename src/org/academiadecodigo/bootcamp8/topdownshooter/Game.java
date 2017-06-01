package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.topdownshooter.state.Menu;
import org.academiadecodigo.bootcamp8.topdownshooter.state.State;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
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

    //Game delay
    private final int DELAY;

    //Bonus properties
    private final int BONUS_CHANCE = 2;
    private final int BONUS_DURATION = 2000;        //TEST

    //Enemy properties
    private LinkedList<Bonus> bonusList = new LinkedList<>();
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private int maxEnemiesOnScreen = 5;                            // ?????
    private boolean bossStage = false;
    private Boss boss;
    private int enemiesBetweenBoss=15;
    private double enemyOddPerRound=0.015;

    private Player playerOne;

    private Menu menu;
    private State state;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        //BONUS_DURATION = 500 * DELAY;
        state = State.MENU;

    }

    public void menu() throws InterruptedException {


        menu = new Menu(field);

        while (state == State.MENU) {       //while state != quit
            state = menu.getState();

            if (state == State.GAME) {
                menu.getFieldPosition().hide();
                setup();
            }

            //if story

            Thread.sleep(50);
        }
    }

    //Game setup
    public void setup() throws InterruptedException {

        field.setup();

        playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);

        //Enemy reg1 = GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition());


        //playerOne.getFieldPosition().hide();
        //reg1.getPosition().hide();
        gameLoop();

    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        //playerOne.getFieldPosition().show();


        while (!playerOne.isDead()) {                                                      //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            if (bossStage) {
                bossRound();
            } else {
                gameRound();
            }

        }

        //gameOver ---------------

        //System.exit(1);             //Close the program when player die

    }

    //Game Round
    public void gameRound() {

        //TESTING

        bonusRound();

        playerOne.playRound();

        projectileRound(true);


        //REFACTOR THIS --------------------------------------------------------------------------------
        if (!bossStage) {
            double enemyodds = Math.random();
            if (enemyArrayList.size() < maxEnemiesOnScreen) {
                if (enemyodds < enemyOddPerRound) {
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
        }
        // _______________________________________________________________________________________________

        checkEnemyBonusInteraction();
        checkPlayerBonusInteraction();

    }

    public void bossRound() {
        enemyArrayList.clear();
        bonusRound();
        playerOne.playRound();
        projectileRound(true);
        if (!boss.isDead()) {
            boss.playRound();
            projectileRound(false);
            if (boss.getPosition().isColliding(playerOne.getFieldPosition())) {
                playerOne.hit(boss.getEnemyDamage());
            }
        }
        checkPlayerBonusInteraction();
    }

    private void bonusRound() {
        //Chance per image update
        final int CHANCE_PER_TURN = 500;

        //Chance to create bonus
        if (BONUS_CHANCE > (int) (Math.random() * CHANCE_PER_TURN)) {
            bonusList.add(GameObjectFactory.createNewBonus(field/*, DELAY*/));
        }

        Iterator<Bonus> iterator = bonusList.iterator();

        while (iterator.hasNext()) {
            Bonus b = iterator.next();
            if (b.isActive()) {
                b.playRound();
            }
        }

        while (iterator.hasNext()) {
            Bonus b = iterator.next();
            if (!b.isActive()) {
                iterator.remove();
            }
        }

       /* for (Bonus b : bonusList) {
            if (b.isActive()) {
                b.playRound();
            }
        } */
    }

    private void projectileRound(boolean playerorboss) {

        Iterator<Projectile> it;

        if (playerorboss) {
            it = playerOne.iterator();
        } else {
            it = boss.iterator();
        }

        while (it.hasNext()) {
            Projectile p = it.next();
            p.playRound();
            if (playerorboss) {
                if (!bossStage) {
                    if (checkProjectileEnemyCollision(p)) {
                        it.remove();
                    }
                    if (bossStage) {
                        return;
                    }
                } else {
                    if (checkProjectileBossCollision(p)) {
                        it.remove();
                    }
                }
            } else {
                if (checkProjectilePlayerCollision(p)) {
                    it.remove();
                }
            }
        }
    }

    //REFACTOR THIS
    private boolean checkProjectileEnemyCollision(Projectile p) {
        boolean collided = false;

        for (int i = 0; i < enemyArrayList.size(); i++) {

            Enemy enemy = enemyArrayList.get(i);

            if (p.getFieldPosition().isColliding(enemy.getPosition())) {
                enemy.hit(p.getProjectileDamage());
                p.getFieldPosition().hide();
                collided = true;

                if (enemy.isDead()) {
                    playerOne.addPoints();
                    enemyArrayList.remove(enemy);
                }
            }
            if (playerOne.getPoints() % enemiesBetweenBoss == 0 && playerOne.getPoints() > 0) {
                bossStage = true;
                for (Enemy e : enemyArrayList) {
                    e.getPosition().hide();
                }
                boss = GameObjectFactory.getNewBoss(field, playerOne.getFieldPosition());
                return collided;
            }
        }
        return collided;
    }

    private boolean checkProjectileBossCollision(Projectile p) {
        boolean collided = false;
        if (p.getFieldPosition().isColliding(boss.getPosition())) {
            boss.hit(p.getProjectileDamage());
            p.getFieldPosition().hide();
            collided = true;

            if (boss.isDead()) {
                playerOne.addPoints();
                bossStage = false;
                boss.getPosition().hide();
                boss.clearProjectileList();
            }
        }
        return collided;
    }

    private boolean checkProjectilePlayerCollision(Projectile p) {
        boolean collided = false;
        if (p.getFieldPosition().isColliding(playerOne.getFieldPosition())) {
            playerOne.hit(p.getProjectileDamage());
            p.getFieldPosition().hide();
            collided = true;
        }
        return collided;
    }

    private void checkEnemyBonusInteraction() {

        Iterator<Bonus> bonusIterator = bonusList.iterator();
        Iterator<Enemy> enemyIterator = enemyArrayList.iterator();

        while (bonusIterator.hasNext()) {
            Bonus b = bonusIterator.next();

            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();

                if (b.getFieldPosition().isColliding(e.getPosition())) {
                    b.consume();
                    b.getFieldPosition().hide();
                }
            }

        }

    }


    private void checkPlayerBonusInteraction() {

        Iterator<Bonus> iterator = bonusList.iterator();

        FieldPosition playerPosition = playerOne.getFieldPosition();

        while (iterator.hasNext()) {
            Bonus b = iterator.next();

            if (playerPosition.isColliding(b.getFieldPosition())) {
                playerOne.powerUp(b.getBonusType());
                b.consume();
                b.getFieldPosition().hide();

            }
        }
    }
}