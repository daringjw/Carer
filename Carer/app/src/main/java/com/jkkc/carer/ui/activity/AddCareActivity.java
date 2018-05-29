package com.jkkc.carer.ui.activity;

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
import android.view.View;
import android.widget.ImageView;

import com.jkkc.carer.R;
import com.jkkc.carer.ui.fragment.BedCleanFragment;
import com.jkkc.carer.ui.fragment.DietMedicineFragment;
import com.jkkc.carer.ui.fragment.HealthCheckFragment;
import com.jkkc.carer.ui.fragment.PersonalCleanFragment;
import com.jkkc.carer.ui.fragment.SpecialCareFragment;

/**
 * Created by Guan on 2018/5/29.
 */

public class AddCareActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_care);

        //Fragment+ViewPager+FragmentViewPager组合的使用
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public final int COUNT = 5;
        private String[] titles = new String[]{"健康检测类", "个人清洁类", "饮食服药类", "床位清洁类", "特殊护理类"};
        private Context context;

        private SparseArray<Fragment> fragmentMap;


        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;

            if (fragmentMap == null) {

                fragmentMap = new SparseArray();
                fragmentMap.put(0, new HealthCheckFragment());
                fragmentMap.put(1, new PersonalCleanFragment());
                fragmentMap.put(2, new DietMedicineFragment());
                fragmentMap.put(3, new BedCleanFragment());
                fragmentMap.put(4, new SpecialCareFragment());

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


}
