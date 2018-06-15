package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.bean.SchedulingInfomation;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Guan on 2018/6/14.
 */

public class SchedulingInformationActivity extends AppCompatActivity {

    private static final String TAG1 = SchedulingInformationActivity.class.getSimpleName();

    String result;
    LoginBean mLoginBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scheduling_info);

        result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);


        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        initView();


        //获取排班信息
        OkGo.<String>get(Constants.workScheduleList)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String result = response.body().toString();
                        Log.d(TAG1, "result=" + result);
                        Gson gson1 = new Gson();
                        SchedulingInfomation schedulingInfomation = gson1.fromJson(result, SchedulingInfomation.class);
                        String msg = schedulingInfomation.getMsg();
                        if (msg.equals("获取排班信息成功")) {

                            tvWorkerNo.setText("护理员编号：" + schedulingInfomation.getWorkSchedule().get(0).getWorkerId());
                            tvWorkerName.setText("护理员姓名：" + schedulingInfomation.getWorkSchedule().get(0).getWorkerName());
                            tvBanci.setText("班次：" + schedulingInfomation.getWorkSchedule().get(0).getClasses());
                            tvDutyPos.setText("值班岗位：" + schedulingInfomation.getWorkSchedule().get(0).getDutyPosition());
                            tvAreaManager.setText("区域主管：" + schedulingInfomation.getWorkSchedule().get(0).getAreaManager());
                            tvDepartName.setText("部门：" + schedulingInfomation.getWorkSchedule().get(0).getDeptName());
                            String dutyString = schedulingInfomation.getWorkSchedule().get(0).getDutyString();
                            dutyString = dutyString.substring(0, 10);
                            tvDutyTime.setText("值班日期：" + dutyString);


                        }


                    }
                });


    }

    TextView tvWorkerNo, tvWorkerName, tvBanci,
            tvDutyPos, tvAreaManager, tvDepartName, tvDutyTime;

    private void initView() {

        tvWorkerNo = (TextView) findViewById(R.id.tvWorkerNo);
        tvWorkerName = (TextView) findViewById(R.id.tvWorkerName);
        tvBanci = (TextView) findViewById(R.id.tvBanci);
        tvDutyPos = (TextView) findViewById(R.id.tvDutyPos);
        tvAreaManager = (TextView) findViewById(R.id.tvAreaManager);
        tvDepartName = (TextView) findViewById(R.id.tvDepartName);
        tvDutyTime = (TextView) findViewById(R.id.tvDutyTime);


    }


}
