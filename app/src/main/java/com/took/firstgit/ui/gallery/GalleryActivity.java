package com.took.firstgit.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.took.firstgit.R;
import com.took.firstgit.utils.DataUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GalleryActivity extends AppCompatActivity{

    private MyGallery gallery;
    private int index = 0;
    private final int AUTOPLAY = 2;
    List<String> datas;
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_main);
        this.mContext = this;
        initView();
    }

    private void initView(){
        gallery = findViewById(R.id.mygallery);

        datas = DataUtils.getBannerList();
        ImageAdapter adapter = new ImageAdapter();
        gallery.setAdapter(adapter);
        gallery.setSpacing(50); //图片之间的间距
        gallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % datas.size());

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 设置点击事件监听
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GalleryActivity.this, "当前位置position:"+position+"的图片被选中了", Toast.LENGTH_SHORT).show();
            }
        });

        Timer timer = new Timer();
        timer.schedule(task, 3000, 3000);
    }

    /**
     * 定时器，实现自动播放
     */
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = AUTOPLAY;
            index = gallery.getSelectedItemPosition();
            index++;
            handler.sendMessage(message);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTOPLAY:
                    gallery.setSelection(index);
                    break;
                default:
                    break;
            }
        }
    };

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//用于循环滚动
        }

        @Override
        public Object getItem(int position) {
            if (position >= datas.size()) {
                position = position % datas.size();
            }

            return position;
        }

        @Override
        public long getItemId(int position) {
            if (position >= datas.size()) {
                position = position % datas.size();
            }

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView != null) {
                imageView = (ImageView) convertView;
            } else {
                imageView = new ImageView(GalleryActivity.this);
            }

            if (position >= datas.size()) {
                position = position % datas.size();
            }

            Glide.with(mContext).load(datas.get(position)).into(imageView);
            return imageView;
        }
    }
}
