package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum PlayerNumber {

    P1(PlayerType.P1, KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN,
            KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT,
            KeyboardEvent.KEY_Z, KeyboardEvent.KEY_X);

    private int[] keys;

    private PlayerType playerType;

    PlayerNumber(PlayerType playerType, int up, int down, int left, int right, int shootFront, int shootBack) {

        this.playerType = playerType;
        keys = new int[]{up, down, left, right, shootFront, shootBack};
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

    public int getShootFront() {
        return keys[4];
    }

    public int getShootBack() {
        return keys[5];
    }
}
