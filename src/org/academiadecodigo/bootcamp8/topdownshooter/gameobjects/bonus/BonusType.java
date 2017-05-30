package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public enum BonusType {

    FIRE("images/Bonus/bonusfire.png", 1, 5), //turns powers to fire
    WATER("images/Bonus/bonuswater.png", 2, 5), //turns powers to water, slower enemy
    EARTH("Images/Bonus/earth.jpg", 2, 5), //turns powers to earth, damage up, speed down
    WIND("images/Bonus/bonuswind.png", 2, 5), //turns powers to wind, speed up
    HEALTH("images/Bonus/bonushealth.png", 2, 5); //gives health

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
