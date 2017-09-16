package by.thedrop.materialquestredesigned.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import by.thedrop.materialquestredesigned.R;

public class LevelsActivity extends AppCompatActivity {

    private static Fragment currentLevel;
    private TextView mNoLevels;
    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


    }
}
