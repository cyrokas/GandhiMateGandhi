package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;


/**
 * Created by codecadet on 25/05/17.
 */
public enum PlayerNumber {

    P1(PlayerType.P1, KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT),
    P2(PlayerType.P2, KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D);

    private int[] keys;

    private PlayerType playerType;

    PlayerNumber(PlayerType carType, int up, int down, int left, int right) {

        this.playerType = carType;
        keys = new int[] {up, down, left, right};
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getUp() {
        return keys[0];
    }

    public int getDown() {
        return keys[1];
    }

    public int getLeft() {
        return keys[2];
    }

    public int getRight() {
        return keys[3];
    }

}
