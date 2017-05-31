package org.academiadecodigo.bootcamp8.topdownshooter.state;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.FieldPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 29/05/17.
 */
public class Menu {

    private State state;
    private Field field;
    private KeyboardControl keyboardController;
    private FieldPosition fieldPosition;

    public Menu(Field field) {
        this.field = field;
        keyboardController = new KeyboardControl();
        fieldPosition = field.createRepresentation(0, 0, "Ghandi-First-Blood-Rambo.jpg");
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

            KeyboardEvent pressStory = new KeyboardEvent();
            pressStory.setKey(KeyboardEvent.KEY_X);
            pressStory.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressStory);

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

            if (e.getKey() == KeyboardEvent.KEY_X) {
                state = State.STORY;
            }
            if (e.getKey() == KeyboardEvent.KEY_Q) {
                System.exit(1);
            }

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }


    }

    public State getState() {
        return state;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }
}
