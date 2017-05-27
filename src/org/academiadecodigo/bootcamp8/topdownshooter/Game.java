package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

import java.util.LinkedList;

/**
 * Created by codecadet on 24/05/17.
 */
public class Game {

    //Game field
    private Field field;

    //GameObjects list
    private LinkedList<GameObject> gameObjectList;   //subject to change
    private LinkedList<Enemy> enemyLinkedList;
    private LinkedList<Projectile> projectileLinkedList;
    private Player player;
    private LinkedList<Bonus> bonusLinkedList;


    //Game delay
    private final int DELAY;

    //Testing
    private Enemy reg1;
    private Enemy reg2;

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;

    }

    //Game setup
    public void setup(){

        field.setup();

        //Test
        reg1= GameObjectFactory.getNewRegularEnemy(field);
        reg2= GameObjectFactory.getNewRegularEnemy(field);
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (true) {     //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            //gameRound();

            reg1.move(reg1.chooseDirection(reg2.getPos()),1);
        }

    }

    //Game Round
    public  void gameRound() {

        //throw new UnsupportedOperationException();

    }




}