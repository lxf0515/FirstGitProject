package com.took.firstgit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.took.firstgit.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_contacts);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_found);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_me);
                    return true;
                case R.id.navigation_dashboard2:
                    mTextMessage.setText(R.string.title_more);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        navigation   = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //避免每一个NavigationView有位移
        BottomNavigationViewHelper.disableShiftMode(navigation);
        initPointView();
    }

    public void initPointView(){
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.message_red_point_layout, menuView, false);
        //添加到Tab上
        itemView.addView(badge);

        TextView count = badge.findViewById(R.id.tv_msg_count);
        count.setVisibility(View.VISIBLE);
        count.setText(String.valueOf(1));

        //如果没有消息，不需要显示的时候那只需要将它隐藏即可
        //count.setVisibility(View.GONE);
    }

}
