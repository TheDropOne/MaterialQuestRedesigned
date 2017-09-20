package by.thedrop.materialquestredesigned.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.Constants.Constants;
import by.thedrop.materialquestredesigned.R;
import by.thedrop.materialquestredesigned.Templates.LevelsAdapter;

public class LevelsActivity extends AppCompatActivity {

    @BindView(R.id.activity_levels_layout)
    RelativeLayout mLevelsRelativeLayout;
    @BindView(R.id.levels_activity_label)
    TextView mLevelsTextView;
    @BindView(R.id.levels_activity_recycler_view)
    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        ButterKnife.bind(this);
        refreshViews();

        new LoadLevels().execute();
    }

    private void refreshViews() {
        mLevelsRelativeLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mLevelsTextView.setTextColor(ColorPalette.get().text_grey_main);
    }

    class LoadLevels extends AsyncTask<Void, Void, Void> {

        LevelsAdapter mAdapter;

        @Override
        protected Void doInBackground(Void... voids) {
            mAdapter = new LevelsAdapter(Constants.levels);
            mGridLayoutManager = new GridLayoutManager(LevelsActivity.this, 5);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                assert mRecyclerView != null;
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(LevelsActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new LoadLevels().execute();
    }
}
