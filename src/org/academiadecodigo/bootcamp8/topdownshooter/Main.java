package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;

/**
 * Created by codecadet on 24/05/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        int columns = 860;
        int rows = 600;
        int delay = 18;

        Game game = new Game(rows, columns, delay, FieldType.SIMPLE_GFX);
        game.setup();
        game.gameLoop();

    }
}
