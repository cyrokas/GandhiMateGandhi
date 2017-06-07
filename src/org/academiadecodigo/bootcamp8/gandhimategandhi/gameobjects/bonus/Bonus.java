package org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.bonus;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Tiago Santos
 * <Code Cadet> Filipe Santos Sá
 */

public class Bonus extends GameObject {

    private FieldPosition position;

    private boolean active = false;
    private BonusType bonusType;

    private int duration;
    private final int BONUS_DURATION;

    public Bonus(Field field) {

        duration = 0;
        initialBonus();
        BONUS_DURATION = getBonusType().getDuration();

        this.position = field.createRepresentation(bonusType.getImage(), false);
        active = true;
    }

    public void initialBonus() {
        BonusType[] bonusTypes = BonusType.values();                //Array that contains all bonus types
        int random = (int) (Math.random() * bonusTypes.length);     //Generate random bonus type from array
        bonusType = bonusTypes[random];                             //Attribute random direction
    }

    public void playRound() {

        duration++;

        if (duration >= BONUS_DURATION) {
            active = false;
            position.hide();
        }
    }

    public boolean isActive() {
        return active;
    }

    public FieldPosition getPosition() {
        return position;
    }

    public BonusType getBonusType() {
        return bonusType;
    }
}
