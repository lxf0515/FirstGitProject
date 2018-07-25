package com.took.firstgit.ui.password;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jungly.gridpasswordview.GridPasswordView;
import com.took.firstgit.R;


public class PassWordActivity extends AppCompatActivity{

    GridPasswordView gridPasswordView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_password);

        gridPasswordView = findViewById(R.id.pswView);

        gridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                Log.d(getClass().getName(), "onTextChanged() " + psw);
            }

            @Override
            public void onInputFinish(String psw) {
                Log.d(getClass().getName(), "onInputFinish() " + psw);
            }
        });
    }

}
