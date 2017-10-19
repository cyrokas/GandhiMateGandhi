package org.academiadecodigo.bootcamp8.gandhimategandhi.field;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.position.AbstractPosition;
import org.academiadecodigo.bootcamp8.gandhimategandhi.simplegfx.SimpleGfxStats;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public interface Field {

    Picture getPicture();

    void setup();

    int getColumns();

    int getRows();

    AbstractPosition createRepresentation(String image, boolean edge);

    AbstractPosition createRepresentation(int x, int y, String image);

    SimpleGfxStats createRepresentationStats(int points, int hitPoints, int maxHitPoints, int speed, int damage, int projectile);
}
