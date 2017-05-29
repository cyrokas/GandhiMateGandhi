package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public enum BonusType {

    FIRE("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/bonus/fire.jpg", 1, 5), //turns powers to fire
    WATER("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/bonus/water.jpeg", 2, 5), //turns powers to water, slower enemy
    EARTH("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/bonus/earth.jpg", 2, 5), //turns powers to earth, damage up, speed down
    WIND("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/projectile/download.jpeg", 2, 5), //turns powers to wind, speed up
    HEALTH("/Users/codecadet/Documents/TopDownShooter/src/org/academiadecodigo/bootcamp8/topdownshooter/gameobjects/bonus/health.jpg", 2, 5); //gives health

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
