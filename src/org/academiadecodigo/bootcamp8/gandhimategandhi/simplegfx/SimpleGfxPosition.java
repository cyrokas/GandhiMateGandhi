package org.academiadecodigo.bootcamp8.gandhimategandhi.simplegfx;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.Direction;
import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.GameObject;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Enemy;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.enemy.Boss;
import org.academiadecodigo.bootcamp8.gandhimategandhi.gameobjects.player.Player;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxPosition extends AbstractPosition {

    private SimpleGfxField simpleGfxField;
    private Picture picture;

    private final int HEIGHT;
    private final int WIDTH;

    private PlayerImage playerImage = new PlayerImage();
    private EnemyImage enemyPicture = new EnemyImage();
    private BossImage bossPicture = new BossImage();

    private int imageCount = 1;


    public SimpleGfxPosition(String image, SimpleGfxField field, boolean edge) {

        super((int) (Math.random() * field.getRows()), (int) (Math.random() * field.getColumns()), field);

        int row = getRow();
        int column = getColumn();

        if (edge) {
            while (!(column == 0 || column == field.getColumns() - 1 || row == 0 || row == field.getRows() - 1)) {
                row = (int) (Math.random() * field.getRows());
                column = (int) (Math.random() * field.getColumns());
            }
        }

        simpleGfxField = field;
        picture = new Picture(field.columnToX(column), field.rowToY(row), image);

        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();

        fixEdgePosition(row, column);
        show();
    }

    public SimpleGfxPosition(int row, int column, String image, SimpleGfxField field) {

        super(row, column, field);

        simpleGfxField = field;

        picture = new Picture(field.columnToX(column), field.rowToY(row), image);
        HEIGHT = picture.getHeight();
        WIDTH = picture.getWidth();

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
    public void moveInDirection(Direction direction, GameObject gameObject, boolean kiting) {

        int initialColumn = simpleGfxField.columnToX(super.getColumn());
        int initialRow = simpleGfxField.rowToY(super.getRow());

        super.moveInDirection(direction, gameObject);

        int finalColumn = simpleGfxField.columnToX(super.getColumn());
        int finalRow = simpleGfxField.rowToY(super.getRow());

        picture.translate(finalColumn - initialColumn, finalRow - initialRow);

        //DISCUSS ------------------------------------------------------------------------------------------------------
        if (kiting && gameObject instanceof Player) {

            if (direction == Direction.STOPPED) {
                direction = ((Player) gameObject).getFacingDirection();
            }

            direction = direction.opposite();
            changePicture(gameObject, direction);
            imageCount++;
            return;
        }

        changePicture(gameObject, direction);
        imageCount++;
    }

    @Override
    public void moveInDirection(Direction direction, GameObject gameObject) {

        int initialColumn = simpleGfxField.columnToX(super.getColumn());
        int initialRow = simpleGfxField.rowToY(super.getRow());

        super.moveInDirection(direction, gameObject);

        int finalColumn = simpleGfxField.columnToX(super.getColumn());
        int finalRow = simpleGfxField.rowToY(super.getRow());

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

        int rightEdge = simpleGfxField.getColumns() - 1 - Math.round(WIDTH / simpleGfxField.getCellSize());
        int lowerEdge = simpleGfxField.getRows() - 1 - Math.round(HEIGHT / simpleGfxField.getCellSize());

        return getColumn() == rightEdge || getRow() == lowerEdge || getColumn() == 1 || getRow() == 1;
    }

    private void fixEdgePosition(int row, int column) {

        int dx = 0;
        int dy = 0;

        if (simpleGfxField.rowToY(row) + HEIGHT > simpleGfxField.rowToY(simpleGfxField.getRows() - 1)) {
            dy = simpleGfxField.rowToY(simpleGfxField.getRows() - 1) - HEIGHT - simpleGfxField.rowToY(row);
            row = simpleGfxField.getRows() - 1 - Math.round(HEIGHT / simpleGfxField.getCellSize());
        }

        if (simpleGfxField.columnToX(column) + WIDTH > simpleGfxField.columnToX(simpleGfxField.getColumns() - 1)) {
            dx = simpleGfxField.columnToX(simpleGfxField.getColumns() - 1) - WIDTH - simpleGfxField.columnToX(column);
            column = simpleGfxField.getColumns() - 1 - Math.round(WIDTH / simpleGfxField.getCellSize());
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

        int imageChangeCount = 15;                       //When images change
        final int MAX_COUNT = 119;                         //Max count to change images

        if (gameObject instanceof Player) {

            if (imageCount % imageChangeCount == 0) {

                picture.load(playerImage.imageChange(direction, imageCount));

            } else if (imageCount == MAX_COUNT) {

                imageCount = 0;
            }

        } else if (gameObject instanceof Boss) {

            if (imageCount % imageChangeCount == 0) {

                picture.load(bossPicture.imageChange(direction, imageCount));

            } else if (imageCount == MAX_COUNT) {

                imageCount = 0;
            }

        } else if (gameObject instanceof Enemy) {

            if (imageCount % imageChangeCount == 0) {

                picture.load(enemyPicture.imageChange(direction, imageCount));

            } else if (imageCount == MAX_COUNT) {

                imageCount = 0;
            }
        }

    }


}


