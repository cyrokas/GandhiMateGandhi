package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public enum BonusType {

    FIRE("Images/Bonus/fire.jpg", 1, 5), //turns powers to fire
    WATER("Images/Bonus/water.jpeg", 2, 5), //turns powers to water, slower enemy
    EARTH("Images/Bonus/earth.jpg", 2, 5), //turns powers to earth, damage up, speed down
    WIND("Images/Bonus/windkappa.jpeg", 2, 5), //turns powers to wind, speed up
    HEALTH("Images/Bonus/health.jpg", 2, 5); //gives health

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
