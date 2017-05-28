package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Created by codecadet on 27/05/17.
 */
public enum ProjectileType {

    FIRE("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/projectile/download.jpeg");

    private String image;

    ProjectileType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
