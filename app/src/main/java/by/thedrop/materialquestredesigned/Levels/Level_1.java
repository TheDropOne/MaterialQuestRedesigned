package by.thedrop.materialquestredesigned.Levels;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Activities.LevelHolderActivity;
import by.thedrop.materialquestredesigned.Activities.MainActivity;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;

/**
 * Created by Semen on 09-Mar-17.
 */

public class Level_1 extends Fragment {

    private static int iteration;
    private static int divider;

    @BindView(R.id.level_1_main_layout)
    RelativeLayout mMainRelativeLayout;
    @BindView(R.id.ad_layout_for_hint_1)
    LinearLayout mAdLayout;
    @BindView(R.id.ad_layout_for_hint_1_text)
    TextView mAdLayoutQuestion;
    @BindView(R.id.ad_layout_for_hint_1_descr)
    TextView mAdLayoutDescr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_01_level, container, false);

        ButterKnife.bind(this, v);

        final ImageView next = (ImageView) v.findViewById(R.id.level_1_next);
        final TextView levelPassed = (TextView) v.findViewById(R.id.level_1_passed);
        levelPassed.setTypeface(MainActivity.fontFutura);
        levelPassed.setVisibility(View.GONE);
        TextView levelName = (TextView) v.findViewById(R.id.level_1_name);
        levelName.setText(getString(R.string.level_is, 1));
        levelName.setTypeface(MainActivity.fontFutura);
        mMainRelativeLayout.setBackgroundColor(ColorPalette.get().background_grey_main);

        mAdLayoutQuestion.setTextColor(ColorPalette.get().text_grey_main);
        mAdLayoutDescr.setTextColor(ColorPalette.get().text_grey_main);
        if(MainActivity.currentTheme == 2){
            next.setImageResource(R.drawable.next_white);
        }
        mAdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LevelHolderActivity.mAd.isLoaded()) {
                    LevelHolderActivity.mAd.show();
                }else{
                    Toast.makeText(view.getContext(),R.string.ad_not_loaded, Toast.LENGTH_SHORT).show();
                }
            }
        });

        iteration = 20;
        divider = 20;
        ((SwitchCompat) v.findViewById(R.id.level_1_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                v.findViewById(R.id.level_1_layout).setAlpha(changeBackground());
                if (iteration == 10) {
                    next.setVisibility(View.VISIBLE);
                    divider = 10;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (divider == 20) {
                    Toast.makeText(getContext(), R.string.level_1_first, Toast.LENGTH_SHORT).show();
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
                        iteration = 20;
                    } else {
                        next.setVisibility(View.GONE);
                    }
                } else {
                    mAdLayout.setVisibility(View.GONE);
                    levelPassed.setVisibility(View.VISIBLE);
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
                    } else {
                        next.setVisibility(View.GONE);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(1, v.getContext());
                        }
                    }, 1500);
                }
            }
        });
        return v;
    }

    private float changeBackground() {
        return (float) (iteration--) / divider;
    }
}
