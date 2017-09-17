package by.thedrop.materialquestredesigned.Levels;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
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
 * Created by Semen on 10-Mar-17.
 */

public class Level_3 extends BasicLevel {

    @BindView(R.id.level_3_main_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.level_3_layout)
    RelativeLayout mRelativeLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_03_level, container, false);
        ButterKnife.bind(this, v);

        levelPassed = (TextView) v.findViewById(R.id.level_3_passed);
        levelPassed.setTypeface(MainActivity.fontFutura);
        levelName = (TextView) v.findViewById(R.id.level_3_name);
        levelName.setText(getString(R.string.level_is, 3));
        levelName.setTypeface(MainActivity.fontFutura);
        next = (ImageView) v.findViewById(R.id.level_3_next);

        final ImageView nextFinal = (ImageView) v.findViewById(R.id.level_3_next_final);
        final TextView levelPassedFinal = (TextView) v.findViewById(R.id.level_3_passed_final);
        nextFinal.setVisibility(View.GONE);
        levelPassedFinal.setVisibility(View.GONE);

        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mRelativeLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        levelPassed.setTextColor(ColorPalette.get().level_passed);
        levelPassedFinal.setTextColor(ColorPalette.get().level_passed);

        if(MainActivity.currentTheme == 1){
            nextFinal.setImageResource(R.drawable.next_level);
            next.setImageResource(R.drawable.next_level);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelPassed.setText(R.string.level_3_first);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = next.getWidth() / 2;
                    int cy = next.getHeight() / 2;
                    float radius = next.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(next, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            next.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();

                    nextFinal.setVisibility(View.VISIBLE);
                    levelPassedFinal.setVisibility(View.VISIBLE);
                } else {
                    next.setVisibility(View.GONE);
                }


            }
        });
        nextFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelPassedFinal.setText(R.string.level_3_third);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LevelHolderActivity.changeLevel(3, v.getContext());
                    }
                }, 1500);
            }
        });

        return v;
    }
}
