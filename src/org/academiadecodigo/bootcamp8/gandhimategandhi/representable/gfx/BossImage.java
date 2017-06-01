package org.academiadecodigo.bootcamp8.gandhimategandhi.representable.gfx;


import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Cyrille Feijó
 */

public class BossImage {

    //Properties
    private String imagePath;           //Return to simpleGFXPosition the image path
    private String path;


    public String imageChange(Direction direction, int imageCount) {


        if (imageCount == 15) {

            imagePath = imageSet(direction, ImagesBoss.MOV1);

        } else if (imageCount == 30) {

            imagePath = imageSet(direction, ImagesBoss.MOV2);

        } else if (imageCount == 45) {

            imagePath = imageSet(direction, ImagesBoss.MOV3);

        } else if (imageCount == 60) {

            imagePath = imageSet(direction, ImagesBoss.MOV4);

        } else if (imageCount == 75) {

            imagePath = imageSet(direction, ImagesBoss.MOV5);

        } else if (imageCount == 90) {

            imagePath = imageSet(direction, ImagesBoss.MOV6);

        } else if (imageCount == 105) {

            imagePath = imageSet(direction, ImagesBoss.MOV7);

        }

        return imagePath;
    }

    //Select direction of image
    public String imageSet(Direction direction, ImagesBoss imagesBoss) {

        if (direction == Direction.DOWN || direction == Direction.DOWN_LEFT ||
                direction == Direction.DOWN_RIGHT) {

            path = imagesBoss.getDown();

        } else if (direction == Direction.UP || direction == Direction.UP_LEFT ||
                direction == Direction.UP_RIGHT) {

            path = imagesBoss.getUp();

        } else if (direction == Direction.RIGHT) {

            path = imagesBoss.getRight();

        } else {

            path = imagesBoss.getLeft();

        }

        return path;
    }

    //Enum of images for the Boss
    public enum ImagesBoss {
        MOV1("images/boss/back1.png", "images/boss/front1.png",
                "images/boss/left1.png", "images/boss/right1.png"),

        MOV2("images/boss/back2.png", "images/boss/front2.png",
                "images/boss/left2.png", "images/boss/right2.png"),

        MOV3("images/boss/back3.png", "images/boss/front3.png",
                "images/boss/left3.png", "images/boss/right3.png"),

        MOV4("images/boss/back4.png", "images/boss/front4.png",
                "images/boss/left4.png", "images/boss/right4.png"),

        MOV5("images/boss/back5.png", "images/boss/front5.png",
                "images/boss/left5.png", "images/boss/right5.png"),

        MOV6("images/boss/back6.png", "images/boss/front6.png",
                "images/boss/left6.png", "images/boss/right6.png"),

        MOV7("images/boss/back7.png", "images/boss/front7.png",
                "images/boss/left7.png", "images/boss/right7.png");


        private String up;
        private String down;
        private String left;
        private String right;


        ImagesBoss(String down, String up, String right, String left) {

            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;

        }

        public String getUp() {
            return up;
        }

        public String getDown() {
            return down;
        }

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }
    }
}
