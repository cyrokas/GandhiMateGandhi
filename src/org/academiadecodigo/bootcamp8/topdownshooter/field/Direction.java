package org.academiadecodigo.bootcamp8.topdownshooter.field;

/**
 * Created by codecadet on 24/05/17.
 */
public enum Direction {

    UP,
    DOWN,
    RIGHT,
    LEFT;

    public Direction oppositeDirection(){

        Direction opposite=null;

        switch (this){
            case UP:
                opposite=Direction.DOWN;
                break;
            case DOWN:
                opposite=Direction.UP;
                break;
            case LEFT:
                opposite=Direction.RIGHT;
                break;
            case RIGHT:
                opposite=Direction.LEFT;
                break;
        }
        return opposite;
    }

}
