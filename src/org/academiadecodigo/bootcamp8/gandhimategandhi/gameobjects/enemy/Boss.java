package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile.ProjectileType;

import java.util.Iterator;
import java.util.LinkedList;

public class Boss extends Enemy {

    private int maxProjectiles;
    private LinkedList<Projectile> projectileList = new LinkedList<>();                       //Projectile list
    private ProjectileType projectileType = ProjectileType.FIRE;
    private int initHealth;
    private Field field;
    private final double criticalHealth;

    public Boss(AbstractPosition pos, FieldPosition playerpos, Field field) {
        super(100, pos, 2, playerpos); //health=100, speed=2
        enemyDamage = (getEnemyDamage() * 2);
        recoilSpeed = getSpeed() * 50;
        maxProjectiles = 1;
        initHealth = getHealth();
        this.field = field;
        criticalHealth = initHealth * 0.5;
    }


    public void playRound() {
        reload();

        if (!position.isColliding(playerPosition)) {
            move(chooseDirection());
        } else if (position.isColliding(playerPosition)) {
            moverecoil();
        }
        if (getHealth() <= (criticalHealth)) {
            //Shoot
            if (hasProjectiles()) {
                projectileList.add(new Projectile(this, projectileType));
            }
        }
    }


    private boolean hasProjectiles() {
        return projectileList.size() < maxProjectiles;
    }

    public void clearProjectileList() {
        for (Projectile p : projectileList) {
            p.getPosition().hide();
        }
        projectileList.clear();
    }

    @Override
    public void move(Direction direction) {
        for (int i = 0; i < speed; i++) {
            position.moveInDirection(chooseDirection(), this);
            if (position.isColliding(playerPosition)) {
                direction = Direction.STOPPED;

                return;
            }
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

    public Iterator<Projectile> iterator() {
        return projectileList.iterator();
    }

    public Field getField() {
        return field;
    }

    public void collidingWith(Player player) {
        if (position.isColliding(player.getFieldPosition())) {
            player.hit(enemyDamage);
        }
    }
}
