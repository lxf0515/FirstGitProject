package com.took.firstgit.ui.Menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.took.firstgit.R;
import com.took.firstgit.event.ConstactEventMessage;

import org.greenrobot.eventbus.EventBus;

public class FloatingActionMenuActivity extends AppCompatActivity{
    private static final String tag = FloatingActionMenuActivity.class.getSimpleName();
    private Context context;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_menu);
        context = this;
        initView();
    }

    private void initView(){
        floatingActionMenu  = findViewById(R.id.fab);
        floatingActionMenu.setClosedOnTouchOutside(true);

        fab1 = findViewById(R.id.fab_share);
        fab2 = findViewById(R.id.fab_upload);
        fab3 = findViewById(R.id.fab_preview);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"分享",Toast.LENGTH_SHORT).show();
                floatingActionMenu.close(true);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"上传",Toast.LENGTH_SHORT).show();
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"预览",Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new ConstactEventMessage("" + tag));
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new ConstactEventMessage("" + tag));
        super.onDestroy();
    }
}
