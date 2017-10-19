package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus;

public enum BonusType {

    FIRE("resources/images/bonus/fire.png", 1, 300),
    WATER("resources/images/bonus/water.png", 5, 300),
    EARTH("resources/images/bonus/earth.png", 1, 300),
    WIND("resources/images/bonus/wind.png", 1, 300),
    HEALTH("resources/images/bonus/health.png", 10, 500);

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