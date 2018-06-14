package com.jkkc.carer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jkkc.carer.Common.Constants;
import com.jkkc.carer.R;
import com.jkkc.carer.bean.LoginBean;
import com.jkkc.carer.bean.ModifyPwd;
import com.jkkc.carer.utils.AppManager;
import com.jkkc.carer.utils.PrefUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Guan on 2018/5/24.
 */

public class ModifyPwdActivity extends AppCompatActivity {

    private static final String TAG1 = ModifyPwdActivity.class.getSimpleName();

    private TextInputEditText mTieOldPwd;
    private TextInputEditText mTieNewPwd;
    private TextInputEditText mTieConfirmPwd;
    private String mOldPwd;
    private String mNewPwd;
    private String mConfirmPwd;

    private LoginBean mLoginBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String result = PrefUtils.getString(this, "loginBean", null);
        Gson gson = new Gson();
        mLoginBean = gson.fromJson(result, LoginBean.class);


        setContentView(R.layout.activity_modify_pwd);
        AppManager.getAppManager().addActivity(this);

        mTieOldPwd = (TextInputEditText) findViewById(R.id.tieOldPwd);
        mTieNewPwd = (TextInputEditText) findViewById(R.id.tieNewPwd);
        mTieConfirmPwd = (TextInputEditText) findViewById(R.id.tieConfirmPwd);


        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOldPwd = mTieOldPwd.getText().toString();
                mNewPwd = mTieNewPwd.getText().toString();
                mConfirmPwd = mTieConfirmPwd.getText().toString();

                if (!TextUtils.isEmpty(mOldPwd) && !TextUtils.isEmpty(mNewPwd)
                        && !TextUtils.isEmpty(mConfirmPwd)) {

                    if (mNewPwd.equals(mConfirmPwd)) {

                        //密码相同
                        OkGo.<String>get(Constants.backPass)
                                .tag(this)
                                .params("token", mLoginBean.getToken())
                                .params("peopleId", mLoginBean.getPeopleId())
                                .params("userAccount", mLoginBean.getUserAccount())
                                .params("password", mOldPwd)
                                .params("newpass", mNewPwd)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                        String result = response.body().toString();
                                        Gson gson1 = new Gson();
                                        ModifyPwd modifyPwd = gson1.fromJson(result, ModifyPwd.class);
                                        String msg = modifyPwd.getMsg();
                                        ToastUtils.showShort(msg);
                                        if (msg.contains("密码更新成功")){
                                            finish();
                                        }



                                    }
                                });

                    } else {

                        ToastUtils.showShort("新密码和确认密码不一致");

                    }

                } else {

                    ToastUtils.showShort("三个密码都不能为空");
                }


            }


        });


        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }


}
