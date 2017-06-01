package org.academiadecodigo.bootcamp8.gandhimategandhi.state;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Field;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.FieldPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Robin Opinião
 */

public class Menu {

    private State state;
    private Field field;
    private KeyboardControl keyboardController;
    private FieldPosition fieldPosition;

    public Menu(Field field) {
        this.field = field;
        keyboardController = new KeyboardControl();
        fieldPosition = field.createRepresentation(-20, 0, "images/menu/menu.png");
        state = State.MENU;
    }

    private class KeyboardControl implements KeyboardHandler {

        private Keyboard k;


        public KeyboardControl() {
            k = new Keyboard(this);
            keyMap();
        }


        private void keyMap() {
            KeyboardEvent pressGame = new KeyboardEvent();
            pressGame.setKey(KeyboardEvent.KEY_Z);
            pressGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressGame);

            KeyboardEvent pressQuit = new KeyboardEvent();
            pressQuit.setKey(KeyboardEvent.KEY_Q);
            pressQuit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressQuit);
        }

        @Override
        public void keyPressed(KeyboardEvent e) {

            if (e.getKey() == KeyboardEvent.KEY_Z) {
                state = State.GAME;
            }

            if (e.getKey() == KeyboardEvent.KEY_Q) {
                state = State.QUIT;

            }

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }


    }

    public State getState() {

        return state;
    }

    public void removeKeyboard() {
        keyboardController = null;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

}
