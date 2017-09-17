package by.thedrop.materialquestredesigned.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.Constants.Constants;
import by.thedrop.materialquestredesigned.Levels.Level_2;
import by.thedrop.materialquestredesigned.R;

public class LevelHolderActivity extends AppCompatActivity {

    private static Fragment currentLevel;
    private static FragmentManager mFragmentManager;

    @BindView(R.id.activity_level_holder)
    FrameLayout mActivityLevelHolder;
    @BindView(R.id.no_levels_textview)
    TextView mNoLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_holder);

        ButterKnife.bind(this);
        refreshViews();

        currentLevel = Constants.levels.get(MainActivity.currentLevel - 1).getFragment();

        mFragmentManager = getSupportFragmentManager();
        if (currentLevel != null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.activity_level_holder, currentLevel)
                    .commit();
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }

        mNoLevels.setTypeface(MainActivity.fontFutura);
    }

    public static void changeLevel(int currentLevelNumber, Context context) {
        if (currentLevel != null) {
            mFragmentManager.beginTransaction()
                    .detach(currentLevel)
                    .commit();
        }
        if (currentLevelNumber == Constants.levels.size() + 1) {
            return;
        }
        if (currentLevelNumber + 1 <= Constants.levels.size()) {
            MainActivity.setCurrentLevel(currentLevelNumber + 1, context);
            currentLevel = Constants.levels.get(MainActivity.currentLevel - 1).getFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.activity_level_holder, currentLevel)
                    .commit();
        }
    }

    private void refreshViews() {
        mActivityLevelHolder.setBackgroundColor(ColorPalette.get().background_level_holder);
        mNoLevels.setTextColor(ColorPalette.get().text_grey_main);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (currentLevel instanceof Level_2) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    ((Level_2) currentLevel).onKeyVolumeUp();
                    return true;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    ((Level_2) currentLevel).onKeyVolumeDown();
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}