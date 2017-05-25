package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player;

import org.academiadecodigo.bootcamp8.topdownshooter.Direction;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 23/05/17.
 */
public class KeyboardControl implements KeyboardHandler {

    private Direction direction;
    private boolean moving;

    private Keyboard k;
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;

    public KeyboardControl(Direction direction, int upKey, int downKey, int leftKey, int rightKey) {

        this.direction = direction;

        k = new Keyboard(this);
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public void configKeyBoard() {

        KeyboardEvent pressUp = new KeyboardEvent();
        pressUp.setKey(upKey);
        pressUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressUp);

        KeyboardEvent pressDown = new KeyboardEvent();
        pressDown.setKey(downKey);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressDown);

        KeyboardEvent pressLeft = new KeyboardEvent();
        pressLeft.setKey(leftKey);
        pressLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressLeft);

        KeyboardEvent pressRight = new KeyboardEvent();
        pressRight.setKey(rightKey);
        pressRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pressRight);

        KeyboardEvent releaseUp = new KeyboardEvent();
        releaseUp.setKey(upKey);
        releaseUp.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseUp);

        KeyboardEvent releaseDown = new KeyboardEvent();
        releaseDown.setKey(downKey);
        releaseDown.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseDown);

        KeyboardEvent releaseLeft = new KeyboardEvent();
        releaseLeft.setKey(leftKey);
        releaseLeft.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseLeft);

        KeyboardEvent releaseRight = new KeyboardEvent();
        releaseRight.setKey(rightKey);
        releaseRight.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        k.addEventListener(releaseRight);

    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        moving = true;

        if (e.getKey() == leftKey) {
           direction = Direction.LEFT;
        }

        if (e.getKey() == downKey) {
            direction = Direction.DOWN;
        }

        if (e.getKey() == rightKey) {
            direction = Direction.RIGHT;
        }

        if (e.getKey() == upKey) {
            direction = Direction.UP;
        }

    }

    @Override
    public void keyReleased (KeyboardEvent e) {
       moving = false;
    }

    public Direction getDirection() {
        return direction;
    }


    public boolean isMoving() {
        return moving;
    }


}
