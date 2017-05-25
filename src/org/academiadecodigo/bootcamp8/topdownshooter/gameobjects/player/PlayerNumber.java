package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;


/**
 * Created by codecadet on 25/05/17.
 */
public enum PlayerNumber {

    P1(PlayerType.P1, KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D),
    P2(PlayerType.P2, KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT);

    private int up;
    private int down;
    private int left;
    private int right;

    private PlayerType playerType;

    PlayerNumber(PlayerType carType, int up, int down, int left, int right) {

        this.playerType = carType;

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

    }

    public PlayerType getPlayerType() {

        return playerType;
    }

    public int getUp() {

        return up;
    }

    public int getDown() {

        return down;
    }

    public int getLeft() {

        return left;
    }

    public int getRight() {

        return right;
    }

}
