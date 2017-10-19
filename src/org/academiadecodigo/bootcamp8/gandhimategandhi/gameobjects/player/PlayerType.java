package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player;

public enum PlayerType {

    P1("resources/images/player/down1.png");

    private String image;

    PlayerType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
