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

    private final int INITIAL_HITPOINTS = 50;
    private final int INITIAL_SPEED = 2;
    private final int INITIAL_DAMAGE = 1;
    private final int MAX_SPEED = 5;
    private final int MIN_SPEED = 1;

    private int playerSpeed;
    private int playerHitpoints;
    private int maxHitpoints;
    private int playerDamage;

    private final int COOLDOWN = 4;
    private int roundCounter = 0;
    private int maxProjectiles;
    private Stats playerStats;

    private LinkedList<Projectile> projectileList = new LinkedList<>();                       //Projectile list
    private ProjectileType projectileType = ProjectileType.FIRE;


    private PlayerNumber playerNumber;
    private Direction playerDirection;                                                       //Direction player is moving to
    private Direction facingDirection;                                                       //Direction player is facing

    private Field field;
    private FieldPosition fieldPosition;
    private final int HEIGHT;
    private final int WIDTH;

    private KeyboardController keyboardController;

    //Constructor
    public Player(Field field, PlayerNumber playerNumber) {

        this.field = field;

        int fieldCenterRow = field.getRows() / 2;
        int fieldCenterColumn = field.getColumns() / 2;

        //Instantiate representation centered in the field
        this.fieldPosition = field.createRepresentation(fieldCenterRow, fieldCenterColumn, playerNumber.getPlayerType().getImage());

        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();

        playerHitpoints = INITIAL_HITPOINTS;
        maxHitpoints = playerHitpoints;
        playerDamage = INITIAL_DAMAGE;
        playerSpeed = INITIAL_SPEED;
        maxProjectiles = 10;

        this.playerNumber = playerNumber;
        //Choose random direction
        initialDirection();

        facingDirection = playerDirection;
        playerHitpoints = INITIAL_HITPOINTS;

        keyboardControllerConfiguration();

        playerStats = new Stats(field, playerHitpoints, maxHitpoints, playerSpeed, playerDamage, maxProjectiles);                           //Creating Score
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
            //playerStats.removeHitPoints(playerHitpoints, maxHitpoints);

            if (playerHitpoints <= 0) {
                return;
            }
        }

        System.out.println(playerHitpoints);
    }

    @Override
    public boolean isDead() {
        return playerHitpoints == 0;
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
        return roundCounter % COOLDOWN == 0;
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
            fieldPosition.moveInDirection(newDirection, this, keyboardController.isKiting());
        }
    }

    private void reload() {
        Iterator<Projectile> iterator = iterator();

        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            if (!p.isActive()) {
                iterator.remove();
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
        playerStats.addPoints();
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void powerUp(BonusType bonusType) {

        switch (bonusType) {
            case FIRE:
                maxProjectiles += bonusType.getMultiplier();
                projectileType = ProjectileType.FIRE;
                break;
            case WIND:
                for (int i = 0; i < bonusType.getMultiplier(); i++) {
                    playerSpeed++;
                    if (playerSpeed > MAX_SPEED) {
                        playerSpeed = MAX_SPEED;
                        break;
                    }
                }
                projectileType = ProjectileType.WIND;
                break;
            case EARTH:
                playerDamage += bonusType.getMultiplier();
                playerSpeed -= bonusType.getMultiplier();
                if (playerSpeed < MIN_SPEED) {
                    playerSpeed = MIN_SPEED;
                }
                projectileType = ProjectileType.EARTH;
                break;
            case WATER:
                maxHitpoints += bonusType.getMultiplier();
                projectileType = ProjectileType.WATER;
                break;
            case HEALTH:
                for (int i = 0; i < bonusType.getMultiplier(); i++) {
                    playerHitpoints++;

                    if (playerHitpoints > maxHitpoints) {
                        playerHitpoints = maxHitpoints;
                        break;
                    }
                }
                break;
        }
    }


    //Getter of points to creating highscore
    public int getPoints() {

        return playerStats.getPoints();

    }

    public void updateStats() {
        playerStats.showHitPoints(playerHitpoints, maxHitpoints);
        playerStats.showDamage(playerDamage);
        playerStats.showSpeed(playerSpeed);
        playerStats.showProjectiles(maxProjectiles);
    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectileList.iterator();
    }
}



