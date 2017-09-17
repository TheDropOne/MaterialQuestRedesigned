package by.thedrop.materialquestredesigned.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.Constants.Constants;
import by.thedrop.materialquestredesigned.R;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "APP_PREFERENCES";

    public static final String MAX_AVAILABLE_LEVEL = "MAX_AVAILABLE_LEVEL";
    public static final String CURRENT_THEME = "CURRENT_THEME";
    public static final String CURRENT_LEVEL = "CURRENT_LEVEL";

    //1 - Light, 2 - Dark, Others - Additional
    public static int currentTheme;
    public static int currentLevel;
    public static int maxAvailableLevel;

    private boolean firstLaunch = false;
    public static Typeface fontFutura;
    public static SharedPreferences mSharedPreferences;

    @BindView(R.id.activity_main_layout)
    RelativeLayout mMainRelativeLayout;
    @BindView(R.id.main_activity_switch)
    SwitchCompat mThemeSwitcher;
    @BindView(R.id.main_activity_run_button)
    Button mRunButton;
    @BindView(R.id.main_activity_current_level)
    TextView mCurrentLevelTextView;
    @BindView(R.id.main_activity_levels)
    ImageButton mLevelsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initPreferences();
        initFontsColors();

        Constants.initLevels();

        refreshViews();

        addEventListeners();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mCurrentLevelTextView.setText(getString(R.string.level_is, currentLevel));
    }

    private void refreshViews() {
        mMainRelativeLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mCurrentLevelTextView.setTextColor(ColorPalette.get().text_grey_main);
        mLevelsImageView.setBackgroundColor(ColorPalette.get().background_grey_main);
    }

    public static void setCurrentLevel(int currentLevel, Context context) {
        MainActivity.currentLevel = currentLevel;
        if (currentLevel >= maxAvailableLevel) {
            maxAvailableLevel = currentLevel;
        }
        writeDataToPrefs(context);
    }

    private void addEventListeners() {
        mThemeSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                currentTheme = currentTheme == 1 ? 2 : 1;
                writeDataToPrefs(MainActivity.this);

                ColorPalette.get().initColors(currentTheme, MainActivity.this);
                refreshViews();
            }
        });
        mRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstLaunch) {
                    firstLaunch = false;

                    mSharedPreferences = view.getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("FIRST_LAUNCH", firstLaunch);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LevelHolderActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        mLevelsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initPreferences() {
        mSharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSharedPreferences != null) {
            firstLaunch = mSharedPreferences.getBoolean("FIRST_LAUNCH", true);
            currentTheme = mSharedPreferences.getInt(CURRENT_THEME, 1);
            currentLevel = mSharedPreferences.getInt(CURRENT_LEVEL, 1);
            maxAvailableLevel = mSharedPreferences.getInt(MAX_AVAILABLE_LEVEL, 1);
        } else {
            currentTheme = 1;
            currentLevel = 1;
            maxAvailableLevel = 1;
        }
    }

    private static void writeDataToPrefs(Context context) {
        mSharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CURRENT_THEME, currentTheme);
        editor.putInt(CURRENT_LEVEL, currentLevel);
        editor.putInt(MAX_AVAILABLE_LEVEL, maxAvailableLevel);
        editor.apply();
    }

    private void initFontsColors() {
        fontFutura = Typeface.createFromAsset(getAssets(), "Fonts/futura.ttf");
        ColorPalette.get().initColors(currentTheme, this);
    }
}
