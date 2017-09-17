package by.thedrop.materialquestredesigned.Constants;

import java.util.ArrayList;

import by.thedrop.materialquestredesigned.Levels.Level_1;
import by.thedrop.materialquestredesigned.Levels.Level_2;
import by.thedrop.materialquestredesigned.Levels.Level_3;
import by.thedrop.materialquestredesigned.Levels.Level_4;
import by.thedrop.materialquestredesigned.Levels.Level_5;
import by.thedrop.materialquestredesigned.Levels.Level_6;
import by.thedrop.materialquestredesigned.Levels.Level_7;
import by.thedrop.materialquestredesigned.Templates.Level;

/**
 * Created by Semen on 16-Sep-17.
 */

public class Constants {
    public static ArrayList<Level> levels = new ArrayList<>();

    public static void initLevels() {
        levels = new ArrayList<>();
        levels.add(new Level(1, new Level_1()));
        levels.add(new Level(2, new Level_2()));
        levels.add(new Level(3, new Level_3()));
        levels.add(new Level(4, new Level_4()));
        levels.add(new Level(5, new Level_5()));
        levels.add(new Level(6, new Level_6()));
        levels.add(new Level(7, new Level_7()));
        //levels.add(new Level(8, new Level_8()));
        //levels.add(new Level(9, new Level_9()));
        //levels.add(new Level(10, new Level_10()));
    }
}
