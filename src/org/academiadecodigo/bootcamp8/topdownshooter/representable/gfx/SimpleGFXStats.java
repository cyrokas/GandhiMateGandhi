package org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx;

import org.academiadecodigo.bootcamp8.topdownshooter.field.FieldStats;
import org.academiadecodigo.simplegraphics.graphics.Text;

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

    //NOTE FIX Position of SCORE
    //Constructor
    public SimpleGFXStats(int points, int hitPoints, int maxHitPoints, int speed, int damage, int projectile) {

        textPoints = new Text(40, 10, "");
        textHitPoints = new Text(740, 10, "");
        textSpeed = new Text(620,10,"");
        textDamage = new Text(500, 10, "");
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

        textPoints.setText("Player points: " + points);
        textPoints.draw();

    }

    @Override
    public void showHitPoints(int hitPoints, int maxHitpoints) {

        textHitPoints.setText("Player Health: " + hitPoints + " | " + maxHitpoints);
        textHitPoints.draw();

    }

    @Override
    public void showSpeed(int speed) {

        textSpeed.setText("Player speed: " + speed);
        textSpeed.draw();

    }

    @Override
    public void showDamage(int damage) {

        textDamage.setText("Player damage: " + damage);
        textDamage.draw();

    }

    @Override
    public void showProjectiles(int projectile) {

        textProjectiles.setText("Player projectiles: " + projectile);
        textProjectiles.draw();

    }

    @Override
    public void hide() {
        //text.delete();
    }
}
