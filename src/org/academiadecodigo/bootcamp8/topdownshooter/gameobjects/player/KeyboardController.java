package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 */

public class KeyboardController implements KeyboardHandler {

    private Direction direction;                            //Pressed direction
    private boolean moving = false;                         //Movement status
    private boolean shooting = false;                       //Shoot status
    private boolean backShot = false;

    private int[] keyMap;                                   //Available keys
    private boolean[] pressedKeys = new boolean[4];         //Key status - pressed or not
    Keyboard k;

    public KeyboardController(Direction direction, int upKey, int downKey, int leftKey, int rightKey, int shootFront, int shootBack) {

        this.direction = direction;

        //Instantiate keyboard
        k = new Keyboard(this);

        //Define available keys
        keyMap = new int[]{upKey, downKey, leftKey, rightKey, shootFront, shootBack};
    }

    //Instantiates KeyboardEvents and adds EventListeners
    public void keyMapConfiguration() {

        KeyboardEvent pressUp = new KeyboardEvent();
        pressUp.setKey(keyMap[0]);
        pressUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressUp);

        KeyboardEvent releaseUp = new KeyboardEvent();
        releaseUp.setKey(keyMap[0]);
        releaseUp.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseUp);

        KeyboardEvent pressDown = new KeyboardEvent();
        pressDown.setKey(keyMap[1]);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressDown);

        KeyboardEvent releaseDown = new KeyboardEvent();
        releaseDown.setKey(keyMap[1]);
        releaseDown.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseDown);

        KeyboardEvent pressLeft = new KeyboardEvent();
        pressLeft.setKey(keyMap[2]);
        pressLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressLeft);

        KeyboardEvent releaseLeft = new KeyboardEvent();
        releaseLeft.setKey(keyMap[2]);
        releaseLeft.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseLeft);

        KeyboardEvent pressRight = new KeyboardEvent();
        pressRight.setKey(keyMap[3]);
        pressRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressRight);

        KeyboardEvent releaseRight = new KeyboardEvent();
        releaseRight.setKey(keyMap[3]);
        releaseRight.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseRight);

        KeyboardEvent pressShoot = new KeyboardEvent();
        pressShoot.setKey(keyMap[4]);
        pressShoot.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressShoot);

        KeyboardEvent releaseShoot = new KeyboardEvent();
        releaseShoot.setKey(keyMap[4]);
        releaseShoot.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseShoot);

        KeyboardEvent pressShootBack = new KeyboardEvent();
        pressShootBack.setKey(keyMap[5]);
        pressShootBack.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressShootBack);

        KeyboardEvent releaseShootBack = new KeyboardEvent();
        releaseShootBack.setKey(keyMap[5]);
        releaseShootBack.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseShootBack);
    }

    //Detects pressed keys
    @Override
    public void keyPressed(KeyboardEvent e) {

        moving = true;

        if (e.getKey() == keyMap[0]) {
            pressedKeys[0] = true;
        }
        if (e.getKey() == keyMap[1]) {
            pressedKeys[1] = true;
        }
        if (e.getKey() == keyMap[2]) {
            pressedKeys[2] = true;
        }
        if (e.getKey() == keyMap[3]) {
            pressedKeys[3] = true;
        }
        if (e.getKey() == keyMap[4]) {
            shooting = true;
            backShot = false;
        }
        if (e.getKey() == keyMap[5]) {
            shooting = true;
            backShot = true;
        }

        //Updates direction
        direction = pressedDirection();
    }

    //Detects released keys
    @Override
    public void keyReleased(KeyboardEvent e) {

        if (e.getKey() == keyMap[0]) {
            pressedKeys[0] = false;
        }
        if (e.getKey() == keyMap[1]) {
            pressedKeys[1] = false;
        }
        if (e.getKey() == keyMap[2]) {
            pressedKeys[2] = false;
        }
        if (e.getKey() == keyMap[3]) {
            pressedKeys[3] = false;
        }
        if (e.getKey() == keyMap[4]) {
            shooting = false;
        }
        if (e.getKey() == keyMap[5]) {
            shooting = false;
            backShot = false;

        }

        direction = pressedDirection();

        //Stops player if no keys is pressed
        if (direction == Direction.STOPPED) {
            moving = false;
        }
    }

    //Redirects player according to pressed keys
    private Direction pressedDirection() {

        if (pressedKeys[0] && pressedKeys[2]) {
            return Direction.UP_LEFT;
        }
        if (pressedKeys[0] && pressedKeys[3]) {
            return Direction.UP_RIGHT;
        }
        if (pressedKeys[1] && pressedKeys[2]) {
            return Direction.DOWN_LEFT;
        }
        if (pressedKeys[1] && pressedKeys[3]) {
            return Direction.DOWN_RIGHT;
        }
        if (pressedKeys[0]) {
            return Direction.UP;
        }
        if (pressedKeys[1]) {
            return Direction.DOWN;
        }
        if (pressedKeys[2]) {
            return Direction.LEFT;
        }
        if (pressedKeys[3]) {
            return Direction.RIGHT;
        }
        return Direction.STOPPED;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isShooting() {
        return shooting;
    }

    public boolean isKiting() {
        return backShot;
    }
}
