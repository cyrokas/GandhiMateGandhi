package org.academiadecodigo.bootcamp8.gandhimategandhi;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldType;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Robin Opinião
 */

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
