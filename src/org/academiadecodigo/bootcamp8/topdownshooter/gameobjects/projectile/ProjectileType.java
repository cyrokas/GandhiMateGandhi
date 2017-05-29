package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
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
