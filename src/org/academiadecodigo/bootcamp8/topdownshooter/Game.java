package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.RegularEnemy;
import org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx.SimpleGFXField;

import java.util.LinkedList;

/**
 * Created by codecadet on 24/05/17.
 */
public class Game {

    //Game field
    private Field field;

    //GameObjects list
    private LinkedList<GameObject> gameObjectList;                           //subject to change

    //Game delay
    private final int DELAY;

    private GameObject reg1;

    //Constructor
    public Game(int rows, int columns, int delay) {
        field = new SimpleGFXField(rows, columns);
        DELAY = delay;

    }

    //Game setup
    public void setup(){

        field.setup();

        //Test
        reg1= GameObjectFactory.getNewEnemy(field);
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (true) {                                                      //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            gameRound();
        }

    }

    //Game Round
    public  void gameRound() {

        //throw new UnsupportedOperationException();

    }




}