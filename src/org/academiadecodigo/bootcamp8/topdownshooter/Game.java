package org.academiadecodigo.bootcamp8.topdownshooter;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObjectFactory;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.Bonus;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.BonusType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

import java.util.ArrayList;
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

    //Testing
    //private GameObject reg1;
    private Player playerOne;
    private Enemy reg1;
    private Enemy reg2;
    private Projectile p1;
    private Bonus bonus;
    private final int BONUS_CHANCE = 2;
    private final int BONUS_DURATION;
    private ArrayList<Bonus> bonusList = new ArrayList<>();

    //Constructor
    public Game(int rows, int columns, int delay, FieldType fieldType) {

        field = FieldFactory.getNewField(fieldType, rows, columns);
        DELAY = delay;
        BONUS_DURATION = 500 * DELAY;

    }

    //Game setup
    public void setup() {

        field.setup();

        //Test
        //reg1 = GameObjectFactory.getNewEnemy(field);

        playerOne = GameObjectFactory.createNewPlayer(field, PlayerNumber.P1);

        //reg1= GameObjectFactory.getNewRegularEnemy(field);
        //reg2= GameObjectFactory.getNewRegularEnemy(field);
        //p1 = new Projectile(playerOne, ProjectileType.FIRE);
    }

    //Game Loop
    public void gameLoop() throws InterruptedException {

        while (true) {                                                      //maybe change to playerAlive OR lastBoss dead

            Thread.sleep(DELAY);

            if (BONUS_CHANCE > (int) (Math.random() * 100) * DELAY) {

                bonusList.add(GameObjectFactory.createNewBonus(field, DELAY));

            }

            gameRound();

        }

    }

    //Game Round
    public void gameRound() {


        for (Bonus b : bonusList) {

            if (b.isActive()) {

                b.playRound();

            }

        }

        playerOne.playRound();

        //reg1.move(reg1.chooseDirection(reg2.getPos()),1);

        //p1.playRound();


    }


}