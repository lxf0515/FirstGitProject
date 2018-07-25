package com.took.firstgit.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.took.firstgit.R;
import com.took.firstgit.base.BaseFragment;

public class FoundFragment extends BaseFragment {
    private static final String TAG = FoundFragment.class.getSimpleName();
    private TextView _nameView;
    private String   _titleName;
    public static FoundFragment newInstance(String value) {
        Bundle args = new Bundle();
        FoundFragment fragment = new FoundFragment();
        args.putString("titleName",value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_found_layout;
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
        _nameView = findViewById(R.id.found_name);
        _nameView.setText(_titleName);
    }
}
