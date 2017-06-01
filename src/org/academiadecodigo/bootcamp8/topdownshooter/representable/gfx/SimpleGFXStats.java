package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldStats;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Developed @ <Academia de Código_>
 * <p>
 * Created by
 * <Code Cadet> Cyrille Feijó
 */
public class SimpleGFXStats implements FieldStats {

    //Proprieties
    private Text textPoints;                        // Text to show points
    private Text textHitPoints;                    // Text to show HitPoints
    private Text textSpeed;                        //Text to show Speed
    private Text textDamage;                        // Text to show Damage
    private Text textProjectiles;                   //Text to show Projectiels
    private Picture hitpointsPicture = new Picture(480, 0, "images/stats/health icon.png");

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGFXStats(int points, int hitPoints, int maxHitPoints, int speed, int damage, int projectile) {

        hitpointsPicture.draw();
        textPoints = new Text(40, 10, "");
        textHitPoints = new Text(650, 10, "");
        textSpeed = new Text(500,10,"");
        textDamage = new Text(450, 10, "");
        textProjectiles = new Text(350, 10 ,"");

        textPoints.grow(10, 5);
        textHitPoints.grow(10, 5);
        textSpeed.grow(10, 5);
        textDamage.grow(10, 5);
        textProjectiles.grow(10, 5);

        showPoints(points);                 //Show Points
        showHitPoints(hitPoints, maxHitPoints);//Show HitPoints
        showSpeed(speed);                      //Show Speed
        showDamage(damage);                    //Show damage
        showProjectiles(projectile);            //show projectiles

    }

    @Override
    public void showPoints(int points) {

        textPoints.setText("Kills: " + points);
        textPoints.draw();

    }

    @Override
    public void showHitPoints(int hitPoints, int maxHitpoints) {

        textHitPoints.setText("HP: " + hitPoints + " | " + maxHitpoints);
        textHitPoints.draw();

    }

    @Override
    public void showSpeed(int speed) {

        textSpeed.setText("SPD: " + speed);
        textSpeed.draw();

    }

    @Override
    public void showDamage(int damage) {

        textDamage.setText("ATK: " + damage);
        textDamage.draw();

    }

    @Override
    public void showProjectiles(int projectile) {

        textProjectiles.setText("PROJ: " + projectile);
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
    }
}
