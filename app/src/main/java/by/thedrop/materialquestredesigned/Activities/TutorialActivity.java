package by.thedrop.materialquestredesigned.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;

public class TutorialActivity extends AppCompatActivity {

    @BindView(R.id.activity_tutorial_main_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.activity_tutorial_layout)
    RelativeLayout mLayout;
    @BindView(R.id.activity_tutorial_rules)
    TextView mRules;


    @BindView(R.id.activity_rules_check_1)
    CheckBox mCheck1;
    @BindView(R.id.activity_rules_check_2)
    CheckBox mCheck2;
    @BindView(R.id.activity_rules_check_3)
    CheckBox mCheck3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);

        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mLayout.setBackgroundColor(ColorPalette.get().background_grey_main);

        if(MainActivity.currentTheme == 2){
            mRules.setTextColor(ContextCompat.getColor(this, R.color.tutorial));
        }

        mCheck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheck1.isChecked() && mCheck2.isChecked() && mCheck3.isChecked()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }
            }
        });
        mCheck2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheck1.isChecked() && mCheck2.isChecked() && mCheck3.isChecked()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 500);
                }
            }
        });
        mCheck3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheck1.isChecked() && mCheck2.isChecked() && mCheck3.isChecked()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 500);
                }
            }
        });
    }
}
