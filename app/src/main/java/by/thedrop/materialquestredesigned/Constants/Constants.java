package by.thedrop.materialquestredesigned.Constants;

import java.util.ArrayList;

import by.thedrop.materialquestredesigned.Levels.Level_1;
import by.thedrop.materialquestredesigned.Levels.Level_2;
import by.thedrop.materialquestredesigned.Levels.Level_3;
import by.thedrop.materialquestredesigned.Levels.Level_4;
import by.thedrop.materialquestredesigned.Levels.Level_5;
import by.thedrop.materialquestredesigned.Levels.Level_6;
import by.thedrop.materialquestredesigned.Levels.Level_7;
import by.thedrop.materialquestredesigned.R;
import by.thedrop.materialquestredesigned.Templates.Level;

/**
 * Created by Semen on 16-Sep-17.
 */

public class Constants {
    public static ArrayList<Level> levels = new ArrayList<>();

    public static void initLevels() {
        levels = new ArrayList<>();
        levels.add(new Level(1, new Level_1(), R.string.level_1_hint));
        levels.add(new Level(2, new Level_2(), R.string.level_2_hint));
        levels.add(new Level(3, new Level_3(), R.string.level_3_hint));
        levels.add(new Level(4, new Level_4(), R.string.level_4_hint));
        levels.add(new Level(5, new Level_5(), R.string.level_5_hint));
        levels.add(new Level(6, new Level_6(), R.string.level_6_hint));
        levels.add(new Level(7, new Level_7(), R.string.level_7_hint));
        //levels.add(new Level(8, new Level_8()));
        //levels.add(new Level(9, new Level_9()));
        //levels.add(new Level(10, new Level_10()));
    }

    public static int getLevelsCount(){
        return levels.size();
    }
}
