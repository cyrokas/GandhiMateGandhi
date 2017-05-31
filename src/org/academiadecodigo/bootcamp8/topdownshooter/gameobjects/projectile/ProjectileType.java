package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("images/powers/fireball.png"),
    ROCK("images/powers/rockball.png"),
    WATER("images/powers/waterball.png"),
    WIND("images/powers/windball.png");

    private String image;

    ProjectileType(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }
}
