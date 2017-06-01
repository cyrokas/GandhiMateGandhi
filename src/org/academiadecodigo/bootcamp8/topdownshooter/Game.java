package org.academiadecodigo.bootcamp8.topdownshooter;


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

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 *
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
    private final int BONUS_CHANCE = 1;                         //Chance of 1
    final int CHANCE_PER_TURN = 500;                            //Every 500 game rounds
    private LinkedList<Bonus> bonusList = new LinkedList<>();

    //Enemy properties
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private int maxEnemiesPerLevel = 10;

    private Player playerOne;

    private Menu menu;
    private State state;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        state = State.MENU;
    }

    public void start() throws InterruptedException {

        menu = new Menu(field);
        menu.getFieldPosition().show();

        while (state == State.MENU) {
            state = menu.getState();

            if (state == State.GAME) {
                menu.getFieldPosition().hide();
                menu = null;
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
        gameLoop();
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (!playerOne.isDead()) {

            Thread.sleep(DELAY);
            gameRound();
        }

        Thread.sleep(3000); //Sleep for 3 seconds, so player has a chance to see his score

        //Reset game status
        enemyArrayList.clear();
        bonusList.clear();
        playerOne.getPlayerStats().getFieldStats().hide();
        playerOne = null;
        state = State.MENU;

        //Go back to Menu
        start();
    }

    //Game Round
    public void gameRound() {

        bonusRound();
        playerOne.playRound();
        projectileRound();

        //REFACTOR THIS --------------------------------------------------------------------------------
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
                continue;
            }
        }
        // _______________________________________________________________________________________________

        checkEnemyBonusInteraction();
        checkPlayerBonusInteraction();
        playerOne.updateStats();

    }

    private void bonusRound() {

        //Chance to create bonus
        if (BONUS_CHANCE > (int) (Math.random() * CHANCE_PER_TURN)) {
            bonusList.add(GameObjectFactory.createNewBonus(field/*, DELAY*/));
        }

        //Play round of active bonuses
        Iterator<Bonus> iterator = bonusList.iterator();

        while (iterator.hasNext()) {
            Bonus b = iterator.next();
            if (b.isActive()) {
                b.playRound();
            }
        }

        //Remove inactive bonuses
        while (iterator.hasNext()) {
            Bonus b = iterator.next();
            if (!b.isActive()) {
                iterator.remove();
            }
        }
    }

    private void projectileRound() {

        Iterator<Projectile> it = playerOne.iterator();

        while (it.hasNext()) {
            Projectile p = it.next();
            p.playRound();
            if (checkProjectileEnemyCollision(p)) {
                it.remove();
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

                if(b.getFieldPosition().isColliding(e.getPosition())) {
                    bonusIterator.remove();
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
                iterator.remove();
                b.getFieldPosition().hide();
            }
        }
    }
}