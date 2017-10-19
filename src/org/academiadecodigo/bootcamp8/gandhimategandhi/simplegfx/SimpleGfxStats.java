package org.academiadecodigo.bootcamp8.gandhimategandhi.simplegfx;

import org.academiadecodigo.bootcamp8.gandhimategandhi.field.FieldStats;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxStats implements FieldStats {

    //Proprieties
    private Text textPoints;                        // Text to show points
    private Text textHitPoints;                    // Text to show HitPoints
    private Text textSpeed;                        //Text to show Speed
    private Text textDamage;                        // Text to show Damage
    private Text textProjectiles;                   //Text to show Projectiels

    private Picture hitpointsPicture = new Picture(720, 10, "resources/images/stats/health.png");
    private Picture damagePicture = new Picture(620, 10, "resources/images/stats/gladius.png");
    private Picture speedPicture = new Picture(520, 10, "resources/images/stats/boot.png");
    private Picture projectilesPicture = new Picture(420, 10, "resources/images/stats/projectiles.png");
    private Picture pointsPicture = new Picture(12, 10, "resources/images/stats/skull.png");

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGfxStats(int points, int hitPoints, int maxHitPoints, int speed, int damage, int projectile) {

        hitpointsPicture.draw();
        damagePicture.draw();
        speedPicture.draw();
        pointsPicture.draw();
        projectilesPicture.draw();

        textPoints = new Text(35, 15, "");
        textHitPoints = new Text(750, 15, "");
        textSpeed = new Text(550, 15, "");
        textDamage = new Text(650, 15, "");
        textProjectiles = new Text(450, 15, "");

        textPoints.grow(5, 5);
        textHitPoints.grow(10, 5);
        textSpeed.grow(5, 5);
        textDamage.grow(5, 5);
        textProjectiles.grow(5, 5);

        showPoints(points);                     //Show Points
        showHitPoints(hitPoints, maxHitPoints);//Show HitPoints
        showSpeed(speed);                      //Show Speed
        showDamage(damage);                    //Show damage
        showProjectiles(projectile);            //show projectiles

    }

    @Override
    public void showPoints(int points) {

        textPoints.setText(" " + points);
        textPoints.draw();

    }

    @Override
    public void showHitPoints(int hitPoints, int maxHitpoints) {

        textHitPoints.setText(hitPoints + " | " + maxHitpoints);
        textHitPoints.draw();

    }

    @Override
    public void showSpeed(int speed) {

        textSpeed.setText(" " + speed);
        textSpeed.draw();

    }

    @Override
    public void showDamage(int damage) {

        textDamage.setText(" " + damage);
        textDamage.draw();

    }

    @Override
    public void showProjectiles(int projectile) {

        textProjectiles.setText(" " + projectile);
        textProjectiles.draw();

    }

    @Override
    public void hide() {

        textDamage.delete();
        textHitPoints.delete();
        textPoints.delete();
        textProjectiles.delete();
        textSpeed.delete();

        hitpointsPicture.delete();
        damagePicture.delete();
        speedPicture.delete();
        projectilesPicture.delete();
        pointsPicture.delete();
    }
}
