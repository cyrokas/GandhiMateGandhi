package org.academiadecodigo.bootcamp8.topdownshooter.field;

import org.academiadecodigo.bootcamp8.topdownshooter.gameobjects.player.PlayerNumber;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Cyrille Feijó
 */

public interface FieldScore {

    void show(int points, PlayerNumber playerNumber);

    void hide();

}
