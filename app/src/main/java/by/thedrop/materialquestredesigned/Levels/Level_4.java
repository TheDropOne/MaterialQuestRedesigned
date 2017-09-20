package by.thedrop.materialquestredesigned.Levels;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.thedrop.materialquestredesigned.Activities.LevelHolderActivity;
import by.thedrop.materialquestredesigned.Activities.MainActivity;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;
import by.thedrop.materialquestredesigned.Templates.Level_4_card;
import by.thedrop.materialquestredesigned.Templates.Level_4_rvAdapter;

/**
 * Created by Semen on 17-Sep-17.
 */

public class Level_4 extends BasicLevel implements SwipeRefreshLayout.OnRefreshListener {

    boolean canSolve;
    private Level_4_rvAdapter mAdapter;

    @BindView(R.id.level_4_layout)
    RelativeLayout mMainLayout;
    @BindView(R.id.level_4_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.level_4_name)
    TextView mLevelName;
    @BindView(R.id.level_4_passed)
    TextView mPassed;
    @BindView(R.id.level_4_next)
    ImageView mNext;
    @BindView(R.id.level_4_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.ad_layout_for_hint_4)
    LinearLayout mAdLayout;
    @BindView(R.id.ad_layout_for_hint_4_text)
    TextView mAdLayoutQuestion;
    @BindView(R.id.ad_layout_for_hint_4_descr)
    TextView mAdLayoutDescr;

    List<Level_4_card> mCards;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_04_level, container, false);
        ButterKnife.bind(this, v);

        mPassed.setTypeface(MainActivity.fontFutura);
        mLevelName.setText(getString(R.string.level_is, 4));
        mLevelName.setTypeface(MainActivity.fontFutura);

        mMainLayout.setBackgroundColor(ColorPalette.get().background_grey_main);
        mPassed.setTextColor(ColorPalette.get().level_passed);
        mPassed.setVisibility(View.GONE);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.GRAY);

        mCards = new ArrayList<>();
        canSolve = true;

        mAdLayoutQuestion.setTextColor(ColorPalette.get().text_grey_main);
        mAdLayoutDescr.setTextColor(ColorPalette.get().text_grey_main);
        mAdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LevelHolderActivity.mAd.isLoaded()) {
                    LevelHolderActivity.mAd.show();
                }else {
                    Toast.makeText(view.getContext(), R.string.ad_not_loaded, Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (MainActivity.currentTheme == 1) {
            mNext.setImageResource(R.drawable.next_level);
        }

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCards.size() < 3) {
                    mNext.setVisibility(View.GONE);
                    canSolve = false;
                }
                if (mCards.size() == 3) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int cx = mNext.getWidth() / 2;
                        int cy = mNext.getHeight() / 2;
                        float radius = mNext.getWidth();
                        Animator anim = ViewAnimationUtils.createCircularReveal(mNext, cx, cy, radius, 500);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mNext.setVisibility(View.GONE);
                            }
                        });
                        anim.start();
                    } else {
                        mNext.setVisibility(View.GONE);
                    }
                    canSolve = false;
                }
                if (mCards.size() == 4) {
                    mPassed.setVisibility(View.VISIBLE);
                    mAdLayout.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LevelHolderActivity.changeLevel(4, v.getContext());
                        }
                    }, 1500);
                }
            }
        });

        new LoadRV().execute();

        return v;
    }

    class LoadRV extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mAdapter = new Level_4_rvAdapter(mCards);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onRefresh() {
        Level_4_card card = null;
        switch (mCards.size()) {
            case 0:
                card = new Level_4_card("Не нажимай на кнопку", "Не надо");
                break;
            case 1:
                card = new Level_4_card("Тебе что", "Херово что-ль сказали?");
                break;
            case 2:
                card = new Level_4_card("АААААААААААААААААА", "БЛ№№№№№ТЬ");
                break;
            case 3:
                card = new Level_4_card("Ну как знаешь...", "Мое дело предупредить");
                break;
        }
        if (card != null && canSolve) {
            final Level_4_card finalCard = card;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mCards.add(finalCard);
                    mAdapter.notifyDataSetChanged();
                }
            }, 1000);
            Log.i("Level", "" + mCards.size());
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
