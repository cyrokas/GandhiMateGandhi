package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus.BonusType;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile.ProjectileType;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Tiago Santos
 */

public class Player extends GameObject implements Movable, Hittable, Iterable<Projectile> {

    private final int INITIAL_HITPOINTS = 50;
    private final int INITIAL_SPEED = 3;
    private final int INITIAL_DAMAGE = 1;
    private final int MAX_SPEED = 5;
    private final int MIN_SPEED = 1;

    private int speed;
    private int hitpoints;
    private int maxHitpoints;
    private int damage;

    private final int COOLDOWN = 4;
    private int roundCounter = 0;
    private int maxProjectiles;
    private Stats stats;

    private LinkedList<Projectile> projectileList;
    private ProjectileType projectileType;

    private Direction playerDirection;
    private Direction facingDirection;

    private Field field;
    private FieldPosition fieldPosition;
    private KeyboardController keyboardController;

    //Constructor
    public Player(Field field, PlayerNumber playerNumber) {

        this.field = field;

        int fieldCenterRow = field.getRows() / 2;
        int fieldCenterColumn = field.getColumns() / 2;

        this.fieldPosition = field.createRepresentation(fieldCenterRow, fieldCenterColumn, playerNumber.getPlayerType().getImage());

        projectileList = new LinkedList<>();
        projectileType = ProjectileType.FIRE;

        hitpoints = INITIAL_HITPOINTS;
        maxHitpoints = hitpoints;
        damage = INITIAL_DAMAGE;
        speed = INITIAL_SPEED;
        maxProjectiles = 5;

        initialDirection();
        facingDirection = playerDirection;

        keyboardControllerConfiguration(playerNumber);

        stats = new Stats(field, hitpoints, maxHitpoints, speed, damage, maxProjectiles);                           //Creating Score
    }

    private void initialDirection() {
        Direction[] directions = Direction.values();
        int random = (int) (Math.random() * directions.length - 1);
        playerDirection = directions[random];
    }

    private void keyboardControllerConfiguration(PlayerNumber playerNumber) {

        keyboardController = new KeyboardController(playerDirection,
                playerNumber.getUp(), playerNumber.getDown(),
                playerNumber.getLeft(), playerNumber.getRight(),
                playerNumber.getShootFront(), playerNumber.getShootBack());

        keyboardController.keyMapConfiguration();
    }

    @Override
    public void hit(int damage) {
        for (int i = 0; i < damage; i++) {
            hitpoints--;

            if (hitpoints == 0) {
                return;
            }
        }
    }

    @Override
    public boolean isDead() {
        return hitpoints == 0;
    }

    @Override
    public void playRound() {

        reload();

        if (keyboardController.isMoving()) {
            move(chooseDirection());
        }

        if (keyboardController.isShooting() && hasProjectiles() && notOnCooldown()) {
            projectileList.add(new Projectile(this, projectileType, keyboardController.isKiting()));
        }

        roundCounter++;
    }

    private boolean hasProjectiles() {
        return projectileList.size() < maxProjectiles;
    }

    private boolean notOnCooldown() {
        return roundCounter % COOLDOWN == 0;
    }

    @Override
    public Direction chooseDirection() {
        Direction newDirection = keyboardController.getDirection();

        if (newDirection != Direction.STOPPED) {
            facingDirection = newDirection;
        }

        return newDirection;
    }

    @Override
    public void move(Direction direction) {

        playerDirection = direction;

        for (int i = 0; i < speed; i++) {
            fieldPosition.moveInDirection(direction, this, keyboardController.isKiting());
        }
    }

    private void reload() {
        Iterator<Projectile> iterator = iterator();

        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            if (!p.isActive()) {
                iterator.remove();
            }
        }
    }

    public Field getField() {
        return field;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public LinkedList<Projectile> getProjectileList() {
        return projectileList;
    }

    public void addKill() {
        stats.addKill();
    }

    public int getDamage() {
        return damage;
    }

    public void powerUp(BonusType bonusType) {

        switch (bonusType) {
            case FIRE:
                maxProjectiles += bonusType.getMultiplier();
                projectileType = ProjectileType.FIRE;
                break;
            case WIND:
                for (int i = 0; i < bonusType.getMultiplier(); i++) {
                    speed++;
                    if (speed > MAX_SPEED) {
                        speed = MAX_SPEED;
                        break;
                    }
                }
                projectileType = ProjectileType.WIND;
                break;
            case EARTH:
                damage += bonusType.getMultiplier();
                speed -= bonusType.getMultiplier();
                if (speed < MIN_SPEED) {
                    speed = MIN_SPEED;
                }
                projectileType = ProjectileType.EARTH;
                break;
            case WATER:
                maxHitpoints += bonusType.getMultiplier();
                projectileType = ProjectileType.WATER;
                break;
            case HEALTH:
                for (int i = 0; i < bonusType.getMultiplier(); i++) {
                    hitpoints++;

                    if (hitpoints > maxHitpoints) {
                        hitpoints = maxHitpoints;
                        break;
                    }
                }
                break;
        }
    }

    public Stats getStats() {
        return stats;
    }

    public void updateStats() {
        stats.showHitPoints(hitpoints, maxHitpoints);
        stats.showDamage(damage);
        stats.showSpeed(speed);
        stats.showProjectiles(maxProjectiles);
    }

    public int getPoints() {
        return stats.getKillCount();
    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectileList.iterator();
    }
}



