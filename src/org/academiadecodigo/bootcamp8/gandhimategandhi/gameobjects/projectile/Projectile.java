package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> João Portela
 */

public class Projectile extends GameObject implements Movable {

    private FieldPosition fieldPosition;
    private Field field;
    private final int HEIGHT;
    private final int WIDTH;

    private int projectileSpeed;
    private boolean active = true;

    private ProjectileType projectileType;
    private Direction direction;
    private int projectileDamage;

    public Projectile(Player player, ProjectileType projectileType, boolean kiting) {

        field = player.getField();
        this.projectileType = projectileType;

        int row = player.getFieldPosition().getRow() + player.getFieldPosition().getHeight() / 4;
        int column = player.getFieldPosition().getColumn() + player.getFieldPosition().getWidth() / 4;

        if (kiting) {
            direction = player.getFacingDirection().opposite();
        } else {
            direction = player.getFacingDirection();

        }

        fieldPosition = field.createRepresentation(row, column, projectileType.getImage());

        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();

        projectileDamage = player.getPlayerDamage();
        projectileSpeed = player.getPlayerSpeed();
    }

    public Projectile(Boss s, ProjectileType projectileType) {
        field = s.getField();
        this.projectileType = projectileType;

        int row = s.getPosition().getRow() + s.getPosition().getHeight() / 4;
        int column = s.getPosition().getColumn() + s.getPosition().getWidth() / 4;

        direction = s.chooseDirection();

        fieldPosition = field.createRepresentation(row, column, projectileType.getImage());

        HEIGHT = fieldPosition.getHeight();
        WIDTH = fieldPosition.getWidth();

        projectileDamage = (s.getEnemyDamage() / 3);
        projectileSpeed = s.getSpeed() * 2;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public int getProjectileDamage() {
        return projectileDamage;
    }

    @Override
    public void playRound() {
        move(chooseDirection());
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

            fieldPosition.moveInDirection(direction, this);
        }
    }

    public boolean isActive() {
        return active;
    }
}