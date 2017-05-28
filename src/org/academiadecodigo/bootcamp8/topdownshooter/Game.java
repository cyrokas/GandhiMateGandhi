package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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

    //Testing
    //private GameObject reg1;
    private Player playerOne;
    private Enemy reg1;
    private Enemy reg2;
    private Projectile p1;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;

    }

    //Game setup
    public void setup() {

        field.setup();

        //Test
        //reg1 = GameObjectFactory.getNewEnemy(field);
        playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);

        reg1 = GameObjectFactory.getNewRegularEnemy(field);
        reg2 = GameObjectFactory.getNewRegularEnemy(field);
        //p1 = new Projectile(playerOne, ProjectileType.FIRE);
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

        int activeProjectiles = 0;


        playerOne.playRound();

        for (Projectile p : playerOne.getProjectileList()) {
            if (p.isActive()) {
                activeProjectiles++;
                p.playRound();
            }
        }

        if (activeProjectiles == 0) {
            playerOne.reload();
        }

    }
}