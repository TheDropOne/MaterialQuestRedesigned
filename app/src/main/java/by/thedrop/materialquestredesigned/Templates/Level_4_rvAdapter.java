package by.thedrop.materialquestredesigned.Templates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.thedrop.materialquestredesigned.R;

/**
 * Created by Semen on 17-Sep-17.
 */

public class Level_4_rvAdapter extends RecyclerView.Adapter<Level_4_rvAdapter.MyViewHolder> {
    private List<Level_4_card> cards;

    public Level_4_rvAdapter(List<Level_4_card> cards) {
        this.cards = cards;
    }

    @Override
    public Level_4_rvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_level_4, parent, false);
        return new Level_4_rvAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Level_4_rvAdapter.MyViewHolder holder, final int position) {
        final Level_4_card card = cards.get(position);

        TextView title = holder.mLevelTitle;
        TextView descr = holder.mLevelDescr;
        final View view = holder.mItemView;


        title.setText(card.getTitle());
        descr.setText(card.getDescription());
        //descr.setBackgroundColor(ColorPalette.get().text_grey_levels_background);
        //descr.setTextColor(ColorPalette.get().text_grey_levels);

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mLevelTitle;
        private TextView mLevelDescr;
        private View mItemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;

            mLevelTitle = (TextView) itemView.findViewById(R.id.item_level_4_title);
            mLevelDescr = (TextView) itemView.findViewById(R.id.item_level_4_descr);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            mItemView.setOnClickListener(onClickListener);
        }
    }
}
