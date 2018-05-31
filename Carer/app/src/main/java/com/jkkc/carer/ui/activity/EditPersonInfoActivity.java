package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.bean.WorkerBaseInfo;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.jkkc.carer.R.id.tvSex;

/**
 * Created by Guan on 2018/5/26.
 */

public class EditPersonInfoActivity extends AppCompatActivity {

    private TextView mTvSex;

    LoginBean mLoginBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);

        setContentView(R.layout.activity_edit_person_info);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        mTvSex = (TextView) findViewById(tvSex);


        LinearLayout llSex = (LinearLayout) findViewById(R.id.llSex);
        llSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(EditPersonInfoActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("修改性别")
                        .setContentText("")
                        .setConfirmText("男")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                mTvSex.setText("男");

                            }
                        })
                        .setCancelText("女")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();

                                mTvSex.setText("女");

                            }
                        })
                        .show();

            }
        });


        final EditText etName = (EditText) findViewById(R.id.etName);
        final TextView tvSex = (TextView) findViewById(R.id.tvSex);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etPhone = (EditText) findViewById(R.id.etPhone);
        final EditText etHomeAddress = (EditText) findViewById(R.id.etHomeAddress);


        //获取个人信息
        OkGo.<String>get(Constants.getCare)
                .tag(this)
                .params("token", mLoginBean.getToken())
                .params("peopleId", mLoginBean.getPeopleId())
                .params("phoneNum", mLoginBean.getPhoneNum())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        String result = response.body().toString();
                        Gson gson1 = new Gson();
                        WorkerBaseInfo workerBaseInfo = gson1.fromJson(result, WorkerBaseInfo.class);
                        String code = workerBaseInfo.getCode();
                        if (code.contains("success")) {

                            etName.setText(workerBaseInfo.getWorkerBaseInfo().getPeopleName());
                            tvSex.setText(workerBaseInfo.getWorkerBaseInfo().getGender());
                            etAge.setText(workerBaseInfo.getWorkerBaseInfo().getBirthday());
                            etPhone.setText(workerBaseInfo.getWorkerBaseInfo().getPhoneNum());
                            etHomeAddress.setText(workerBaseInfo.getWorkerBaseInfo().getAddressCity()
                                    + workerBaseInfo.getWorkerBaseInfo().getAddressCounty()
                                    + workerBaseInfo.getWorkerBaseInfo().getAddressDetail());


                        }


                    }
                });


        //个人信息修改
        TextView tvSave = (TextView) findViewById(R.id.tvSave);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*final EditText etName = (EditText) findViewById(R.id.etName);
                final TextView tvSex = (TextView) findViewById(R.id.tvSex);
                final EditText etAge = (EditText) findViewById(R.id.etAge);
                final EditText etPhone = (EditText) findViewById(R.id.etPhone);
                final EditText etHomeAddress = (EditText) findViewById(R.id.etHomeAddress);*/

                String gender = tvSex.getText().toString();
                String peopleName = etName.getText().toString();
                String phoneNum = etPhone.getText().toString();

                OkGo.<String>get(Constants.updCare)
                        .tag(this)
                        .params("token", mLoginBean.getToken())
                        .params("peopleId", mLoginBean.getPeopleId())
                        .params("phoneNum", phoneNum)
                        .params("gender", gender)
                        .params("peopleName", peopleName)
                        .params("nationality", "")
                        .params("bloodType", "")
                        .params("firstDept", "")
                        .params("duty", "")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                String reuslt = response.body().toString();
                                if (result.contains("success")){

                                    setResult(RESULT_OK);
                                    finish();

                                }


                            }
                        });


            }
        });


    }


}
