package by.thedrop.materialquestredesigned.Templates;

import android.support.v4.app.Fragment;

/**
 * Created by Semen on 08-Mar-17.
 */

public class Level {
    private int number;
    private Fragment mFragment;
    private int hint;

    public Level(int number) {
        this.number = number;
    }

    public Level(int number, Fragment fragment, int hint) {
        this.number = number;
        this.mFragment = fragment;
        this.hint = hint;
    }

    public int getNumber() {
        return number;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public int getHint() {
        return hint;
    }
}
