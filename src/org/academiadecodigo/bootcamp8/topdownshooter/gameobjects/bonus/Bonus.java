package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.bonus;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public class Bonus extends GameObject {

    private Field field;
    private FieldPosition fieldPosition;

    private boolean active = false;
    private BonusType bonusType;

    private int duration;
    private final int BONUS_DURATION;

    public Bonus (Field field) {

        this.field = field;
        duration = 0;
        initialBonus();
        BONUS_DURATION = getBonusType().getDuration();

        this.fieldPosition = field.createRepresentation(bonusType.getImage(), false);
        active = true;
    }

    public void initialBonus() {

        BonusType[] bonusTypes = BonusType.values();                //Array that contains all bonus types
        int random = (int) (Math.random() * bonusTypes.length);     //Generate random bonus type from array
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

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public BonusType getBonusType() {
        return bonusType;
    }
}
