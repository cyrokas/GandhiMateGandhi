package org.academiadecodigo.bootcamp8.gandhimategandhi;

import org.academiadecodigo.bootcamp8.gandhimategandhi.sfx.Sound;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.state.Menu;
import org.academiadecodigo.bootcamp8.gandhimategandhi.state.State;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldFactory;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldType;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile.Projectile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Robin Opinião
 */

public class Game {

    private final Field field;
    private final int DELAY;

    private LinkedList<Bonus> bonusList;
    private ArrayList<Enemy> enemyArrayList;
    private int maxEnemiesOnScreen;
    private int enemiesBetweenBoss;

    private boolean bossStage;
    private Boss boss;
    private Player playerOne;

    private Menu menu;
    private State state;
    private Sound sound;

    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        sound = new Sound("/resources/sound/DoomOST.wav");
        bonusList = new LinkedList<>();
        enemyArrayList = new ArrayList<>();
        bossStage = false;
    }

    public void start() throws InterruptedException {

        state = State.MENU;
        menu = new Menu(field);

        state = menu.play();

        if (state == State.QUIT) {
            System.exit(0);
        }

        if (state == State.GAME) {
            setup();
        }
    }

    private void setup() throws InterruptedException {

        final int INITIAL_MAX_ENEMIES = 10;
        final int INITIAL_ENEMIES_BETWEEN_BOSS = 5;

        field.setup();
        sound.play(true);
        sound.loopIndef();

        playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);
        maxEnemiesOnScreen = INITIAL_MAX_ENEMIES;
        enemiesBetweenBoss = INITIAL_ENEMIES_BETWEEN_BOSS;

        gameLoop();
    }

    private void gameLoop() throws InterruptedException {

        while (!playerOne.isDead()) {
            Thread.sleep(DELAY);

            //TODO

            if (bossStage) {    //--------------------------------------------------------------------------------------
                bossRound();
            } else {
                boss = null;
                gameRound();
            }
        }

        Thread.sleep(2000);

        reset();
        start();
    }


    //TODO
    private void reset() {
        //Reset game status --------------------------------------------------------------------------------------------
        field.getPicture().delete();
        enemyArrayList.clear();
        bonusList.clear();
        playerOne.getStats().getStats().hide();
        playerOne = null;
        bossStage = false;

        if (boss != null) {
            boss.getField().getPicture().delete();
            boss = null;
        }

        //Go back to Menu
        menu = null;
        sound.stop();
    }

    private void gameRound() {

        createBonus();
        bonusRound();
        playerOne.playRound();
        projectileRound(true);

        if (!bossStage) {
            createSoldier();
            soldierRound();
        }

        soldierRound();
        enemyBonusInteraction();
        playerBonusInteraction();
        playerOne.updateStats();

    }

    private void createBonus() {

        final int BONUS_CHANCE = 1;
        final int CHANCE_PER_TURN = 500;

        if (BONUS_CHANCE > (int) (Math.random() * CHANCE_PER_TURN)) {
            bonusList.add(GameObjectFactory.createNewBonus(field));
        }
    }

    private void soldierRound() {

        for (Enemy e : enemyArrayList) {
            e.playRound();
            enemyPlayerCollision(e);
        }
    }

    //COOLDOWN NOT WORKING ---------------------------------------------------------------------------------------------
    private void enemyPlayerCollision(Enemy enemy) {

        if (enemy.getPosition().isColliding(playerOne.getFieldPosition()) && enemy.getRoundsAfterLastHit() >= enemy.getCOOLDOWN()) {
            playerOne.hit(enemy.getEnemyDamage());
            enemy.enterCooldownPhase();
        }
    }

    private void createSoldier() {

        final double NEW_ENEMY_CHANCE = 0.015;
        double newEnemyChance = Math.random();

        if (enemyArrayList.size() < maxEnemiesOnScreen && newEnemyChance < NEW_ENEMY_CHANCE) {
            enemyArrayList.add(GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition()));
        }
    }

    //DISCUSS THIS -----------------------------------------------------------------------------------------------------
    private void bossRound() {

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
        playerBonusInteraction();
        playerOne.updateStats();
    }

    private void bonusRound() {

        Iterator<Bonus> iterator = bonusList.iterator();

        while (iterator.hasNext()) {
            Bonus b = iterator.next();
            b.playRound();
        }
    }

    private void projectileRound(boolean isPlayer) {

        Iterator<Projectile> iterator;

        if (isPlayer) {
            iterator = playerOne.iterator();
        } else {
            iterator = boss.iterator();
        }

        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.playRound();

            if (isPlayer && !bossStage && projectileEnemyCollision(p)) {
                iterator.remove();
            }

            if (isPlayer && bossStage && projectileBossCollision(p)) {
                iterator.remove();
            }

            if (!isPlayer && projectilePlayerCollision(p)) {
                iterator.remove();
            }
        }

    }

    private boolean projectileEnemyCollision(Projectile projectile) {

        boolean collided = false;

        Iterator<Enemy> iterator = enemyArrayList.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();

            if (projectile.getPosition().isColliding(enemy.getPosition())) {
                collided = true;
                enemy.hit(projectile.getDamage());
                projectile.getPosition().hide();
            }

            if (enemy.isDead()) {
                playerOne.addKill();
                iterator.remove();
            }
        }

        checkBossStage();
        return collided;
    }

    //DISCUSS ----------------------------------------------------------------------------------------------------------
    private void checkBossStage() {

        if (playerOne.getPoints() % enemiesBetweenBoss == 0 && playerOne.getPoints() > 0) {
            bossStage = true;
            boss = GameObjectFactory.getNewBoss(field, playerOne.getFieldPosition());

            Iterator<Enemy> iterator = enemyArrayList.iterator();
            while (iterator.hasNext()) {
                Enemy e = iterator.next();
                e.getPosition().hide();
                iterator.remove();
            }
        }
    }

    private boolean projectileBossCollision(Projectile projectile) {

        final int BOSS_POINTS = 5;
        boolean collided = false;

        if (projectile.getPosition().isColliding(boss.getPosition())) {
            collided = true;
            boss.hit(projectile.getDamage());
            projectile.getPosition().hide();

            if (boss.isDead()) {

                for (int i = 0; i < BOSS_POINTS; i++) {
                    playerOne.addKill();
                }

                maxEnemiesOnScreen++;
                enemiesBetweenBoss++;

                bossStage = false;
                boss.getPosition().hide();
                boss.clearProjectileList();
            }
        }
        return collided;
    }

    private boolean projectilePlayerCollision(Projectile p) {

        boolean collided = false;

        if (p.getPosition().isColliding(playerOne.getFieldPosition())) {
            playerOne.hit(p.getDamage());
            p.getPosition().hide();
            collided = true;
        }
        return collided;
    }

    private void enemyBonusInteraction() {

        Iterator<Bonus> bonusIterator = bonusList.iterator();
        Iterator<Enemy> enemyIterator = enemyArrayList.iterator();

        while (bonusIterator.hasNext()) {
            Bonus b = bonusIterator.next();

            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();

                if (b.getPosition().isColliding(e.getPosition())) {
                    b.getPosition().hide();
                }
            }
        }
    }

    private void playerBonusInteraction() {

        Iterator<Bonus> iterator = bonusList.iterator();

        FieldPosition playerPosition = playerOne.getFieldPosition();

        while (iterator.hasNext()) {
            Bonus b = iterator.next();

            if (playerPosition.isColliding(b.getPosition())) {
                playerOne.powerUp(b.getBonusType());
                iterator.remove();
                b.getPosition().hide();
            }
        }
    }
}