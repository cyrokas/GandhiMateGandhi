package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Hittable;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Mobile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

/**
 * Created by codecadet on 24/05/17.
 */
public class Player extends GameObject implements Mobile, Hittable {

    private int playerSpeed = 5;
    private final int HEIGHT;
    private final int WIDTH;
    private Direction playerDirection;
    private Field field;
    private FieldPosition fieldPosition;
    private final int MAX_PROJECTILES = 10;
    private int projectilesFired;
    private int playerDamage;

    private KeyboardController keyboardController;
    private PlayerNumber playerNumber;

    public Player(Field field, PlayerNumber playerNumber) {

        this.field = field;
        this.fieldPosition = field.createRepresentation(field.getRows() / 2, field.getColumns() / 2, playerNumber.getPlayerType().getImage());
        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();
        this.playerNumber = playerNumber;
        projectilesFired = 0;
        playerDamage = 1;

        initialDirection();

        keyboardControllerConfiguration();
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

        Direction[] directions = Direction.values();                    //Array that contains all directions
        int random = (int) (Math.random() * directions.length);         //Generate random direction from array
        playerDirection = directions[random];                           //Attribute random direction
    }

    @Override
    public void hit(int damage) {

        new Projectile(this, ProjectileType.FIRE).playRound();
        projectilesFired++;
        System.out.println("made one");

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public void playRound() {

        if (keyboardController.isMoving()) {
            move(chooseDirection(), playerSpeed);
        }

        if (keyboardController.isShooting() && projectilesFired <= MAX_PROJECTILES) {
           hit(playerDamage);
        }
    }

    @Override
    public Direction chooseDirection() {

        return keyboardController.getDirection();
    }

    @Override
    public void move(Direction direction, int speed) {

        Direction newDirection = direction;
        playerSpeed = speed;
        playerDirection = newDirection;

        for (int i = 0; i < speed; i++) {
            fieldPosition.moveInDirection(newDirection, 1);
        }
    }

    public Field getField() {
        return field;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }
}
