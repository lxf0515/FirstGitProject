package com.took.firstgit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.took.firstgit.ui.Menu.FloatingActionMenuActivity;
import com.took.firstgit.ui.activity.BottomSlidingTabStripActivity;
import com.took.firstgit.ui.activity.BottomTabActivity;
import com.took.firstgit.ui.activity.PagerBottomTabStripActivity;
import com.took.firstgit.ui.activity.lock.LockViewActivity;
import com.took.firstgit.ui.activity.lock.MyLockViewActivity;
import com.took.firstgit.ui.auto.AutoViewPagerCardActivity;
import com.took.firstgit.ui.dialog.BottomDialogView;
import com.took.firstgit.ui.gallery.GalleryActivity;
import com.took.firstgit.ui.guide.GuideActivity;
import com.took.firstgit.ui.notice.ONoticeActivity;
import com.took.firstgit.ui.password.PassWordActivity;
import com.took.firstgit.ui.picture.PictureSelectActivity;
import com.took.firstgit.ui.tab.SlidingTabStripActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button _button1,_button2,_button3,_button4
            ,_button5,_button6,_button7,_button8,_button9
            ,_button10,_button11,_button0,_button12,_button13,_button14,_button15;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_home);
        initView();
        setListener();
    }

    private void initView(){
        _button0 = findViewById(R.id.home_but_0);
        _button1 = findViewById(R.id.home_but_1);
        _button2 = findViewById(R.id.home_but_2);
        _button3 = findViewById(R.id.home_but_3);
        _button4 = findViewById(R.id.home_but_4);
        _button5 = findViewById(R.id.home_but_5);
        _button6 = findViewById(R.id.home_but_6);
        _button7 = findViewById(R.id.home_but_7);
        _button8 = findViewById(R.id.home_but_8);
        _button9 = findViewById(R.id.home_but_9);
        _button10 = findViewById(R.id.home_but_10);
        _button11 = findViewById(R.id.home_but_11);
        _button12 = findViewById(R.id.home_but_12);
        _button13 = findViewById(R.id.home_but_13);
        _button14 = findViewById(R.id.home_but_14);
        _button15 = findViewById(R.id.home_but_15);
    }

    private void setListener(){
        _button1.setOnClickListener(this);
        _button2.setOnClickListener(this);
        _button3.setOnClickListener(this);
        _button4.setOnClickListener(this);
        _button5.setOnClickListener(this);
        _button6.setOnClickListener(this);
        _button7.setOnClickListener(this);
        _button8.setOnClickListener(this);
        _button9.setOnClickListener(this);
        _button10.setOnClickListener(this);
        _button11.setOnClickListener(this);
        _button0.setOnClickListener(this);
        _button12.setOnClickListener(this);
        _button13.setOnClickListener(this);
        _button14.setOnClickListener(this);
        _button15.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_but_1:
                startActivity(MainActivity.class,false);
                break;
            case R.id.home_but_2:
                startActivity(PagerBottomTabStripActivity.class,false);
                break;
            case R.id.home_but_3:
                startActivity(BottomTabActivity.class,false);
                break;
            case R.id.home_but_4:
                startActivity(LockViewActivity.class,false);
                break;
            case R.id.home_but_5:
                startActivity(MyLockViewActivity.class,false);
                break;
            case R.id.home_but_6:
                startActivity(SlidingTabStripActivity.class,false);
                break;
            case R.id.home_but_7:
                showDialog();
                break;
            case R.id.home_but_8:
                startActivity(PassWordActivity.class,false);
                break;
            case R.id.home_but_9:
                startActivity(PictureSelectActivity.class,false);
                break;
            case R.id.home_but_10:
                startActivity(com.took.firstgit.ui.main.MainActivity.class,false);
                break;
            case R.id.home_but_11:
                startActivity(GuideActivity.class,false);
                break;
            case R.id.home_but_0:
                startActivity(BottomSlidingTabStripActivity.class,false);
                break;
            case R.id.home_but_12:
                startActivity(AutoViewPagerCardActivity.class,false);
                break;
            case R.id.home_but_13:
                startActivity(GalleryActivity.class,false);
                break;
            case R.id.home_but_14:
                startActivity(FloatingActionMenuActivity.class,false);
                break;
            case R.id.home_but_15:
                startActivity(ONoticeActivity.class,false);
                break;
        }
    }

    private void startActivity(Class<?> cla,boolean isFinish){
        Intent intent = new Intent(this,cla);
        startActivity(intent);
    }

    private void showDialog(){
        BottomDialogView bottomDialogView = new BottomDialogView(this,false,false);
        bottomDialogView.show();
    }
}
