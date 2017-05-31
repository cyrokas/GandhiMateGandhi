package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> João Portela
 */

public class SimpleGFXPosition extends AbstractPosition {

    private SimpleGFXField simpleGFXField;
    private Picture picture;

    private final int HEIGHT;
    private final int WIDTH;

    private int i = 0; //------------------------------------------------

    public SimpleGFXPosition(String image, SimpleGFXField field, boolean edge) {

        super((int) (Math.random() * field.getRows()), (int) (Math.random() * field.getColumns()), field);

        int row = getRow();
        int column = getColumn();

        if (edge) {
            while (!(column == 0 || column == field.getColumns() - 1 || row == 0 || row == field.getRows() - 1)) {
                row = (int) (Math.random() * field.getRows());
                column = (int) (Math.random() * field.getColumns());
            }
        }

        simpleGFXField = field;
        picture = new Picture(field.columnToX(column), field.rowToY(row), image);

        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();

        //fix image to fit field
        fixEdgePosition(row, column);

        show();

    }

    public SimpleGFXPosition(int row, int column, String image, SimpleGFXField field) {

        super(row, column, field);

        simpleGFXField = field;

        picture = new Picture(field.columnToX(column), field.rowToY(row), image);
        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();

        //fix image to fit field
        fixEdgePosition(row, column);

        show();
    }


    @Override
    public void show() {

        picture.draw();
    }

    @Override
    public void hide() {

        picture.delete();
    }

    @Override
    public void moveInDirection(Direction direction, GameObject gameObject) {

        int initialColumn = simpleGFXField.columnToX(super.getColumn());
        int initialRow = simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction, gameObject);

        int finalColumn = simpleGFXField.columnToX(super.getColumn());
        int finalRow = simpleGFXField.rowToY(super.getRow());

        picture.translate(finalColumn - initialColumn, finalRow - initialRow);

        if (gameObject instanceof Player) {

            imageChange(direction);                //Set images with movement

        }
    }

    @Override
    public int getHeight() {

        return HEIGHT;
    }

    @Override
    public int getWidth() {

        return WIDTH;
    }

    @Override
    public boolean isEdge() {

        int rightEdge = simpleGFXField.getColumns() - 1 - Math.round(WIDTH / simpleGFXField.getCellSize());

        int lowerEdge = simpleGFXField.getRows() - 1 - Math.round(HEIGHT / simpleGFXField.getCellSize());

        return getColumn() == rightEdge || getRow() == lowerEdge || getColumn() == 1 || getRow() == 1;
    }

    private void fixEdgePosition(int row, int column) {
        int dx = 0;
        int dy = 0;

        if (simpleGFXField.rowToY(row) + HEIGHT > simpleGFXField.rowToY(simpleGFXField.getRows() - 1)) {
            dy = simpleGFXField.rowToY(simpleGFXField.getRows() - 1) - HEIGHT - simpleGFXField.rowToY(row);
            row = simpleGFXField.getRows() - 1 - Math.round(HEIGHT / simpleGFXField.getCellSize());
        }
        if (simpleGFXField.columnToX(column) + WIDTH > simpleGFXField.columnToX(simpleGFXField.getColumns() - 1)) {
            dx = simpleGFXField.columnToX(simpleGFXField.getColumns() - 1) - WIDTH - simpleGFXField.columnToX(column);
            column = simpleGFXField.getColumns() - 1 - Math.round(WIDTH / simpleGFXField.getCellSize());
        }
        setPosition(row, column);

        picture.translate(dx, dy);

    }

    @Override
    public int getMaxRow() {
        return getRow() + HEIGHT;
    }

    @Override
    public int getMaxColumn() {
        return getColumn() + WIDTH;
    }

    //Select Image to use
    public void imageChange(Direction direction) {

        if (i < 10) {

            imageSet(direction, ImagesPlayer.MOV2);
            ++i;

        } else if (i < 20) {

            imageSet(direction, ImagesPlayer.MOV4);
            ++i;

        } else if (i < 30) {

            imageSet(direction, ImagesPlayer.MOV6);
            i++;

        } else {
            i = 0;
        }
    }

    //Select direction of image
    public void imageSet(Direction direction, ImagesPlayer imagesPlayer) {
        if (direction == Direction.DOWN || direction == Direction.DOWN_LEFT ||
                direction == Direction.DOWN_RIGHT) {

            picture.load(imagesPlayer.getDown());

        } else if (direction == Direction.UP || direction == Direction.UP_LEFT ||
                direction == Direction.UP_RIGHT) {

            picture.load(imagesPlayer.getUp());

        } else if (direction == Direction.RIGHT) {

            picture.load(imagesPlayer.getRight());

        } else if (direction == Direction.LEFT) {

            picture.load(imagesPlayer.getLeft());

        }
    }

    //Enum of images for the player
    public enum ImagesPlayer {
        MOV2("images/player/up2.png", "images/player/down2.png",
                "images/player/left2.png", "images/player/right2.png"),
        MOV4("images/player/up4.png", "images/player/down4.png",
                "images/player/left4.png", "images/player/right4.png"),
        MOV6("images/player/up6.png", "images/player/down6.png",
                "images/player/left6.png", "images/player/right4.png");


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


