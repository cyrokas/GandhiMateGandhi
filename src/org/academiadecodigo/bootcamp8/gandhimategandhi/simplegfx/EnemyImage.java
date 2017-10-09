package org.academiadecodigo.bootcamp8.gandhimategandhi.simplegfx;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;

/**
 * Developed @ <Academia de Código_>
 * Created by
 * <Code Cadet> Cyrille Feijó
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
    public String imageSet(Direction direction, ImagesEnemy imagesEnemy) {

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

    //Enum of images for the Enemy
    public enum ImagesEnemy {
        MOV1("resources/images/soldier_enemy/back1.png", "resources/images/soldier_enemy/front1.png",
                "resources/images/soldier_enemy/left1.png", "resources/images/soldier_enemy/right1.png"),

        MOV2("resources/images/soldier_enemy/back2.png", "resources/images/soldier_enemy/front2.png",
                "resources/images/soldier_enemy/left2.png", "resources/images/soldier_enemy/right2.png"),

        MOV3("resources/images/soldier_enemy/back3.png", "resources/images/soldier_enemy/front3.png",
                "resources/images/soldier_enemy/left3.png", "resources/images/soldier_enemy/right3.png"),

        MOV4("resources/images/soldier_enemy/back4.png", "resources/images/soldier_enemy/front4.png",
                "resources/images/soldier_enemy/left4.png", "resources/images/soldier_enemy/right4.png"),

        MOV5("resources/images/soldier_enemy/back5.png", "resources/images/soldier_enemy/front5.png",
                "resources/images/soldier_enemy/left5.png", "resources/images/soldier_enemy/right5.png"),

        MOV6("resources/images/soldier_enemy/back6.png", "resources/images/soldier_enemy/front6.png",
                "resources/images/soldier_enemy/left6.png", "resources/images/soldier_enemy/right6.png"),

        MOV7("resources/images/soldier_enemy/back7.png", "resources/images/soldier_enemy/front7.png",
                "resources/images/soldier_enemy/left7.png", "resources/images/soldier_enemy/right7.png");


        private String up;
        private String down;
        private String left;
        private String right;


        ImagesEnemy(String down, String up, String right, String left) {

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
