package org.academiadecodigo.bootcamp8.gandhimategandhi.field;

import org.academiadecodigo.bootcamp8.gandhimategandhi.representable.gfx.SimpleGFXField;

/**
 * Developed @ <Academia de Código_>
 *
 * Created by
 * <Code Cadet> Cyrille Feijó
 * <Code Cadet> Filipe Santos Sá
 */

public class FieldFactory {

    //Initializes a field according to argument library
    public static Field getNewField(FieldType fieldType, int rows, int columns) {

        switch (fieldType) {
            case SIMPLE_GFX:
                return new SimpleGFXField(rows, columns);
            default:
                return new SimpleGFXField(rows, columns);
        }
    }
}
