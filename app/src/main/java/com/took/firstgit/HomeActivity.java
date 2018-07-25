package com.took.firstgit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.took.firstgit.ui.activity.BottomTabActivity;
import com.took.firstgit.ui.activity.PagerBottomTabStripActivity;
import com.took.firstgit.ui.activity.lock.LockViewActivity;
import com.took.firstgit.ui.activity.lock.MyLockViewActivity;
import com.took.firstgit.ui.dialog.BottomDialogView;
import com.took.firstgit.ui.password.PassWordActivity;
import com.took.firstgit.ui.picture.PictureSelectActivity;
import com.took.firstgit.ui.tab.SlidingTabStripActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button _button1,_button2,_button3,_button4,_button5,_button6,_button7,_button8,_button9;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_home);
        initView();
        setListener();
    }

    private void initView(){
        _button1 = findViewById(R.id.home_but_1);
        _button2 = findViewById(R.id.home_but_2);
        _button3 = findViewById(R.id.home_but_3);
        _button4 = findViewById(R.id.home_but_4);
        _button5 = findViewById(R.id.home_but_5);
        _button6 = findViewById(R.id.home_but_6);
        _button7 = findViewById(R.id.home_but_7);
        _button8 = findViewById(R.id.home_but_8);
        _button9 = findViewById(R.id.home_but_9);
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
