package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus.BonusType;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Tiago Santos
 */

public class Player extends GameObject implements Movable, Hittable, Iterable<Projectile> {

    private int playerSpeed = 2;
    private final int INITIAL_HITPOINTS = 50;
    private int playerHitpoints;
    private int playerDamage = 1;

    private int maxProjectiles = 10;
    private LinkedList<Projectile> projectileList = new LinkedList<>();                       //Projectile list
    private ProjectileType projectileType = ProjectileType.FIRE;
    private int roundCounter;
    private int cooldown = 4;

    private PlayerNumber playerNumber;
    private final int HEIGHT;
    private final int WIDTH;

    private Direction playerDirection;                                                       //Direction player is moving to
    private Direction facingDirection;                                                       //Direction player is facing
    private Field field;
    private FieldPosition fieldPosition;
    private KeyboardController keyboardController;


    private Stats stats;                                                                    //Score of player FOR TESTING PLAYER WILL EARN POINTS WITH MOVE

    //Constructor
    public Player(Field field, PlayerNumber playerNumber) {

        this.field = field;

        int fieldCenterRow = field.getRows() / 2;
        int fieldCenterColumn = field.getColumns() / 2;

        //Instantiate representation centered in the field
        this.fieldPosition = field.createRepresentation(fieldCenterRow, fieldCenterColumn, playerNumber.getPlayerType().getImage());

        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();
        this.playerNumber = playerNumber;
        //Choose random direction
        initialDirection();

        facingDirection = playerDirection;
        playerHitpoints = INITIAL_HITPOINTS;

        keyboardControllerConfiguration();

        stats = new Stats(field);                           //Creating Score
    }

    private void initialDirection() {

        Direction[] directions = Direction.values();                        //Array that contains all directions
        int random = (int) (Math.random() * directions.length - 1);         //Choose random direction from array, except stopped
        playerDirection = directions[random];
    }

    private void keyboardControllerConfiguration() {

        //Instantiate keyboardController
        keyboardController = new KeyboardController(playerDirection,
                playerNumber.getUp(), playerNumber.getDown(),
                playerNumber.getLeft(), playerNumber.getRight(),
                playerNumber.getShootFront(), playerNumber.getShootBack());

        //Configure keyboardController
        keyboardController.keyMapConfiguration();
    }

    @Override
    public void hit(int damage) {

        for (int i = 0; i < damage; i++) {
            playerHitpoints--;
            stats.removeHitPoints(playerHitpoints);

            if (playerHitpoints <= 0) {
                return;
            }
        }

    }

    @Override
    public boolean isDead() {

        return playerHitpoints <= 0;
    }

    @Override
    public void playRound() {

        //Reloads projectiles
        reload();

        //Move
        if (keyboardController.isMoving()) {
            move(chooseDirection());
        }

        //Shoot
        if (keyboardController.isShooting() && hasProjectiles() && notOnCooldown()) {
            projectileList.add(new Projectile(this, projectileType, keyboardController.isKiting()));
        }

        roundCounter++;
    }

    private boolean hasProjectiles() {
        return projectileList.size() < maxProjectiles;
    }

    private boolean notOnCooldown() {
        return roundCounter % cooldown == 0;
    }

    @Override
    public Direction chooseDirection() {

        Direction newDirection = keyboardController.getDirection();

        //Update facing direction if player isn't stopped
        if (newDirection != Direction.STOPPED) {
            facingDirection = newDirection;
        }

        return newDirection;
    }

    @Override
    public void move(Direction direction) {

        Direction newDirection = direction;
        playerDirection = newDirection;

        for (int i = 0; i < playerSpeed; i++) {
            fieldPosition.moveInDirection(newDirection, this);
            //if (colisionDetector)
        }
    }

    private void reload() {
        Iterator<Projectile> it = iterator();

        while (it.hasNext()) {
            Projectile p = it.next();
            if (!p.isActive()) {
                it.remove();
            }
        }
    }

    public Field getField() {
        return field;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public LinkedList<Projectile> getProjectileList() {
        return projectileList;
    }

    //Getter to addPoints when enemys died
    public void addPoints() {
        stats.addPoints();
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void powerUp(BonusType bonusType) {

        switch (bonusType) {
            case FIRE:
                maxProjectiles += bonusType.getMultiplier();
                break;
            case WIND:
                playerSpeed += bonusType.getMultiplier();
                break;
            case EARTH:
                playerDamage += bonusType.getMultiplier();
                playerSpeed -= bonusType.getMultiplier();
                if (playerSpeed < 1) {
                    playerSpeed = 1;
                }
                break;
            case WATER:
                playerHitpoints += bonusType.getMultiplier();
                break;
            case HEALTH:
                playerHitpoints += bonusType.getMultiplier();
                if (playerHitpoints > INITIAL_HITPOINTS) {
                    playerHitpoints = INITIAL_HITPOINTS;
                }

        }
    }


    //Getter of points to creating highscore
    public int getPoints() {

        return stats.getPoints();
    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectileList.iterator();
    }
}



