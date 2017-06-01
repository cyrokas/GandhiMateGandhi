package org.academiadecodigo.bootcamp8.gandhimategandhi.representable.gfx;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Developed @ <Academia de Código_>
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

    private Direction direction;

    private PlayerImage playerImage = new PlayerImage();
    private EnemyImage enemyImage = new EnemyImage();
    private BossImage bossImage = new BossImage();

    private int imageCount = 1;                         // Counter to change image
    private int imageChangeCount = 15;                       //When images change
    private int maxCount = 119;                         //Max count to change images


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
    public void moveInDirection(Direction direction, GameObject gameObject, boolean kitting) {

        int initialColumn = simpleGFXField.columnToX(super.getColumn());
        int initialRow = simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction, gameObject);

        int finalColumn = simpleGFXField.columnToX(super.getColumn());
        int finalRow = simpleGFXField.rowToY(super.getRow());

        picture.translate(finalColumn - initialColumn, finalRow - initialRow);

        if (kitting && gameObject instanceof Player) {

            if (direction == Direction.STOPPED) {
                direction = ((Player) gameObject).getFacingDirection();
            }

            direction = direction.opposite();
            changePicture(gameObject, direction);

            imageCount++;
        } else {
            changePicture(gameObject, direction);
            imageCount++;
        }


    }

    @Override
    public void moveInDirection(Direction direction, GameObject gameObject) {

        int initialColumn = simpleGFXField.columnToX(super.getColumn());
        int initialRow = simpleGFXField.rowToY(super.getRow());

        super.moveInDirection(direction, gameObject);

        int finalColumn = simpleGFXField.columnToX(super.getColumn());
        int finalRow = simpleGFXField.rowToY(super.getRow());

        picture.translate(finalColumn - initialColumn, finalRow - initialRow);
        direction = direction.opposite();
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

            if (imageCount % imageChangeCount == 0) {

                picture.load(playerImage.imageChange(direction, imageCount));

            } else if (imageCount == maxCount) {

                imageCount = 0;
            }

        } else if (gameObject instanceof Boss) {

            if (imageCount % imageChangeCount == 0) {

                picture.load(bossImage.imageChange(direction, imageCount));

            } else if (imageCount == maxCount) {

                imageCount = 0;
            }

        } else if (gameObject instanceof Enemy) {

            if (imageCount % imageChangeCount == 0) {

                picture.load(enemyImage.imageChange(direction, imageCount));

            } else if (imageCount == maxCount) {

                imageCount = 0;
            }
        }

    }


}


