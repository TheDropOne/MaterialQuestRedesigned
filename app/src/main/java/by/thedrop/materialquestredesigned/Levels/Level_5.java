package by.thedrop.materialquestredesigned.Levels;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Activities.LevelHolderActivity;
import by.thedrop.materialquestredesigned.Activities.MainActivity;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;

/**
 * Created by Semen on 17-Sep-17.
 */

public class Level_5 extends BasicLevel {

    boolean canSolve;
    private final int DELAY = 600;

    @BindView(R.id.level_5_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.level_5_name)
    TextView mLevelName;
    @BindView(R.id.level_5_passed)
    TextView mPassed;
    @BindView(R.id.level_5_next)
    ImageView mNext;

    @BindView(R.id.level_5_progress_bar_1)
    ProgressBar mProgressBar1;
    @BindView(R.id.level_5_progress_bar_2)
    ProgressBar mProgressBar2;
    @BindView(R.id.level_5_progress_bar_3)
    ProgressBar mProgressBar3;
    @BindView(R.id.level_5_progress_bar_4)
    ProgressBar mProgressBar4;
    @BindView(R.id.level_5_switch_1)
    SwitchCompat mSwitchCompat1;
    @BindView(R.id.level_5_switch_2)
    SwitchCompat mSwitchCompat2;
    @BindView(R.id.level_5_switch_3)
    SwitchCompat mSwitchCompat3;
    @BindView(R.id.level_5_switch_4)
    SwitchCompat mSwitchCompat4;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_05_level, container, false);
        ButterKnife.bind(this, v);

        mPassed.setTypeface(MainActivity.fontFutura);
        mLevelName.setText(getString(R.string.level_is, 5));
        mLevelName.setTypeface(MainActivity.fontFutura);
        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mPassed.setTextColor(ColorPalette.get().level_passed);
        mPassed.setVisibility(View.GONE);
        canSolve = true;

        mProgressBar1.setVisibility(View.GONE);
        mProgressBar2.setVisibility(View.GONE);
        mProgressBar3.setVisibility(View.GONE);
        mProgressBar4.setVisibility(View.GONE);

        if (MainActivity.currentTheme == 1) {
            mNext.setImageResource(R.drawable.next_level);
        }

        mSwitchCompat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mProgressBar1.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar1.setVisibility(View.GONE);
                        mSwitchCompat1.setChecked(false);
                    }
                }, DELAY);
            }
        });
        mSwitchCompat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mProgressBar2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar2.setVisibility(View.GONE);
                        mSwitchCompat2.setChecked(false);
                    }
                }, DELAY);
            }
        });
        mSwitchCompat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mProgressBar3.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar3.setVisibility(View.GONE);
                        mSwitchCompat3.setChecked(false);
                    }
                }, DELAY);
            }
        });
        mSwitchCompat4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mProgressBar4.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar4.setVisibility(View.GONE);
                        mSwitchCompat4.setChecked(false);
                    }
                }, DELAY);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areAllVisible()) {
                    mPassed.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(5, v.getContext());
                        }
                    }, 1500);
                } else {
                    canSolve = false;
                    mNext.setVisibility(View.GONE);
                }
            }
        });
        return v;
    }

    private boolean areAllVisible() {
        boolean visible = true;
        if (mProgressBar1.getVisibility() == View.GONE
                || mProgressBar2.getVisibility() == View.GONE
                || mProgressBar3.getVisibility() == View.GONE
                || mProgressBar4.getVisibility() == View.GONE
                ) {
            visible = false;
        }
        return visible;
    }
}
