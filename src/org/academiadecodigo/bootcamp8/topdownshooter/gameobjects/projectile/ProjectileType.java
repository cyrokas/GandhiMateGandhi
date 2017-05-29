package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("Images/Projectile/projectile.jpeg");

    private String image;

    ProjectileType(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }
}
