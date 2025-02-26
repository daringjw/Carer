package com.jkkc.carer.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jkkc.carer.R;
import com.jkkc.carer.ui.fragment.DailyCareFragment;
import com.jkkc.carer.ui.fragment.DailyWorkFragment;
import com.jkkc.carer.ui.fragment.MineFragment;
import com.jkkc.carer.utils.AppManager;
import com.tencent.bugly.beta.Beta;

/**
 * Created by Guan on 2018/5/23.
 */

public class HomeActivity extends AppCompatActivity {

    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        //主动升级
        Beta.checkUpgrade();

        AppManager.getAppManager().addActivity(this);

        //Fragment+ViewPager+FragmentViewPager组合的使用
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = new String[]{"日常护理", "日常工作", "个人中心"};
        private Context context;
        private SparseArray<Fragment> fragmentMap;


        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;

            if (fragmentMap == null) {

                fragmentMap = new SparseArray();
                fragmentMap.put(0, new DailyCareFragment());
                fragmentMap.put(1, new DailyWorkFragment());
                fragmentMap.put(2, new MineFragment());

            }
        }

        @Override
        public Fragment getItem(int position) {

            return fragmentMap.get(position);

        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
