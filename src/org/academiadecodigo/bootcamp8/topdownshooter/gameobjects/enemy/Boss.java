package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.ProjectileType;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> João Portela
 * <Code Cadet> Robin Opinião
 */

public class Boss extends Enemy {
    private int maxProjectiles;
    private LinkedList<Projectile> projectileList = new LinkedList<>();                       //Projectile list
    private ProjectileType projectileType = ProjectileType.FIRE;
    private int initHealth;
    private Field field;
    private final double criticalHealth;

    public Boss(AbstractPosition pos, FieldPosition playerpos, Field field) {
        super(100, pos, 2, playerpos); //health=100, speed=2
        enemyDamage = getEnemyDamage()*5;
        recoilSpeed = getSpeed() * 10;
        maxProjectiles = 10;
        initHealth = getHealth();
        this.field = field;
        criticalHealth=initHealth*0.3;
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
            p.getFieldPosition().hide();
        }
        projectileList.clear();
    }

    @Override
    public void move(Direction direction) {
        for (int i = 0; i < speed; i++) {
            position.moveInDirection(chooseDirection(), this);
            if (position.isColliding(playerPosition)) {
                direction = Direction.STOPPED;
                //hit(enemyDamage);

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

}
