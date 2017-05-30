package org.academiadecodigo.bootcamp8.topdownshooter.gameobjects;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Filipe Santos Sá
 * <Code Cadet> Cyrille Feijó
 */

public interface Hittable {

    void hit(int damage);
    boolean isDead();
}
