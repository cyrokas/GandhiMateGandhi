package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * <Academia de Código_>
 *
 * Created by
 * <Code Cadet> João Portela
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> Robin Opinião
 */

public abstract class Enemy extends GameObject implements Movable, Hittable {
    private int health;
    private boolean dead;
    private Direction currentDirection;
    private AbstractPosition position;
    private Field field;
    private int speed;
    private FieldPosition playerPosition;
    private int enemyDamage = 2;
    private int recoilSpeed;

    public Enemy(int health, AbstractPosition position, int speed, FieldPosition playerPosition) {

        this.position = position;
        this.health = health;
        dead = false;
        this.speed = speed;
        this.playerPosition = playerPosition;
        recoilSpeed = speed * 40;
        currentDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];

        //test
        //System.out.println(currentDirection.toString());
        //System.out.println("health "+health);
    }


    //getters
    public int getHealth() {
        return health;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
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
                //hit(enemyDamage);

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
