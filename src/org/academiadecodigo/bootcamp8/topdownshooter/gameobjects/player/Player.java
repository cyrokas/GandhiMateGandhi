package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Movable;
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

    private int playerSpeed = 5;
    private int playerHitpoints = 100;
    private int playerDamage = 1;

    private final int MAX_PROJECTILES = 10;
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

    //Constructor
    public Player(Field field, PlayerNumber playerNumber, int delay) {

        //cooldown = delay / 10; -------------------
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

        keyboardControllerConfiguration();
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
        return projectileList.size() < MAX_PROJECTILES;
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
            fieldPosition.moveInDirection(newDirection);
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

    public int getPlayerDamage() {
        return playerDamage;
    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectileList.iterator();
    }
}

