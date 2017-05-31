package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public enum BonusType {

    FIRE("images/Bonus/bonusfire.png", 1, 100), //bonus maximum projectiles
    WATER("images/Bonus/bonuswater.png", 1, 100), //bonus hitpoints
    EARTH("images/Bonus/bonuswater.png", 1, 100), //bonus damage, reduced speed
    WIND("images/Bonus/bonuswind.png", 1, 100), //bonus speed
    HEALTH("images/Bonus/bonushealth.png", 5, 100); //gives health

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
