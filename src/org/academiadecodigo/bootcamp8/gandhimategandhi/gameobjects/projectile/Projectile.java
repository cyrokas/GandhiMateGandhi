package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.Movable;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;

public class Projectile extends GameObject implements Movable {

    private FieldPosition position;
    private Field field;

    private int speed;
    private boolean isActive = true;

    private Direction direction;
    private int damage;

    public Projectile(Player player, ProjectileType projectileType, boolean kiting) {
        field = player.getField();

        int row = player.getFieldPosition().getRow() + player.getFieldPosition().getHeight() / 4;
        int column = player.getFieldPosition().getColumn() + player.getFieldPosition().getWidth() / 4;

        if (kiting) {
            direction = player.getFacingDirection().opposite();
        }
        else {
            direction = player.getFacingDirection();
        }

        position = field.createRepresentation(row, column, projectileType.getImage());
        damage = player.getDamage();
        speed = player.getSpeed();
    }

    public Projectile(Boss boss, ProjectileType projectileType) {
        field = boss.getField();

        int row = boss.getPosition().getRow() + boss.getPosition().getHeight() / 4;
        int column = boss.getPosition().getColumn() + boss.getPosition().getWidth() / 4;

        direction = boss.chooseDirection();
        position = field.createRepresentation(row, column, projectileType.getImage());
        damage = Math.round(boss.getEnemyDamage()/2);
        speed = boss.getSpeed() * 2;
    }

    public FieldPosition getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
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

        for (int i = 0; i < speed; i++) {

            if (position.isEdge()) {
                isActive = false;
                position.hide();
                return;
            }
            position.moveInDirection(direction, this);
        }
    }

    public boolean isActive() {
        return isActive;
    }
}