package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

/**
 * Created by codecadet on 25/05/17.
 */
public enum PlayerType {

    P1("/Users/codecadet/TopDownShooter/1571779_k_152.jpg"),
    P2;

    private String image;

    PlayerType() {
    }

    PlayerType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

}
