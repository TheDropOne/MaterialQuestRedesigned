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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.Constants.Constants;
import by.thedrop.materialquestredesigned.Levels.Level_2;
import by.thedrop.materialquestredesigned.R;

public class LevelHolderActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private static Fragment currentLevel;
    private static FragmentManager mFragmentManager;

    public static RewardedVideoAd mAd;

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

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }

    public static void loadRewardedVideoAd() {
        mAd.loadAd("ca-app-pub-8634096223053663/8143378559", new AdRequest.Builder().build());
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
        if (currentLevelNumber <= Constants.levels.size() + 1) {
            MainActivity.setCurrentLevel(currentLevelNumber, context);
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

    // Required to reward the user.
    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, Constants.levels.get(MainActivity.currentLevel - 1).getHint(), Toast.LENGTH_LONG).show();
        loadRewardedVideoAd();
        // Reward the user.
    }

    // The following listener methods are optional.
    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, R.string.ad_closed, Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, R.string.ad_cannot_load, Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, R.string.ad_watch_to_end, Toast.LENGTH_SHORT).show();
    }
}