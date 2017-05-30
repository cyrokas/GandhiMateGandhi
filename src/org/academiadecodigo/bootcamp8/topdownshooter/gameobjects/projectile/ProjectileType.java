package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("images/Powers/fireball.png"),
    ROCK("images/Powers/rockball.png"),
    WATER("images/Powers/waterball.png"),
    WIND("images/Powers/windball.png");

    private String image;

    ProjectileType(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }
}
