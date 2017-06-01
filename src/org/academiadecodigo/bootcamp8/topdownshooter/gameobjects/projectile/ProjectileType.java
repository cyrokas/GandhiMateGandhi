package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("images/projectiles/fire.png"),
    EARTH("images/projectiles/rock.png"),
    WATER("images/projectiles/water.png"),
    WIND("images/projectiles/wind.png");

    private String image;

    ProjectileType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
