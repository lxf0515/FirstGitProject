package com.took.firstgit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.took.firstgit.R;
import com.took.firstgit.base.BaseFragment;
import com.took.firstgit.event.ConstactEventMessage;
import com.took.firstgit.event.MeEventMessage;
import com.took.firstgit.ui.Menu.FloatingActionMenuActivity;
import com.took.firstgit.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ContactsFragment extends BaseFragment {
    private static final String TAG = ContactsFragment.class.getSimpleName();
    private TextView _nameView;
    private String   _titleName;
    public static ContactsFragment newInstance(String value) {
        Bundle args = new Bundle();
        ContactsFragment fragment = new ContactsFragment();
        args.putString("titleName",value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_contacts_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        if(getArguments() != null) {
            _titleName = getArguments().getString("titleName","Default");
            //Log.d(TAG,_titleName);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        _nameView = findViewById(R.id.contacts_name);
        _nameView.setText(_titleName);
        _nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatingActionMenuActivity.class,false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()" + _titleName);
        LogUtil.d("Contacts onResume()" + _titleName);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()" + _titleName);
        LogUtil.d("Contacts onResume()" + _titleName);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
//        if (hidden) {
//            Log.d(TAG,"onPause() " + hidden);
//            // 相当于onPause()方法--获取焦点
//            onPause();
//        } else {
//            // 相当于onResume()方法---失去焦点
//            Log.d(TAG,"onResume() " + hidden);
//            onResume();
//        }
    }

    private void startActivity(Class<?> cla,boolean isFinish){
        Intent intent = new Intent(getActivity(),cla);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ConstactEventMessage event) {
        LogUtil.d("Contacts ConstactEventMessage() " + event.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
