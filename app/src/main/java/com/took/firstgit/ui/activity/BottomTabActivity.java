package com.took.firstgit.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.took.firstgit.R;
import com.took.firstgit.ui.fragment.ContactsFragment;
import com.took.firstgit.ui.fragment.MeFragment;


public class BottomTabActivity extends AppCompatActivity implements View.OnClickListener{
    private Button contactsView,foundView,meView;
    private TextView contactsTv,meTv;
    private ContactsFragment contactsFragment;
    private MeFragment meFragment;
    private LinearLayout foundLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab_custom);

        initView();
    }

    public void initView(){
        contactsView = findViewById(R.id.bottom_tab_custom_friend_img);
        foundView    = findViewById(R.id.bottom_tab_custom_found_img);
        meView       = findViewById(R.id.bottom_tab_custom_me_img);
        foundLayout  = findViewById(R.id.found_container);
        contactsView.setOnClickListener(this);
        foundView.setOnClickListener(this);
        meView.setOnClickListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        contactsView.setSelected(true);
        if(contactsFragment==null){
            contactsFragment = ContactsFragment.newInstance("好友页面");
            transaction.add(R.id.fragment_container,contactsFragment);
        }else{
            transaction.show(contactsFragment);
        }
        transaction.commit();
    }

    public void showToast(String content){
        Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.bottom_tab_custom_friend_img:
                hideAllFragment(transaction);
                selectedView();
                contactsView.setSelected(true);
                contactsTv.setTextColor(getResources().getColor(R.color.colorAccent));
                if(contactsFragment==null){
                    contactsFragment = ContactsFragment.newInstance("好友页面");
                    transaction.add(R.id.fragment_container,contactsFragment);
                }else{
                    transaction.show(contactsFragment);
                }
                transaction.commit();
                break;
            case R.id.bottom_tab_custom_found_img:
                selectedView();

                foundView.setSelected(true);
                foundLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.bottom_tab_custom_me_img:
                hideAllFragment(transaction);
                selectedView();
                meView.setSelected(true);
                meTv.setTextColor(getResources().getColor(R.color.colorAccent));
                if(meFragment==null){
                    meFragment = MeFragment.newInstance("我的页面");
                    transaction.add(R.id.fragment_container,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                transaction.commit();
                break;
        }
    }

    //重置所有文本的选中状态
    public void selectedView(){
        contactsView.setSelected(false);
        foundView.setSelected(false);
        meView.setSelected(false);
        contactsTv.setTextColor(getResources().getColor(R.color.colorGreyText));
        meTv.setTextColor(getResources().getColor(R.color.colorGreyText));
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        foundLayout.setVisibility(View.GONE);
        if(contactsFragment!=null){
            transaction.hide(contactsFragment);
        }
        if(meFragment!=null){
            transaction.hide(meFragment);
        }
    }
}
