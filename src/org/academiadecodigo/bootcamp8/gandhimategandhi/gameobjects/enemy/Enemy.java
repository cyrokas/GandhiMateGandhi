package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;

/**
 * <Academia de Código_>
 * Created by
 * <Code Cadet> João Portela
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> Robin Opinião
 * <Code Cadet> Filipe Santos Sá
 */

public abstract class Enemy extends GameObject implements Movable, Hittable {
    private int health;
    private boolean dead;
    private Direction currentDirection;
    protected AbstractPosition position;
    protected int speed;
    protected FieldPosition playerPosition;
    protected int enemyDamage = 1;
    protected int recoilSpeed;
    private final int COOLDOWN = 30;
    private int roundsAfterLastHit;

    public Enemy(int health, AbstractPosition position, int speed, FieldPosition playerPosition) {

        this.position = position;
        this.health = health;
        dead = false;
        this.speed = speed;
        this.playerPosition = playerPosition;
        recoilSpeed = speed * 40;
        currentDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
        roundsAfterLastHit = COOLDOWN;
    }

    //getters
    public int getHealth() {
        return health;
    }

    public AbstractPosition getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public int getRoundsAfterLastHit() {
        return roundsAfterLastHit;
    }

    public void enterCooldownPhase() {
        roundsAfterLastHit = 0;
    }

    public int getCOOLDOWN() {
        return COOLDOWN;
    }


    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void playRound() {
        if (!isDead() && !position.isColliding(playerPosition)) {
            move(chooseDirection());
        } else if (position.isColliding(playerPosition)) {
            moverecoil();
        }
        roundsAfterLastHit++;
    }

    @Override
    public void hit(int damage) {

        if (!dead) {
            health -= damage;
        }
        if (health <= 0) {
            dead = true;
            position.hide();
        }
    }

    //implement method depending on player position
    @Override
    public Direction chooseDirection() {

        Direction vertical = Direction.STOPPED;

        Direction horiz = Direction.STOPPED;

        if (playerPosition.getColumn() > position.getColumn()) {

            horiz = Direction.RIGHT;

        } else if (playerPosition.getColumn() < position.getColumn()) {
            horiz = Direction.LEFT;
        }

        if (playerPosition.getRow() > position.getRow()) {
            vertical = Direction.DOWN;

        } else if (playerPosition.getRow() < position.getRow()) {
            vertical = Direction.UP;
        }
        if (horiz == Direction.STOPPED) {
            return vertical;
        }

        if (vertical == Direction.STOPPED) {
            return horiz;
        }

        switch (horiz) {
            case RIGHT:
                if (vertical == Direction.DOWN) {
                    return Direction.DOWN_RIGHT;
                } else {
                    return Direction.UP_RIGHT;
                }
            case LEFT:
                if (vertical == Direction.DOWN) {
                    return Direction.DOWN_LEFT;
                } else {
                    return Direction.UP_LEFT;
                }
        }
        return null;
    }


    @Override
    public void move(Direction direction) {
        for (int i = 0; i < speed; i++) {
            position.moveInDirection(chooseDirection(), this);
            if (position.isColliding(playerPosition)) {
                direction = Direction.STOPPED;

                return;
            }
        }
    }

    public void moverecoil() {
        for (int i = 0; i < recoilSpeed; i++) {
            position.moveInDirection(chooseDirection().opposite(), this);
        }
    }

}
