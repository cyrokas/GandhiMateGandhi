package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.Mobile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;

/**
 * Created by codecadet on 24/05/17.
 */

public class Projectile implements Mobile {

    private int projectileSpeed;
    private boolean active = true;
    private ProjectileType projectileType;
    private FieldPosition fieldPosition;
    private Field field;
    private final int HEIGHT;
    private final int WIDTH;
    private Direction direction;

    public Projectile(Player player, ProjectileType projectileType) {

        field = player.getField();
        this.projectileType = projectileType;
        fieldPosition = field.createRepresentation(player.getFieldPosition().getRow(), player.getFieldPosition().getColumn(), projectileType.getImage());

        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();
        direction = player.getFacingDirection();
        projectileSpeed = player.getPlayerSpeed() * 2;
    }


    @Override
    public void playRound() {
        move(chooseDirection());
        System.out.println(active);
    }

    @Override
    public Direction chooseDirection() {
       return direction;
    }

    @Override
    public void move(Direction direction) {

        for (int i = 0; i < projectileSpeed; i++) {

            if (fieldPosition.isEdge()) {
                active = false;
                fieldPosition.hide();

                return;
            }

            fieldPosition.moveInDirection(direction);

        }
    }

    public boolean isActive() {
        return active;
    }
}