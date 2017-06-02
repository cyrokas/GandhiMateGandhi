package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("resources/images/projectiles/fire.png"),
    EARTH("resources/images/projectiles/rock.png"),
    WATER("resources/images/projectiles/water.png"),
    WIND("resources/images/projectiles/wind.png");

    private String image;

    ProjectileType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
