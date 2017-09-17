package by.thedrop.materialquestredesigned.Levels;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Activities.LevelHolderActivity;
import by.thedrop.materialquestredesigned.Activities.MainActivity;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;

public class Level_2 extends Fragment {

    @BindView(R.id.level_2_layout)
    RelativeLayout level_2_layout;
    @BindView(R.id.level_2_el2_2)
    TextView level_2_el2_2;
    @BindView(R.id.level_2_el2_1)
    TextView level_2_el2_1;
    @BindView(R.id.level_2_next)
    ImageView mNextImageButton;
    @BindView(R.id.level_2_el_edit)
    EditText editLabel;
    @BindView(R.id.level_2_name)
    TextView levelName;
    @BindView(R.id.level_2_passed)
    TextView levelPassed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_02_level, container, false);
        ButterKnife.bind(this,v);

        levelPassed.setTypeface(MainActivity.fontFutura);
        levelPassed.setTextColor(ColorPalette.get().background_inverse);
        levelPassed.setVisibility(View.GONE);
        levelName.setText(getString(R.string.level_is, 2));
        levelName.setTypeface(MainActivity.fontFutura);
        levelName.setTextColor(ColorPalette.get().background_inverse);
        level_2_layout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mNextImageButton.setVisibility(View.GONE);

        editLabel.setVisibility(View.GONE);
        final TextView level_2_elX = (TextView) v.findViewById(R.id.level_2_elX);
        level_2_elX.setTextColor(ColorPalette.get().background_inverse);
        level_2_el2_1.setTypeface(MainActivity.fontFutura);
        level_2_el2_1.setTextColor(ColorPalette.get().background_inverse);
        level_2_el2_2.setTypeface(MainActivity.fontFutura);
        level_2_el2_2.setTextColor(ColorPalette.get().background_inverse);

        final Animation rotateAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.level_2_cross);
        final Animation rotateAnimationTwo = AnimationUtils.loadAnimation(v.getContext(), R.anim.level_2_two);
        level_2_elX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_elX.startAnimation(rotateAnimation);
            }
        });
        level_2_el2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_el2_1.startAnimation(rotateAnimationTwo);
            }
        });
        level_2_el2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_2_el2_2.startAnimation(rotateAnimationTwo);
            }
        });
        if(MainActivity.currentTheme == 1){
            mNextImageButton.setImageResource(R.drawable.next_level);
        }
        mNextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Integer.parseInt((String) level_2_el2_1.getText()) * Integer.parseInt((String) level_2_el2_2.getText()) == Integer.parseInt(editLabel.getText().toString())) {
                        Toast.makeText(getContext(), R.string.level_2_second, Toast.LENGTH_SHORT).show();
                        levelName.setVisibility(View.VISIBLE);
                        levelPassed.setVisibility(View.VISIBLE);

                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LevelHolderActivity.changeLevel(2, v.getContext());
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(getContext(), R.string.level_2_first, Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(getContext(), R.string.level_2_first, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public void onKeyVolumeUp() {
        level_2_el2_2.setText(String.valueOf(Integer.parseInt((String) level_2_el2_2.getText()) + 1));
        if (Integer.parseInt((String) level_2_el2_2.getText()) > 10 || Integer.parseInt((String) level_2_el2_1.getText()) < 1) {
            editLabel.setVisibility(View.VISIBLE);
            mNextImageButton.setVisibility(View.VISIBLE);
        }
    }

    public void onKeyVolumeDown() {
        level_2_el2_1.setText(String.valueOf(Integer.parseInt((String) level_2_el2_1.getText()) - 1));
        if (Integer.parseInt((String) level_2_el2_2.getText()) > 10 || Integer.parseInt((String) level_2_el2_1.getText()) < 1) {
            editLabel.setVisibility(View.VISIBLE);
            mNextImageButton.setVisibility(View.VISIBLE);
        }
    }
}
