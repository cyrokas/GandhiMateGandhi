package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public enum BonusType {

    FIRE("images/bonus/fire.png", 1, 300), //bonus maximum projectiles
    WATER("images/bonus/water.png", 5, 300), //bonus hitpoints
    EARTH("images/bonus/earth.png", 1, 300), //bonus damage, reduced speed
    WIND("images/bonus/wind.png", 1, 300), //bonus speed
    HEALTH("images/bonus/health.png", 10, 500); //gives health

    private int multiplier;
    private String image;
    private int duration;

    BonusType(String image, int multiplier, int duration) {

        this.multiplier = multiplier;
        this.image = image;
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getDuration() {
        return duration;
    }
}