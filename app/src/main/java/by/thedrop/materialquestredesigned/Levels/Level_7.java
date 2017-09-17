package by.thedrop.materialquestredesigned.Levels;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class Level_7 extends BasicLevel {

    boolean canSolve;

    @BindView(R.id.level_7_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.level_7_name)
    TextView mLevelName;
    @BindView(R.id.level_7_passed)
    TextView mPassed;


    @BindView(R.id.level_7_alarm)
    ImageView mAlarm;
    CountDownTimer mCountDownTimer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_07_level, container, false);
        ButterKnife.bind(this, v);

        mPassed.setTypeface(MainActivity.fontFutura);
        mLevelName.setText(getString(R.string.level_is, 7));
        mLevelName.setTypeface(MainActivity.fontFutura);
        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mPassed.setTextColor(ColorPalette.get().level_passed);
        mPassed.setVisibility(View.GONE);
        canSolve = true;


        mCountDownTimer = new CountDownTimer(20000, 5000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (canSolve) {
                    mPassed.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(7, v.getContext());
                        }
                    }, 2000);
                }
            }
        }.start();

        mAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canSolve = false;
                mAlarm.setImageResource(R.drawable.alarm_sign);
            }
        });
//        if (MainActivity.currentTheme == 1) {
//            mNext.setImageResource(R.drawable.next_level);
//        }
//        mNext.setVisibility(View.GONE);


        return v;
    }
}