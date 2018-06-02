package com.jkkc.carer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.IdBean;
import com.jkkc.carer.ui.fragment.BedCleanFragment;
import com.jkkc.carer.ui.fragment.DietMedicineFragment;
import com.jkkc.carer.ui.fragment.HealthCheckFragment;
import com.jkkc.carer.ui.fragment.PersonalCleanFragment;
import com.jkkc.carer.ui.fragment.SpecialCareFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Guan on 2018/5/29.
 */

/**
 * {
 "olderId": "122",
 "nurserId": "321"
 }
 */

public class AddCareActivity extends AppCompatActivity
        implements HealthCheckFragment.CallBackValue,
        BedCleanFragment.CallBackValue,
        DietMedicineFragment.CallBackValue,
        PersonalCleanFragment.CallBackValue,
        SpecialCareFragment.CallBackValue {

    private static final int REQUEST_CODE = 100;
    private static final String TAG1 = AddCareActivity.class.getSimpleName();
    private JSONArray mJsonArray;
    private TextView mTvCareProject;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_care);

        mTvCareProject = (TextView) findViewById(R.id.tvCareProject);

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


        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        Log.d(TAG1, "result=" + result);
        Gson gson = new Gson();
        IdBean idBean = gson.fromJson(result, IdBean.class);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("olderId", idBean.olderId);
            jsonObject.put("nurserId", idBean.nurserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mJsonArray = new JSONArray();
        mJsonArray.put(jsonObject);

        // 把Json数据转换成String类型，使用输出流向服务器写
        String content = String.valueOf(mJsonArray);
        Log.d(TAG1, "content=" + content);

        mTvCareProject.setText(content);


        Button btnAddOldMan = (Button) findViewById(R.id.btnAddOldMan);
        btnAddOldMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //扫描二维码
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.d(TAG1, "解析结果:" + result);
//                    ToastUtils.showShort("解析结果:"+result);
                    //扫描成功，添加护理，
                    Gson gson = new Gson();
                    IdBean idBean = gson.fromJson(result, IdBean.class);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("olderId", idBean.olderId);
                        jsonObject.put("nurserId", idBean.nurserId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mJsonArray.put(jsonObject);
                    String content2 = String.valueOf(mJsonArray);
                    Log.d(TAG1, "content2=" + content2);
                    mTvCareProject.setText(content2);


                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort("解析二维码失败");
                }
            }
        }


    }

    @Override
    public void SendMessageValue(String strValue) {

        mTvCareProject.setText("护理项目：" + strValue);


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
