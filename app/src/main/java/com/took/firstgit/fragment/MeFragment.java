package com.took.firstgit.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.took.firstgit.R;
import com.took.firstgit.base.BaseFragment;

public class MeFragment extends BaseFragment {
    private static final String TAG = MeFragment.class.getSimpleName();
    private TextView _nameView;
    private String   _titleName;
    public static MeFragment newInstance(String value) {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        args.putString("titleName",value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me_layout;
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
        _nameView = findViewById(R.id.me_name);
        _nameView.setText(_titleName);
    }
}
