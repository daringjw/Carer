package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.bean.ShiftBean;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Guan on 2018/6/13.
 */

public class ShiftActivity extends AppCompatActivity {

    String result;
    LoginBean mLoginBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shift);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);

        initView();

        //获取交接班信息
        OkGo.<String>get(Constants.workShiftList)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("userAccount", mLoginBean.getUserAccount())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String result = response.body().toString();

                        Gson gson1 = new Gson();
                        ShiftBean shiftBean = gson1.fromJson(result, ShiftBean.class);
                        String msg = shiftBean.getMsg();
                        if (msg.equals("获取交接班信息成功")) {

                            tvWorkerId.setText("护理员编号："+shiftBean.getWorkerShiftList().getAreaManagerId());
                            tvWorkerName.setText("护理员姓名："+shiftBean.getWorkerShiftList().getWorkerName());
                            tvWorkArea.setText("护理区域："+shiftBean.getWorkerShiftList().getDutyArea());
                            tvAreaManager.setText("区域主管："+shiftBean.getWorkerShiftList().getAreaManager());
                            tvDutyState.setText("值班状态："+shiftBean.getWorkerShiftList().getShiftState());



                        }


                    }
                });


    }

    TextView tvWorkerId, tvWorkerName, tvWorkArea, tvAreaManager, tvDutyState;

    private void initView() {

        tvWorkerId = (TextView) findViewById(R.id.tvWorkerId);
        tvWorkerName = (TextView) findViewById(R.id.tvWorkerName);
        tvWorkArea = (TextView) findViewById(R.id.tvWorkArea);
        tvAreaManager = (TextView) findViewById(R.id.tvAreaManager);
        tvDutyState = (TextView) findViewById(R.id.tvDutyState);


    }


}
