package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 23/05/17.
 */
public class KeyboardController implements KeyboardHandler {

    private Direction direction;
    private boolean moving;
    private boolean shooting = false;

    private int[] keyMap;
    private boolean[] pressedKeys = new boolean[4];
    Keyboard k;

    public KeyboardController(Direction direction, int upKey, int downKey, int leftKey, int rightKey, int shoot) {

        this.direction = direction;

        k = new Keyboard(this);
        keyMap = new int[] {upKey, downKey, leftKey, rightKey, shoot};
    }

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

    }

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
        }

        direction = pressedDirection();
    }

    @Override
    public void keyReleased (KeyboardEvent e) {

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

        direction = pressedDirection();

        if(direction == Direction.STOPPED) {
            moving = false;
        }
    }

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

    public boolean isShooting(){
        return shooting;
    }

}
