package com.took.firstgit.ui.notice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.took.firstgit.R;
import com.took.firstgit.utils.LogUtil;


public class ONoticeActivity extends AppCompatActivity{

    public Button  button0,button1;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_notice_activity);
        this.context = this;
        initView();
    }

    private void initView(){
        button0 = findViewById(R.id.o_notice_but_0);
        button1 = findViewById(R.id.o_notice_but_1);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNotificationEnable(context,0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNotificationEnable(context,1);
            }
        });
    }

    AlertDialog alertDialog = null;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void checkNotificationEnable(final Context context,final int type) {
        boolean bool = NotificationUtils.isNotificationEnabled(context);

        LogUtil.d("Notification is open " + bool);
        if (!bool) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
            localBuilder.setTitle("通知权限弹窗");
            localBuilder.setMessage("检测到您还没有打开通知是否去打开");
            localBuilder.setPositiveButton("去打开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(type == 1) {
                        NotificationUtils.toSetting2(context);
                    }else if(type == 0){
                        NotificationUtils.toSetting(context);
                    }
                }
            }).setNegativeButton("暂不打开", null);
            localBuilder.show();
        }else{
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
            localBuilder.setTitle("通知权限弹窗");
            localBuilder.setMessage("检测到您已经打开通知了");
            localBuilder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog = localBuilder.create();
            localBuilder.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
