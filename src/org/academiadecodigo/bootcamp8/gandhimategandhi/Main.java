package org.academiadecodigo.bootcamp8.gandhimategandhi;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldType;

public class Main {

    public static void main(String[] args) {

        final int COLUMNS = 860;
        final int ROWS = 600;
        final int DELAY = 18;

        Game game = new Game(ROWS, COLUMNS, DELAY, FieldType.SIMPLE_GFX);

        try {
            game.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
