package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Robin Opinião
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int columns = 860;
        int rows = 600;
        int delay = 18;

        Game game = new Game(rows, columns, delay, FieldType.SIMPLE_GFX);
        game.setup();


    }
}
