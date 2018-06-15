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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.IdBean;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.ui.fragment.BedCleanFragment;
import com.jkkc.carer.ui.fragment.DietMedicineFragment;
import com.jkkc.carer.ui.fragment.HealthCheckFragment;
import com.jkkc.carer.ui.fragment.PersonalCleanFragment;
import com.jkkc.carer.ui.fragment.SpecialCareFragment;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Guan on 2018/5/29.
 */

/**
 * {
 * "olderId": "122",
 * "nurserId": "321"
 * }
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

    private List<IdBean> mIdBeanList;
    private IdBean mIdBean;

    private IdBean mIdBean1;


    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    MyAdapter mAdapter;
    private String mContent2;

    LoginBean mLoginBean;


    private MyFragmentPagerAdapter mAdapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_care);

        final String resultx = PrefUtils.getString(this, "loginBean", null);
        Gson gsonx = new Gson();
        mLoginBean = gsonx.fromJson(resultx, LoginBean.class);

        //Fragment+ViewPager+FragmentViewPager组合的使用
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter1 = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(mAdapter1);

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

        Gson gson = new Gson();
        mIdBean = gson.fromJson(result, IdBean.class);
        mIdBean.careProject = careName;
        mIdBean.careName = variety;


        Button btnAddProject = (Button) findViewById(R.id.btnAddProject);

        btnAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加护理项目
                if (mIdBeanList == null) {

                    Intent intent = getIntent();
                    String result = intent.getStringExtra("result");
                    Gson gson = new Gson();
                    mIdBean = gson.fromJson(result, IdBean.class);
                    mIdBean.careProject = careName;
                    mIdBean.careName = variety;

                    mIdBeanList = new ArrayList<>();
                    mIdBeanList.add(mIdBean);

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("olderId", mIdBean.olderId);
                        jsonObject.put("nurserId", mIdBean.nurserId);
                        jsonObject.put("nursingName", mIdBean.careName);
                        jsonObject.put("nursingValue", mIdBean.careProject);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mJsonArray = new JSONArray();
                    mJsonArray.put(jsonObject);

                    // 把Json数据转换成String类型，使用输出流向服务器写
                    mContent2 = String.valueOf(mJsonArray);
                    Log.d(TAG1, "content=" + mContent2);


                } else if (mIdBean1 != null) {

                    mIdBean1.careProject = careName;
                    mIdBean1.careName = variety;

                    mIdBeanList.add(mIdBean1);
                    mIdBean1 = null;

                } else {

                    ToastUtils.showShort("请继续添加老人");

                }


                for (IdBean idBean : mIdBeanList) {
                    Log.d(TAG1, "careProject=" + idBean.careProject);
                }

                //添加后，界面显示recyclerview
                mRecyclerView = (RecyclerView) findViewById(R.id.rvCareProject);
//创建默认的线性LayoutManager
                mLayoutManager = new LinearLayoutManager(AddCareActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
                mRecyclerView.setHasFixedSize(true);
//创建并设置Adapter
                mAdapter = new MyAdapter(mIdBeanList);
                mRecyclerView.setAdapter(mAdapter);


            }
        });


//        mTvCareProject.setText(content);


        Button btnAddOldMan = (Button) findViewById(R.id.btnAddOldMan);
        btnAddOldMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //扫描二维码
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        //提交
        Button btnCommit = (Button) findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG1, "mContent2=" + mContent2);
                OkGo.<String>get(Constants.addDailyNursingInfo)
                        .tag(this)
                        .params("token", mLoginBean.getToken())
                        .params("peopleId", mLoginBean.getPeopleId())
                        .params("userAccount", mLoginBean.getUserAccount())
                        .params("nursingJson", mContent2)
//                        .params("nursingName","健康1")
//                        .params("nursingValue","剪指甲")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                String result = response.body().toString();
                                Log.d(TAG1, "result=" + result);
                                ToastUtils.showShort(result);

                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);

                                ToastUtils.showShort("抱歉，服务器开小差了");


                            }
                        });


            }
        });


    }

    private String[] getDummyDatas() {

        String[] a = {"北京", "上海", "广东", "深圳"};
        return a;

    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public List<IdBean> datas = null;

        public MyAdapter(List<IdBean> datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_care_project, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            viewHolder.mTextView.setText("老人id:" + datas.get(position).olderId + "  ;  "
                    + "护理类别：" + datas.get(position).careName
                    + "\n护理项目：" + datas.get(position).careProject);


        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.tvCareProject);
            }
        }
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
                    mIdBean1 = gson.fromJson(result, IdBean.class);
//                    mIdBean1.careProject = careName;


                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("olderId", mIdBean1.olderId);
                        jsonObject.put("nurserId", mIdBean1.nurserId);

                        jsonObject.put("nursingName", mIdBean.careName);
                        jsonObject.put("nursingValue", mIdBean.careProject);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mJsonArray.put(jsonObject);

                    mContent2 = String.valueOf(mJsonArray);


                    Log.d(TAG1, "content2=" + mContent2);


                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort("解析二维码失败");
                }
            }
        }


    }

    String careName;

    @Override
    public void SendMessageValue(String strValue) {

        careName = strValue;

    }

    String variety;

    @Override
    public void SendVariety(String str) {

        variety = str;
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
