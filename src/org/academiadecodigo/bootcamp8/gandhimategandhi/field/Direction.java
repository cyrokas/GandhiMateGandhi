package org.academiadecodigo.bootcamp8.gandhimategandhi.field;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
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

    public Direction opposite() {

        Direction oppositeDirection = Direction.STOPPED;

        switch (this) {
            case UP:
                oppositeDirection = Direction.DOWN;
                break;
            case DOWN:
                oppositeDirection = Direction.UP;
                break;
            case LEFT:
                oppositeDirection = Direction.RIGHT;
                break;
            case RIGHT:
                oppositeDirection = Direction.LEFT;
                break;
            case UP_LEFT:
                oppositeDirection = Direction.DOWN_RIGHT;
                break;
            case UP_RIGHT:
                oppositeDirection = Direction.DOWN_LEFT;
                break;
            case DOWN_LEFT:
                oppositeDirection = Direction.UP_RIGHT;
                break;
            case DOWN_RIGHT:
                oppositeDirection = Direction.UP_LEFT;
                break;
        }

        return oppositeDirection;
    }
}
