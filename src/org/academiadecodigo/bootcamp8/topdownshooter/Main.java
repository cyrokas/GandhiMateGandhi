package org.academiadecodigo.bootcamp8.topdownshooter;

/**
 * Created by codecadet on 24/05/17.
 */
public class Main {

    public static void main(String[] args) {

        int columns = 80;
        int rows = 30;
        int delay = 200;

        Game game = new Game(rows, columns, delay);
        game.setup();


    }
}
