package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.Direction;
import org.academiadecodigo.bootcamp8.topdownshooter.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.enemy.Enemy;
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

    private PlayerImage playerImage = new PlayerImage();
    private EnemyImage enemyImage = new EnemyImage();

    private int imageCount = 1;

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
        changePicture(gameObject, direction);
        imageCount++;

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

    private void changePicture(GameObject gameObject, Direction direction) {

        if (gameObject instanceof Player) {

            if (imageCount % 15 == 0 && imageCount < 120) {

                picture.load(playerImage.imageChange(direction, imageCount));    //Set images with movement

            } else if (imageCount == 120) {

                imageCount = 1;
            }
        } else if (gameObject instanceof Enemy) {

            if (imageCount % 15 == 0 && imageCount < 120) {

                picture.load(enemyImage.imageChange(direction, imageCount));    //Set images with movement

            } else if (imageCount == 120) {

                imageCount = 1;
            }
        }

    }
}


