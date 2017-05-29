package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Mobile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

import java.util.ArrayList;

/**
 * Created by codecadet on 24/05/17.
 */
public class Player extends GameObject implements Mobile, Hittable {

    private int playerSpeed = 5;
    private int playerHitpoints = 100;

    private final int MAX_PROJECTILES = 10;
    private int projectilesFired;                                                           //Fired projectile counter
    private ArrayList<Projectile> projectileList = new ArrayList<>();                       //Projectile list

    private PlayerNumber playerNumber;
    private final int HEIGHT;
    private final int WIDTH;

    private Direction playerDirection;                                                       //Direction player is moving to
    private Direction facingDirection;                                                       //Direction player is facing
    private Field field;
    private FieldPosition fieldPosition;

    private KeyboardController keyboardController;

    private Score score;                                                                    //Score of player FOR TESTING PLAYER WILL EARN POINTS WITH MOVE

    public Player(Field field, PlayerNumber playerNumber) {

        this.field = field;
        this.fieldPosition = field.createRepresentation(field.getRows() / 2, field.getColumns() / 2, playerNumber.getPlayerType().getImage()); //Instantiate representation centered in the field
        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();
        this.playerNumber = playerNumber;

        initialDirection();

        facingDirection = playerDirection;

        keyboardControllerConfiguration();

        score = new Score(field);                           //Creating Score
    }

    private void keyboardControllerConfiguration() {

        //Instantiate keyboardController
        keyboardController = new KeyboardController(playerDirection, playerNumber.getUp(),
                                                    playerNumber.getDown(), playerNumber.getLeft(),
                                                    playerNumber.getRight(), playerNumber.getShoot());

        //Configure keyboardController
        keyboardController.keyMapConfiguration();
    }

    private void initialDirection() {

        Direction[] directions = Direction.values();                        //Array that contains all directions
        int random = (int) (Math.random() * directions.length - 1);         //Generate random direction from array, except stopped
        playerDirection = directions[random];                               //Attribute random direction
    }

    @Override
    public void hit(int damage) {

        playerHitpoints -= damage;

    }

    @Override
    public boolean isDead() {
        return playerHitpoints <= 0;
    }

    @Override
    public void playRound() {

        if (keyboardController.isMoving()) {
            move(chooseDirection());
        }

        if (keyboardController.isShooting() && projectilesFired < MAX_PROJECTILES) {
            projectileList.add(new Projectile(this, ProjectileType.FIRE));
            projectilesFired++;
        }

    }

    @Override
    public Direction chooseDirection() {

        Direction newDirection = keyboardController.getDirection();

        if (newDirection != Direction.STOPPED) {
            facingDirection = newDirection;
            score.incrementPoints();                                                // Incrementetion points when he moves
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

    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }

    public void reload() {
        projectilesFired = 0;
    }

    public int getPoints(){
        return score.getPoints();
    }

}

