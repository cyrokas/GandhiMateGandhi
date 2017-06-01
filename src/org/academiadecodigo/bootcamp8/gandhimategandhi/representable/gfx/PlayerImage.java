package org.academiadecodigo.bootcamp8.gandhimategandhi.representable.gfx;


import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;

/**
 * Created by Cyrille on 31/05/17.
 */
public class PlayerImage {

    //Properties
    private String imagePath;          //Return to simpleGFXPosition the image path
    private String path;


    public String imageChange(Direction direction, int imageCount) {


        if (imageCount == 15) {

            imagePath = imageSet(direction, ImagesPlayer.MOV1);

        } else if (imageCount == 30) {

            imagePath = imageSet(direction, ImagesPlayer.MOV2);

        } else if (imageCount == 45) {

            imagePath = imageSet(direction, ImagesPlayer.MOV3);

        } else if (imageCount == 60) {

            imagePath = imageSet(direction, ImagesPlayer.MOV4);

        } else if (imageCount == 75) {

            imagePath = imageSet(direction, ImagesPlayer.MOV5);

        } else if (imageCount == 90) {

            imagePath = imageSet(direction, ImagesPlayer.MOV6);

        } else if (imageCount == 105) {

            imagePath = imageSet(direction, ImagesPlayer.MOV7);

        }

        return imagePath;
    }

    //Select direction of image
    public String imageSet(Direction direction, ImagesPlayer images) {

        if (direction == Direction.DOWN || direction == Direction.DOWN_LEFT ||
                direction == Direction.DOWN_RIGHT) {

            path = images.getDown();

        } else if (direction == Direction.UP || direction == Direction.UP_LEFT ||
                direction == Direction.UP_RIGHT) {

            path = images.getUp();

        } else if (direction == Direction.RIGHT) {

            path = images.getRight();

        } else {

            path = images.getLeft();

        }

        return path;
    }


    //Enum of images for the player
    public enum ImagesPlayer{
        MOV1("images/player/up1.png", "images/player/down1.png",
                "images/player/left1.png", "images/player/right1.png"),

        MOV2("images/player/up2.png", "images/player/down2.png",
                "images/player/left2.png", "images/player/right2.png"),

        MOV3("images/player/up3.png", "images/player/down3.png",
                "images/player/left3.png", "images/player/right3.png"),

        MOV4("images/player/up4.png", "images/player/down4.png",
                "images/player/left4.png", "images/player/right4.png"),

        MOV5("images/player/up5.png", "images/player/down5.png",
                "images/player/left5.png", "images/player/right5.png"),

        MOV6("images/player/up6.png", "images/player/down6.png",
                "images/player/left6.png", "images/player/right4.png"),

        MOV7("images/player/up7.png", "images/player/down7.png",
                "images/player/left7.png", "images/player/right7.png");


        private String up;
        private String down;
        private String left;
        private String right;


        ImagesPlayer(String up, String down, String left, String right) {

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
