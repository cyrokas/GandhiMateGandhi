package org.academiadecodigo.bootcamp8.topdownshooter.state;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 31/05/17.
 */
public class Story {

    private State state;
    private Field field;
    private FieldPosition fieldPosition;
    private KeyboardControll keyboardControler;


    public Story(Field field) {
        this.field = field;
        keyboardControler = new KeyboardControll();
        fieldPosition = field.createRepresentation(-20, 0, "");
    }

    private class KeyboardControll implements KeyboardHandler {

        private Keyboard k;


        public KeyboardControll() {
            k = new Keyboard(this);
            keyMap();
        }

        private void keyMap() {

            KeyboardEvent pressNextPage = new KeyboardEvent();
            pressNextPage.setKey(KeyboardEvent.KEY_X);
            pressNextPage.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressNextPage);


        }

        @Override
        public void keyPressed(KeyboardEvent e1) {

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }


        public State getState() {
            return state;

        }

        public FieldPosition getFieldPosition() {
            return fieldPosition;
        }
    }

}
