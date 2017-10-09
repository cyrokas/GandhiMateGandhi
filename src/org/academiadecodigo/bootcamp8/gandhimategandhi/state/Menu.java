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
    private FieldPosition position;

    public Menu(Field field) {
        new MenuController();
        position = field.createRepresentation(-20, 0, "resources/images/menu/menu.png");
        state = State.MENU;
    }

    public State play() throws InterruptedException {

        position.show();

        while (state == State.MENU) {
            Thread.sleep(50);
        }

        position.hide();
        System.out.println(state);
        return state;
    }

    private class MenuController implements KeyboardHandler {

        private Keyboard k;

        public MenuController() {
            k = new Keyboard(this);
            mapKeys();
        }

        private void mapKeys() {
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

            switch (e.getKey()) {
                case KeyboardEvent.KEY_Z:
                    state = State.GAME;
                    break;
                case KeyboardEvent.KEY_Q:
                    state = State.QUIT;
                    break;
                default:
                    System.err.println("MENU KEYS NOT WORKING.");
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {
        }
    }
}
