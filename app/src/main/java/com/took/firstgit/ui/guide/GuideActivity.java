package com.took.firstgit.ui.guide;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.took.firstgit.R;
import com.yanzhenjie.permission.AndPermission;

public class GuideActivity extends AppCompatActivity{
    Button button1,button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        //GuideUtil.getInstance().initGuide(this, R.drawable.add_guide);
        setListener();

        /**权限申请**/
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.SYSTEM_ALERT_WINDOW)
                .send();
        //打开悬浮穿权限
        if(Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
            } else {
                //TODO do something you need
                GuideUtil.getInstance().initGuide(this, R.drawable.add_guide);
            }
        }
    }

    private void initView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
    }

    private void setListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideUtil.getInstance().initGuide(GuideActivity.this, R.drawable.add_guide);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideUtil.getInstance().setFirst(false);
                GuideUtil.getInstance().initGuide(GuideActivity.this, R.drawable.add_guide);
            }
        });
    }
}
