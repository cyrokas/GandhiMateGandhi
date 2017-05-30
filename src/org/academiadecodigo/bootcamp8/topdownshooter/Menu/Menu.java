package org.academiadecodigo.bootcamp8.topdownshooter.Menu;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Field;
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

    private class KeyboardControl implements KeyboardHandler{

        private Keyboard k;


        public KeyboardControl(){
            k = new Keyboard(this);
        }


        public void keyMap() {
            KeyboardEvent pressMenu = new KeyboardEvent();
            pressMenu.setKey(KeyboardEvent.KEY_Z);
            pressMenu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressMenu);

            KeyboardEvent pressStory = new KeyboardEvent();
            pressStory.setKey(KeyboardEvent.KEY_X);
            pressStory.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            k.addEventListener(pressStory);
        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }
    }

    }
