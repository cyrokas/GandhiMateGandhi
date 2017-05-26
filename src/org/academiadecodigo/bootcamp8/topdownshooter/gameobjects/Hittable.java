package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

/**
 * Created by codecadet on 24/05/17.
 */
public interface Hittable {

    void hit(int damage);
    boolean isDead();

}
