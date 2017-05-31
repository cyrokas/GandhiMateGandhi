package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.projectile;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum ProjectileType {

    FIRE("images/powers/fire.png"),
    ROCK("images/powers/rock.png"),
    WATER("images/powers/water.png"),
    WIND("images/powers/wind.png");

    private String image;

    ProjectileType(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }
}
