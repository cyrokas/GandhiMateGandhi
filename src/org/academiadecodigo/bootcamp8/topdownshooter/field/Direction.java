package org.academiadecodigo.bootcamp8.topdownshooter.field;

/**
 * Created by codecadet on 24/05/17.
 */
public enum Direction {

    UP,
    DOWN,
    RIGHT,
    LEFT,
    UP_RIGHT,
    UP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT,
    STOPPED;

    public Direction oppositeDirection() {

        Direction opposite = null;

        switch (this) {
            case UP:
                opposite = Direction.DOWN;
                break;
            case DOWN:
                opposite = Direction.UP;
                break;
            case LEFT:
                opposite = Direction.RIGHT;
                break;
            case RIGHT:
                opposite = Direction.LEFT;
                break;
            case UP_LEFT:
                opposite = Direction.DOWN_RIGHT;
                break;
            case UP_RIGHT:
                opposite = Direction.DOWN_LEFT;
                break;
            case DOWN_LEFT:
                opposite = Direction.UP_RIGHT;
                break;
            case DOWN_RIGHT:
                opposite = Direction.UP_LEFT;
                break;
        }

        return opposite;
    }
}
