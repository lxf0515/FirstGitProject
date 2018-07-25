package com.took.firstgit.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.took.firstgit.R;
import com.took.firstgit.adapter.MyViewPagerAdapter;
import com.took.firstgit.ui.fragment.ContactsFragment;
import com.took.firstgit.ui.fragment.FoundFragment;
import com.took.firstgit.ui.fragment.MeFragment;
import com.took.firstgit.view.NoTouchViewPager;
import com.took.firstgit.view.SpecialTab;
import com.took.firstgit.view.SpecialTabRound;
import java.util.ArrayList;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class PagerBottomTabStripActivity extends AppCompatActivity {

    private PageNavigationView tabView;
    private NoTouchViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        initView();
        initNavigationView();
    }

    public void initView(){
        tabView = findViewById(R.id.bottom_tab);
        viewPager = findViewById(R.id.bottom_view_pager);
    }

    public void initNavigationView(){
        NavigationController navigationController = tabView.custom()
                .addItem(newItem(R.drawable.tab_contacts,R.drawable.tab_contacts_hover,"好友"))
                .addItem(newRoundItem(R.drawable.tab_found,R.drawable.tab_found_hover,"发现"))
                .addItem(newItem(R.drawable.tab_me,R.drawable.tab_me_hover,"我的"))
                .build();
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                if(index == 1) showToast("index " + index + " old " + old);
            }

            @Override
            public void onRepeat(int index) {

            }
        });

        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(ContactsFragment.newInstance(getResources().getString(R.string.title_contacts)));
        fragments.add(FoundFragment.newInstance(getResources().getString(R.string.title_found)));
        fragments.add(MeFragment.newInstance(getResources().getString(R.string.title_me)));

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable,int checkedDrawable,String text){
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }

    public void showToast(String content){
        Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
    }
}
