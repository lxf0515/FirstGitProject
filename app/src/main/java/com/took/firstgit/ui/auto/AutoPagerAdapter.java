package com.took.firstgit.ui.auto;

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

public class AutoPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> dataList = new ArrayList<>();
    /**
     * 默认轮播个数
     */
    public static final int FAKE_BANNER_SIZE = 10000;


    public AutoPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public AutoPagerAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.dataList = list;
    }

    public void setDatas(List<String> list) {
        if (list.size() <= 0) {
            dataList.clear();
            notifyDataSetChanged();
            return;
        }
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //return /*Integer.MAX_VALUE*/dataList.size();
        return FAKE_BANNER_SIZE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //int currentPosition = dataList.size() % position;
        if (position >= dataList.size()) {
            position = position % dataList.size();
        }

        String data = dataList.get(position);

        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_view_layout, null);
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
        }
        bindView(viewHolder, data);

        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return view;
    }

    private void bindView(ViewHolder viewholder, final String data) {
        Glide.with(mContext).load(data).into(viewholder.cardView);


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    class ViewHolder {
        //RoundedImageView welfareImage;
        ImageView cardView;
        ViewHolder(View view) {
            //welfareImage = view.findViewById(R.id.welfare_image);
            cardView = view.findViewById(R.id.photo_album_detail_item_photo);
            view.setTag(this);
        }

        public void reset() {
            cardView.setBackground(mContext.getResources().getDrawable(R.drawable.welfare_default_icon));
        }
    }
}
