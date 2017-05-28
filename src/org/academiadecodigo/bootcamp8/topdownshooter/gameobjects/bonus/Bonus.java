package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;

/**
 * Created by codecadet on 24/05/17.
 */
public class Bonus extends GameObject {

    private Field field;
    private FieldPosition fieldPosition;
    private boolean active = false;
    private BonusType bonusType;
    private int duration;
    private final int BONUS_DURATION;
    private final int DELAY;


    public Bonus (Field field, int DELAY) {

        this.field = field;
        this.DELAY = DELAY;
        duration = 0;
        initialBonus();
        BONUS_DURATION = bonusType.getDuration() * DELAY;
        this.fieldPosition = field.createRepresentation(bonusType.getImage());
        active = true;

        System.out.println("col " + fieldPosition.getColumn());
        System.out.println("row " + fieldPosition.getRow());
    }

    public void initialBonus() {

        BonusType[] bonusTypes = BonusType.values();                //Array that contains all Bonus types
        int random = (int) (Math.random() * bonusTypes.length);     //Generate random Bonus type from array
        bonusType = bonusTypes[random];                             //Attribute random direction

    }

    public void playRound() {

        duration++;
        if (duration > BONUS_DURATION) {

            active = false;
            fieldPosition.hide();
        }

    }

    public boolean isActive() {

        return active;

    }

}
