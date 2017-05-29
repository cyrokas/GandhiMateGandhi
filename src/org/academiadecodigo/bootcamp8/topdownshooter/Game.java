package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.Menu.State;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;

import java.util.LinkedList;

/**
 * Created by codecadet on 24/05/17.
 */
public class Game {



    // Game state
    private State state;

    //Game field
    private Field field;

    //GameObjects list
    private LinkedList<GameObject> gameObjectList;                           //subject to change

    //Game delay
    private final int DELAY;

    //Testing
    private GameObject reg1;
    private Player playerOne;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        state = State.MENU;

    }

    //Game setup
    public void setup() {

        if (state == State.MENU) {
            field.setup();
            field.createRepresentation(70, 150, "/Users/codecadet/TopDownShooter/play button.jpg");
            field.createRepresentation(140, 150, "/Users/codecadet/TopDownShooter/quit button.jpg");

        } else {

            field.setup();

            //Test
            reg1 = GameObjectFactory.getNewEnemy(field);
            playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);

        }
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (true) {                                                      //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            gameRound();
        }

    }

    //Game Round
    public void gameRound() {

        playerOne.playRound();
    }


}