package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile.Projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> João Portela
 * <Code Cadet> Robin Opinião
 */

public class Shellazar extends Enemy {

    public Shellazar(AbstractPosition pos, FieldPosition playerpos) {
        super(4000, pos, 7, playerpos);
    }

    public void playRound(FieldPosition position) {
        int shootingOdds = (int) (Math.random() * 20);
        if (shootingOdds < 3) {

        }
        move(chooseDirection());
    }
}
