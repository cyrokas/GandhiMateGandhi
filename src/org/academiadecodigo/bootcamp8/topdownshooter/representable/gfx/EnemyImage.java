package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;

/**
 * Created by Cyrille on 31/05/17.
 */
public class EnemyImage {

    //Properties
    private String imagePath;           //Return to simpleGFXPosition the image path
    private String path;


    public String imageChange(Direction direction, int imageCount) {


        if (imageCount == 15) {

            imagePath = imageSet(direction, ImagesEnemy.MOV1);

        } else if (imageCount == 30) {

            imagePath = imageSet(direction, ImagesEnemy.MOV2);

        } else if (imageCount == 45) {

            imagePath = imageSet(direction, ImagesEnemy.MOV3);

        } else if (imageCount == 60) {

            imagePath = imageSet(direction, ImagesEnemy.MOV4);

        } else if (imageCount == 75) {

            imagePath = imageSet(direction, ImagesEnemy.MOV5);

        } else if (imageCount == 90) {

            imagePath = imageSet(direction, ImagesEnemy.MOV6);

        } else if (imageCount == 105) {

            imagePath = imageSet(direction, ImagesEnemy.MOV7);

        }

        return imagePath;
    }

    //Select direction of image
    private String imageSet(Direction direction, ImagesEnemy imagesEnemy) {

        if (direction == Direction.DOWN || direction == Direction.DOWN_LEFT ||
                direction == Direction.DOWN_RIGHT) {

            path = imagesEnemy.getDown();

        } else if (direction == Direction.UP || direction == Direction.UP_LEFT ||
                direction == Direction.UP_RIGHT) {

            path = imagesEnemy.getUp();

        } else if (direction == Direction.RIGHT) {

            path = imagesEnemy.getRight();

        } else {

            path = imagesEnemy.getLeft();

        }

        return path;
    }

    //Enum of images for the player
    public enum ImagesEnemy {
        MOV1("images/soldier_enemy/back1.png", "images/soldier_enemy/front1.png",
                "images/soldier_enemy/left1.png", "images/soldier_enemy/right1.png"),

        MOV2("images/soldier_enemy/back2.png", "images/soldier_enemy/front2.png",
                "images/soldier_enemy/left2.png", "images/soldier_enemy/right2.png"),

        MOV3("images/soldier_enemy/back3.png", "images/soldier_enemy/front3.png",
                "images/soldier_enemy/left3.png", "images/soldier_enemy/right3.png"),

        MOV4("images/soldier_enemy/back4.png", "images/soldier_enemy/front4.png",
                "images/soldier_enemy/left4.png", "images/soldier_enemy/right4.png"),

        MOV5("images/soldier_enemy/back5.png", "images/soldier_enemy/front5.png",
                "images/soldier_enemy/left5.png", "images/soldier_enemy/right5.png"),

        MOV6("images/soldier_enemy/back6.png", "images/soldier_enemy/front6.png",
                "images/soldier_enemy/left6.png", "images/soldier_enemy/right6.png"),

        MOV7("images/soldier_enemy/back7.png", "images/soldier_enemy/front7.png",
                "images/soldier_enemy/left7.png", "images/soldier_enemy/right7.png");


        private String up;
        private String down;
        private String left;
        private String right;


        ImagesEnemy(String up, String down, String left, String right) {

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
