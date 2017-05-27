package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;

/**
 * Created by codecadet on 24/05/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        int columns = 460;
        int rows = 300;
        int delay = 100;

        Game game = new Game(rows, columns, delay, FieldType.SIMPLE_GFX);
        game.setup();
        game.gameLoop();
        
    }
}
