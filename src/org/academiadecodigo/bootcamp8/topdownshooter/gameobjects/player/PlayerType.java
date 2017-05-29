package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public enum PlayerType {

    P1("/Users/codecadet/Desktop/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/player/1571779_k_152.jpg");

    private String image;

    PlayerType(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

}
