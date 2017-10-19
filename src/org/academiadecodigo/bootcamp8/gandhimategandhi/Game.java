package org.academiadecodigo.bootcamp8.gandhimategandhi;

import org.academiadecodigo.bootcamp8.gandhimategandhi.sfx.Sound;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
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

public class Game {

    private final Field field;
    private final int DELAY;

    private LinkedList<Bonus> bonuses;
    private ArrayList<Enemy> enemies;
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
        menu = new Menu(field);
        sound = new Sound("/resources/sound/DoomOST.wav");
        bonuses = new LinkedList<>();
        enemies = new ArrayList<>();
        bossStage = false;
    }

    public void start() throws InterruptedException {

        state = State.MENU;
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
            gameRound(bossStage);
        }

        Thread.sleep(2000);
        reset();
        start();
    }


    //TODO
    private void reset() {
        //Reset game status --------------------------------------------------------------------------------------------
        field.getPicture().delete();
        enemies.clear();
        bonuses.clear();
        playerOne.getStats().getStats().hide();
        playerOne = null;
        bossStage = false;

        if (boss != null) {
            boss.getField().getPicture().delete();
            boss = null;
        }

        sound.stop();
    }

    private void gameRound(boolean isBossRound) {

        if (!isBossRound) {
            GameObjectFactory.createNewBonus(field, bonuses);
        }

        bonusRound();
        playerOne.playRound();
        projectileRound(true);

        if (isBossRound) {
            bossRound();
        }

        if (!isBossRound) {
            createSoldier();
            soldierRound();
            enemyBonusInteraction();
        }

        playerBonusInteraction();
        playerOne.updateStats();
    }

    private void bossRound() {

        if (!boss.isDead()) {
            boss.playRound();
            projectileRound(false);
            boss.collidingWith(playerOne);
        }
    }

    private void soldierRound() {

        for (Enemy e : enemies) {
            e.playRound();
            enemyPlayerCollision(e);
        }
    }

    //TODO
    private void enemyPlayerCollision(Enemy enemy) {

        if (enemy.getPosition().isColliding(playerOne.getFieldPosition()) &&
                enemy.getRoundsAfterLastHit() >= enemy.getCooldown()) {
            playerOne.hit(enemy.getEnemyDamage());
            enemy.enterCooldownPhase();
        }
    }

    private void createSoldier() {

        if (enemies.size() < maxEnemiesOnScreen) {
            GameObjectFactory.getNewRegularEnemy(field, playerOne.getFieldPosition(), enemies);
        }
    }

    private void bonusRound() {

        for (Bonus b : bonuses) {
            b.playRound();
        }
    }

    //TODO
    private void projectileRound(boolean isPlayer) {

        Iterator<Projectile> iterator;

        iterator = isPlayer ? playerOne.iterator() : boss.iterator();

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

        Iterator<Enemy> iterator = enemies.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();

            if (projectile.getPosition().isColliding(enemy.getPosition())) {
                enemy.hit(projectile.getDamage());
                projectile.getPosition().hide();
                return true;
            }

            if (enemy.isDead()) {
                playerOne.addKill();
                iterator.remove();
            }
        }

        checkBossStage();
        return false;
    }

    //DISCUSS ----------------------------------------------------------------------------------------------------------
    private void checkBossStage() {

        if (playerOne.getPoints() % enemiesBetweenBoss == 0 && playerOne.getPoints() > 0) {
            bossStage = true;
            boss = GameObjectFactory.getNewBoss(field, playerOne.getFieldPosition());

            Iterator<Enemy> iterator = enemies.iterator();
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

        Iterator<Bonus> bonusIterator = bonuses.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();

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

        Iterator<Bonus> iterator = bonuses.iterator();

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

    public static enum State {
        MENU,
        GAME,
        QUIT
    }
}
