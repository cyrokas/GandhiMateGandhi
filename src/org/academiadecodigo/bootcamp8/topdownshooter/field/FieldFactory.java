package org.academiadecodigo.bootcamp8.topdownshooter.field;

import org.academiadecodigo.bootcamp8.topdownshooter.representable.gfx.SimpleGFXField;

/**
 * Created by cyrokas on 26-05-2017.
 */
public class FieldFactory {

    //Initializes a field according to argument library
    public static Field getNewField(FieldType fieldType, int rows, int columns) {

        switch (fieldType) {
            case SIMPLE_GFX:
                return new SimpleGFXField(rows, columns);
            default:
                return null;
        }
    }
}
