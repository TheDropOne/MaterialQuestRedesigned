package by.thedrop.materialquestredesigned.Templates;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.thedrop.materialquestredesigned.Activities.LevelHolderActivity;
import by.thedrop.materialquestredesigned.Activities.MainActivity;
import by.thedrop.materialquestredesigned.Constants.ColorPalette;
import by.thedrop.materialquestredesigned.R;


/**
 * Created by Semen on 08-Mar-17.
 */

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.MyViewHolder> {
    private List<Level> levels;

    public LevelsAdapter(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public LevelsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_level, parent, false);
        return new LevelsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LevelsAdapter.MyViewHolder holder, final int position) {
        final String string = String.valueOf(levels.get(position).getNumber());

        TextView level = holder.mLevelTextview;
        CardView levelCard = holder.mLevelCard;
        final View view = holder.mItemView;

        level.setText(string);
        level.setBackgroundColor(ColorPalette.get().text_grey_levels_background);
        level.setTextColor(ColorPalette.get().text_grey_levels);
        int textSize = 24;
        level.setTextSize(textSize);

        if (position > MainActivity.maxAvailableLevel - 1) {
            levelCard.setAlpha(0.4f);//Color.parseColor("#ededed"));
            levelCard.setEnabled(false);
        }
        if (position == MainActivity.currentLevel - 1) {
            level.setBackgroundColor(Color.parseColor("#FFFF5656"));
        }

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LevelHolderActivity.class);
                MainActivity.currentLevel = position + 1;
                view.getContext().startActivity(intent);
                /*s
                start activity with chosen level
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mLevelTextview;
        private View mItemView;
        private CardView mLevelCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;

            mLevelTextview = (TextView) itemView.findViewById(R.id.item_level_textview);
            mLevelCard = (CardView) itemView.findViewById(R.id.level_card);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            mItemView.setOnClickListener(onClickListener);
        }
    }
}
