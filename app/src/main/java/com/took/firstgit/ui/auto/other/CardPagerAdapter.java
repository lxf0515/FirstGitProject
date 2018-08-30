package com.took.firstgit.ui.auto.other;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.took.firstgit.R;
import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private Context mContext;

    public CardPagerAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(List<CardItem> items) {
        for(int i= 0;i<items.size();i++) {
            mViews.add(null);
        }
        this.mData = items;
        notifyDataSetChanged();
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_view_layout, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
        }

        if (position >= mData.size()) position = position % mData.size();
        CardItem data = mData.get(position);

        Glide.with(mContext).load(data.getText()).into(viewHolder.imageView);

        if (mBaseElevation == 0) mBaseElevation = viewHolder.cardView.getCardElevation();
        viewHolder.cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, viewHolder.cardView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bindView(ViewHolder viewholder, final CardItem data) {
        Glide.with(mContext).load(data.getText()).into(viewholder.imageView);
    }

    class ViewHolder {
        ImageView imageView;
        CardView  cardView;
        ViewHolder(View view) {
            imageView = view.findViewById(R.id.photo_album_detail_item_photo);
            cardView = view.findViewById(R.id.cardView_image);
            view.setTag(this);
        }
    }

}
