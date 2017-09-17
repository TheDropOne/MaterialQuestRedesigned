package by.thedrop.materialquestredesigned.Levels;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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

public class Level_6 extends BasicLevel {

    boolean canSolve;

    @BindView(R.id.level_6_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.level_6_name)
    TextView mLevelName;
    @BindView(R.id.level_6_passed)
    TextView mPassed;
//    @BindView(R.id.level_6_next)
//    ImageView mNext;

    @BindView(R.id.level_6_date_picker)
    DatePicker mDatePicker;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_06_level, container, false);
        ButterKnife.bind(this, v);

        mPassed.setTypeface(MainActivity.fontFutura);
        mLevelName.setText(getString(R.string.level_is, 6));
        mLevelName.setTypeface(MainActivity.fontFutura);
        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mPassed.setTextColor(ColorPalette.get().level_passed);
        mPassed.setVisibility(View.GONE);
        canSolve = true;

//        if (MainActivity.currentTheme == 1) {
//            mNext.setImageResource(R.drawable.next_level);
//        }
//        mNext.setVisibility(View.GONE);

        mDatePicker.init(1970, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                Log.i("Level", "" + i + " " + i1 + " " + i2);
                if (i == 2001 && i1 == 8 && i2 == 11) {
                    mPassed.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(6, v.getContext());
                        }
                    }, 2000);
                }
            }
        });

        return v;
    }
}