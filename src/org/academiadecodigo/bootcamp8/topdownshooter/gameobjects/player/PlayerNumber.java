package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;


/**
 * Created by codecadet on 25/05/17.
 */
public enum PlayerNumber {

    P1(PlayerType.P1, KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_SPACE),
    P2(PlayerType.P2, KeyboardEvent.KEY_W, KeyboardEvent.KEY_S, KeyboardEvent.KEY_A, KeyboardEvent.KEY_D, KeyboardEvent.KEY_Z);

    private int[] keys;

    private PlayerType playerType;

    PlayerNumber(PlayerType carType, int up, int down, int left, int right, int shoot) {

        this.playerType = carType;
        keys = new int[] {up, down, left, right, shoot};
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

    public int getShoot() {
        return keys[4];
    }

}
