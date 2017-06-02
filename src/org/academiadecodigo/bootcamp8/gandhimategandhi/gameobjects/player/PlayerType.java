package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

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
