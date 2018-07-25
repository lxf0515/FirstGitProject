package com.took.firstgit.ui.activity.lock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.took.firstgit.R;
import com.took.firstgit.utils.HandlerUtil;
import com.took.firstgit.view.lock.MaterialLockView;

import java.util.List;

public class MyLockViewActivity extends AppCompatActivity{
    private String CorrectPattern = "125789";
    private MaterialLockView materialLockView;
    private TextView _showTv;
    private Button _firstBut,_secondBut,_checkBut;
    private static final int TYPE_FIRST  = 1;//首次设置
    private static final int TYPE_SECOND = 2;//再次确认
    private static final int TYPE_CHECK  = 3;//登录
    private int _currentType = 0;
    private String _firstValue,_secondValue,_checkValue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_my_lock_view);
        initView();
        setListener();
    }

    private void initView(){
        _showTv    = findViewById(R.id.lock_content_tv2);
        _firstBut  = findViewById(R.id.lock_but_first2);
        _secondBut = findViewById(R.id.lock_but_second2);
        _checkBut  = findViewById(R.id.lock_but_check2);
        materialLockView = findViewById(R.id.pattern);
    }

    private void setListener(){
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                Log.d(getClass().getName(), "SimplePattern is " + SimplePattern);

                if(_currentType == TYPE_FIRST){
                    _firstValue = SimplePattern;
                }else if(_currentType == TYPE_SECOND){
                    _secondValue = SimplePattern;
                    if(_firstValue.equals(_secondValue)){
                        materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                        _showTv.setText("密码设置成功");
                    }else{
                        materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                        _showTv.setText("两次密码不一致");
                    }
                }else if(_currentType == TYPE_CHECK){
                    _checkValue = SimplePattern;
                    if(_firstValue.equals(_checkValue)){
                        materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                        _showTv.setText("登录成功");
                    }else{
                        materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                        _showTv.setText("登录失败");
                    }
                }else{

                }

                HandlerUtil.runOnUiThreadDelay(new Runnable() {
                    @Override
                    public void run() {
                        materialLockView.clearPattern();
                    }
                },1000);
                super.onPatternDetected(pattern, SimplePattern);
            }
        });

        _firstBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentType = 1;
                _showTv.setText("初次设置密码");
            }
        });
        _secondBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentType = 2;
                _showTv.setText("确认密码");
            }
        });
        _checkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentType = 3;
                _showTv.setText("登录密码");
            }
        });
    }
}
