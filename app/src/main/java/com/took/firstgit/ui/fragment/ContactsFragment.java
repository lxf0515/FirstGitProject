package com.took.firstgit.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.took.firstgit.R;
import com.took.firstgit.base.BaseFragment;

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
        if(getArguments() != null) {
            _titleName = getArguments().getString("titleName","Default");
            Log.d(TAG,_titleName);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        _nameView = findViewById(R.id.contacts_name);
        _nameView.setText(_titleName);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            Log.d(TAG,"onPause() " + hidden);
            // 相当于onPause()方法--获取焦点
            onPause();
        } else {
            // 相当于onResume()方法---失去焦点
            Log.d(TAG,"onResume() " + hidden);
            onResume();
        }
    }
}
