package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Mobile;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;

/**
 * Created by codecadet on 24/05/17.
 */
public abstract class Enemy extends GameObject implements Mobile, Hittable {
    private int health;
    private boolean dead;
    private Direction currentDirection;
    private AbstractPosition pos;
    private Field field;
    private int speed;
    private FieldPosition playerpos;

    public Enemy(int health, AbstractPosition pos, int speed, FieldPosition playerpos) {

        this.pos = pos;
        this.health = health;
        dead = false;
        this.speed = speed;
        this.playerpos = playerpos;

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

    public AbstractPosition getPos() {
        return pos;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void playRound() {
        move(chooseDirection());

    }

    @Override
    public void hit(int damage) {

        if (!dead) {
            health -= damage;
        }
        if (health <= 0) {
            dead = true;
        }
    }

    //implement method depending on player position
    @Override
    public Direction chooseDirection() {

        Direction vertical = Direction.STOPPED;

        Direction horiz = Direction.STOPPED;

        if (playerpos.getColumn() > pos.getColumn()) {

            horiz = Direction.RIGHT;

        } else if (playerpos.getColumn() < pos.getColumn()) {
            horiz = Direction.LEFT;
        }
        if (playerpos.getRow() > pos.getRow()) {
            vertical = Direction.DOWN;

        } else if (playerpos.getRow() < pos.getRow()) {
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
            pos.moveInDirection(chooseDirection());
        }
    }
}
