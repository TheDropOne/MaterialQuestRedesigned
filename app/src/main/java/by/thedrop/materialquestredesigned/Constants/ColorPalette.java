package by.thedrop.materialquestredesigned.Constants;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import by.thedrop.materialquestredesigned.R;

/**
 * Created by Semen on 16-Sep-17.
 */

public class ColorPalette {

    private static ColorPalette sColorPalette;

    public int black;
    public int white;
    public int background_light_grey;
    public int background_dark_grey;
    public int text_light_grey;
    public int text_dark_grey;

    public int background_grey_main;
    public int text_grey_main;

    public static ColorPalette get() {
        if (sColorPalette == null) {
            sColorPalette = new ColorPalette();
        }
        return sColorPalette;
    }

    public void initColors(int theme, Context context) {
        black = ContextCompat.getColor(context, R.color.black);
        white = ContextCompat.getColor(context, R.color.white);
        background_light_grey = ContextCompat.getColor(context, R.color.background_light_grey);
        background_dark_grey = ContextCompat.getColor(context, R.color.background_dark_grey);
        text_light_grey = ContextCompat.getColor(context, R.color.text_light_grey);
        text_dark_grey = ContextCompat.getColor(context, R.color.text_dark_grey);

        switch (theme) {
            case 1: // Light
                background_grey_main = background_light_grey;
                text_grey_main = text_light_grey;
                break;
            case 2: // Dark
                background_grey_main = background_dark_grey;
                text_grey_main = text_dark_grey;
                break;
        }
    }
}
