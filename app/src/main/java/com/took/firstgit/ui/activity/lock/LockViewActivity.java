package com.took.firstgit.ui.activity.lock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.took.firstgit.R;
import com.took.firstgit.utils.HandlerUtil;

import java.util.List;

public class LockViewActivity extends AppCompatActivity{
    private PatternLockView _patternLockView;
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
        setContentView(R.layout.o_activity_lock_view);

        initView();
        setListener();
    }

    private void initView(){
        _showTv    = findViewById(R.id.lock_content_tv);
        _firstBut  = findViewById(R.id.lock_but_first);
        _secondBut = findViewById(R.id.lock_but_second);
        _checkBut  = findViewById(R.id.lock_but_check);
        _patternLockView  = findViewById(R.id.pattern_lock_view);
        _patternLockView.addPatternLockListener(mPatternLockViewListener);

//        _patternLockView.setInStealthMode(true);
//        _patternLockView.setTactileFeedbackEnabled(true);
    }

    private void setListener(){
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

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(_patternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            String value = PatternLockUtils.patternToString(_patternLockView, pattern);
            Log.d(getClass().getName(), "Pattern complete: " + value);
            //_patternLockView.clearPattern();

            //设置正确/错误模式

            if(_currentType == TYPE_FIRST){
                _firstValue = value;
            }else if(_currentType == TYPE_SECOND){
                _secondValue = value;
                if(_firstValue.equals(_secondValue)){
                    _patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                    _showTv.setText("密码设置成功");
                }else{
                    _patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    _showTv.setText("两次密码不一致");
                }
            }else if(_currentType == TYPE_CHECK){
                _checkValue = value;
                if(_firstValue.equals(_checkValue)){
                    _patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                    _showTv.setText("登录成功");
                }else{
                    _patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    _showTv.setText("登录失败");
                }
            }else{

            }

            HandlerUtil.runOnUiThreadDelay(new Runnable() {
                @Override
                public void run() {
                    _patternLockView.clearPattern();
                }
            },1000);
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeListener();
    }

    private void removeListener(){
        if(mPatternLockViewListener != null){
            _patternLockView.removePatternLockListener(mPatternLockViewListener);
        }
    }
}
