package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

/**
 * Created by codecadet on 28/05/17.
 */
public class Shellazar extends Enemy {

    public Shellazar(AbstractPosition pos, FieldPosition playerpos) {
        super(4000, pos, 7, playerpos);
    }

    public void playRound(FieldPosition position) {
        int shootingOdds = (int) (Math.random() * 20);
        if (shootingOdds < 3) {

        }
        move(chooseDirection(), getSpeed());
    }
}
